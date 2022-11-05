package project.toy.webtoon_copy.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/createComment")
    public String createComment(@ModelAttribute CommentRequestDto commentRequestDto) {
        CommentResponseDto resultDto = commentService.createComment(commentRequestDto.toEntity());
        return null;
    }

    @PutMapping("/deleteComment")
    public String deleteComment(@RequestParam Long commentSeq) {
        commentService.deleteComment(commentSeq);
        return null;
    }
}
