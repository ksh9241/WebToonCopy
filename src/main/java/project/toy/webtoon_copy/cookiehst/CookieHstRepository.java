package project.toy.webtoon_copy.cookiehst;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CookieHstRepository extends JpaRepository<CookieHst, Long> {
    List<CookieHst> findAllByCookieSeq(Long cookieSeq);
}
