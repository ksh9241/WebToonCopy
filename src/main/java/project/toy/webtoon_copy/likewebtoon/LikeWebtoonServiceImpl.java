package project.toy.webtoon_copy.likewebtoon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.toy.webtoon_copy.user.User;
import project.toy.webtoon_copy.user.UserRepository;
import project.toy.webtoon_copy.user.UserService;
import project.toy.webtoon_copy.webtoon.Webtoon;
import project.toy.webtoon_copy.webtoon.WebtoonService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeWebtoonServiceImpl implements LikeWebtoonService{

    @Autowired
    LikeWebtoonRepository likeWebtoonRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WebtoonService webtoonService;

    @Override
    public List<LikeWebtoonResponseDto> findAllLikeWebtoonByUserSeq(Long userSeq) {
        List<LikeWebtoon> list = likeWebtoonRepository.findAllByUserSeq(userSeq);
        List<LikeWebtoonResponseDto> responseDtoList = list.stream().map(LikeWebtoon::toDto).collect(Collectors.toList());

        return responseDtoList;
    }

    @Override
    public void likeWebtoon(Long userSeq, Long webtoonSeq) {
        Optional<User> findUser = userRepository.findByUserSeq(userSeq);
        User user = findUser.orElseThrow(() -> new UsernameNotFoundException("유저 정보가 없습니다."));  // 잘못된 유저 정보일 경우

        LikeWebtoon likeWebtoon = new LikeWebtoon();
        likeWebtoon.setUser(user);
    }
}
