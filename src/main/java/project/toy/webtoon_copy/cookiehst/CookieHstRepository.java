package project.toy.webtoon_copy.cookiehst;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CookieHstRepository extends JpaRepository<CookieHst, Long> {
    @Query(value = "SELECT A.* FROM cookie_hst A WHERE A.cookie_seq = :cookieSeq", nativeQuery = true)
    List<CookieHst> findAllByCookieSeq(@Param("cookieSeq") Long cookieDto);
}
