package project.toy.webtoon_copy.comments;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.toy.webtoon_copy.kafka.KafkaProducer;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    KafkaProducer kafkaProducer;

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        commentDto.checkUser();

        kafkaProducer.sendMessage(commentDto);

        return commentDto;
    }

    @Override
    @Transactional
    public CommentDto afterCreateComment(CommentDto commentDto) {
        Comment comment = mapper.map(commentDto, Comment.class);
        Comment resComment = commentRepository.save(comment);
        CommentDto resultDto = mapper.map(resComment, CommentDto.class);
        return resultDto;
    }

    @Override
    public CommentDto deleteComment(CommentDto commentDto) {
        kafkaProducer.sendMessage(setRequiredDeleteVal(commentDto));
        return commentDto;
    }

    private CommentDto setRequiredDeleteVal (CommentDto commentDto) {
        commentDto.setDeleteYn("Y");
        commentDto.setModifyDt(LocalDateTime.now());
        return commentDto;
    }
}
