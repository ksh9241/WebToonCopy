package project.toy.webtoon_copy.comment;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.toy.webtoon_copy.comments.CommentDto;
import project.toy.webtoon_copy.comments.CommentService;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest // 트랜잭션을 넣어줘야 함. (롤백이 안되기 때문에)
public class CommentServiceTest {

    @Autowired
    CommentService commentService;

    private CommentDto commentDto;

    @BeforeEach
    void setUp() {
        commentDto = new CommentDto(1L, "test", 0L, 0L, LocalDateTime.now(), null, "N", null, null);
    }

    @Test
    void setRequiredDeleteVal_success() {
        commentDto.setDeleteYn("Y");
        commentDto.setModifyDt(LocalDateTime.now());

        assertThat("Y", is(commentDto.getDeleteYn()));
        assertThat(commentDto.getModifyDt(), is(notNullValue()));
    }

    @Test
    void setRequiredDeleteVal_fail() {
        commentDto.setDeleteYn("Y");
        commentDto.setModifyDt(LocalDateTime.now());

        assertThat("N", is(commentDto.getDeleteYn()));
        assertThat(commentDto.getModifyDt(), is(nullValue()));
    }

}
