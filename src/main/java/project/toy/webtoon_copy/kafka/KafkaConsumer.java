package project.toy.webtoon_copy.kafka;

import com.google.gson.Gson;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import project.toy.webtoon_copy.comments.RootCommentDto;

import java.io.IOException;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "example", groupId = "foo")
    public void consumer(String message) throws IOException {
        Gson gson = new Gson();
        RootCommentDto dto = gson.fromJson(message, RootCommentDto.class);
    }
}
