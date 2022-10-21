package project.toy.webtoon_copy.cookie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CookieRepository extends JpaRepository<Cookie, Long> {
    @Query(value = "SELECT A.* FROM cookie A WHERE cookie_seq = :cookieSeq", nativeQuery = true)
    Cookie findByCookieSeq(@Param("cookieSeq") Long cookieSeq);
}
