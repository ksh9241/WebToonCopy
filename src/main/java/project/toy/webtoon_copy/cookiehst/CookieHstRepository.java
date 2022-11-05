package project.toy.webtoon_copy.cookiehst;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CookieHstRepository extends JpaRepository<CookieHst, Long> {
    @Query(value = "SELECT A.* FROM cookie_hst A WHERE A.cookie_cookie_seq = :cookieSeq", nativeQuery = true)
    List<CookieHst> findAllByCookieSeq(@Param("cookieSeq") Long cookieDto);

    @Query(value = "SELECT * FROM cookie_hst A, cookie B, users C WHERE A.cookie_hst_seq = :cookieHstSeq AND A.cookie_cookie_seq = B.cookie_seq AND B.user_user_seq = C.user_seq", nativeQuery = true)
//    @Query(value = "SELECT A FROM CookieHst A JOIN FETCH A.cookie WHERE A.cookieHstSeq = :cookieHstSeq")
    CookieHst findByCookieHstSeq(@Param("cookieHstSeq") Long cookieHstSeq);
}
