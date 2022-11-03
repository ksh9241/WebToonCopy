package project.toy.webtoon_copy.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/createComment")
    public String createComment(CommentRequestDto commentRequestDto) {
        CommentResponseDto resultDto = commentService.createComment(commentRequestDto.toEntity());
        return null;
    }

    @PutMapping("/deleteComment")
    public String deleteComment(CommentRequestDto commentRequestDto) {
        commentService.deleteComment(commentRequestDto.toEntity());
        return null;
    }
}
