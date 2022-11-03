package project.toy.webtoon_copy.util;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class Common {

    LocalDateTime createAt;
    LocalDateTime modifyAt;
    LocalDateTime efctStAt;
    LocalDateTime efctFnsAt;
}
