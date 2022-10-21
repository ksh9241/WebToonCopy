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
    public String createComment(CommentDto commentDto) {
        CommentDto resultDto = commentService.createComment(commentDto);
        return null;
    }

    @PutMapping("/deleteComment")
    public String deleteComment(CommentDto commentDto) {
        commentService.deleteComment(commentDto);
        return null;
    }
}
