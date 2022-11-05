package project.toy.webtoon_copy.comment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import project.toy.webtoon_copy.comments.Comment;
import project.toy.webtoon_copy.comments.CommentResponseDto;
import project.toy.webtoon_copy.comments.CommentService;
import project.toy.webtoon_copy.user.User;
import project.toy.webtoon_copy.webtoon.Webtoon;

import javax.transaction.Transactional;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.notNullValue;


@SpringBootTest // 트랜잭션을 넣어줘야 함. (롤백이 안되기 때문에)
@Transactional
public class CommentServiceTest {

    @Autowired
    CommentService commentService;

    private Comment comment;

    private MessageSource messageSource;

    @BeforeEach
    void initComment() {
        comment = new Comment();
        comment.setCommentSeq(1L);
        comment.setDescription("test");
        comment.setWebtoon(new Webtoon());
    }

    @Test
    @DisplayName("유저정보 확인 후 댓글 등록")
    void createComment_Success() {
        User user = new User();
        user.setUserId("testId");
        comment.setUser(user);

        CommentResponseDto responseDto = commentService.createComment(comment);
        assertNotNull(responseDto);
    }

    @Test
    @DisplayName("유저정보 없을 때 댓글 등록")
    void createComment_NotUser() {
        comment.setUser(new User());
        assertThrows(UsernameNotFoundException.class, ()-> commentService.createComment(comment));
    }

    @Test
    @DisplayName("댓글 PK로 해당 댓글 엔티티 조회")
    void findByCommentSeq() {
        Comment comment = commentService.findByCommentSeq(12L);
        System.out.println(comment);
        assertNotNull(comment);
    }

//    @Test
//    @DisplayName("댓글 삭제")
//    void deleteComment() {
//
//    }
}
