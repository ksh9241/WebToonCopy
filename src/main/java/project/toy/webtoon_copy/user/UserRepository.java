package project.toy.webtoon_copy.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
    User findByUserSeq(Long userSeq);   // Optional<> 로 변경하기
}
