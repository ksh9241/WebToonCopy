package project.toy.webtoon_copy.comments;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findByCommentSeq(Long commentSeq);
}
