package project.toy.webtoon_copy.comments;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import project.toy.webtoon_copy.util.CheckUtils;

public interface RootCommentDto {

    public Exception checkUser();
}
