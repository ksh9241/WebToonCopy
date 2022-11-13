package project.toy.webtoon_copy.likewebtoon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeWebtoonRepository extends JpaRepository<LikeWebtoon, Long> {

    @Query(value = "SELECT * FROM like_webtoon WHERE user_user_seq = :userSeq", nativeQuery = true)
    List<LikeWebtoon> findAllByUserSeq(@Param("userSeq") Long userSeq);
}
