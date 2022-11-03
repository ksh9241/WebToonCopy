package project.toy.webtoon_copy.util;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class Common {

    @CreationTimestamp // Insert 시 자동 값 채워줌
    LocalDateTime createAt;
    @UpdateTimestamp    // Update 시 자동 값 채워줌
    LocalDateTime modifyAt;
    @CreationTimestamp
    LocalDateTime efctStAt;
    LocalDateTime efctFnsAt  = LocalDateTime.of(9999,12,31,23,99,99);
}
