package project.toy.webtoon_copy.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.toy.webtoon_copy.kafka.KafkaProducer;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    KafkaProducer kafkaProducer;

    @Override
    public CommentResponseDto createComment(Comment comment) {
        comment.checkUser();

        kafkaProducer.sendMessage(comment);

        return comment.toDto();
    }

    @Override
    @Transactional
    public CommentResponseDto afterCreateComment(Comment comment) {
        commentRepository.save(comment);
//        commentRepository.getReferenceById() // proxy 객체를 생성 (유저아이디만 넘겨서 프록시 객체를 생성함.)
        return comment.toDto();
    }

    @Override
    public CommentResponseDto deleteComment(Long commentSeq) {
        Comment comment = findByCommentSeq(commentSeq);
        kafkaProducer.sendMessage(setRequiredDeleteVal(comment));
        return comment.toDto();
    }

    public Comment findByCommentSeq(Long commentSeq) {
        return commentRepository.findByCommentSeq(commentSeq);
    }

    private Comment setRequiredDeleteVal (Comment comment) {
        comment.setDeleteYn("Y");
        comment.setModifyAt(LocalDateTime.now());
        return comment;
    }
}
