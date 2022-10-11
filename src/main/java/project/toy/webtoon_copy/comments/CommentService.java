package project.toy.webtoon_copy.comments;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto);
    CommentDto afterCreateComment(CommentDto commentDto);
}
