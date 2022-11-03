package project.toy.webtoon_copy.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
    Optional<User> findByUserSeq(Long userSeq);   // Optional<> 로 변경하기
}
