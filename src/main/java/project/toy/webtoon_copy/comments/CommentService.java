package project.toy.webtoon_copy.comments;

public interface CommentService {
    CommentResponseDto createComment(Comment comment);

    CommentResponseDto afterCreateComment(Comment comment);

    CommentResponseDto deleteComment(Long commentSeq);

    Comment findByCommentSeq(Long commentSeq);
}
