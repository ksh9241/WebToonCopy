package project.toy.webtoon_copy.comments;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        commentDto.checkUser();
        Comment comment = mapper.map(commentDto, Comment.class);
        Comment resComment = commentRepository.save(comment);
        CommentDto resultDto = mapper.map(resComment, CommentDto.class);
        return resultDto;
    }
}
