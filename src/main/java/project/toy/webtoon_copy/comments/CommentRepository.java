package project.toy.webtoon_copy.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT A.* FROM comment A WHERE A.comment_seq = :commentSeq", nativeQuery = true)
    Comment findByCommentSeq(@Param("commentSeq") Long commentSeq);
}
