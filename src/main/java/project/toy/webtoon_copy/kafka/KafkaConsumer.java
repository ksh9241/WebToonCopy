package project.toy.webtoon_copy.kafka;

import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import project.toy.webtoon_copy.comments.CommentResponseDto;
import project.toy.webtoon_copy.comments.CommentService;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class KafkaConsumer {

    private Gson gson;

    @Autowired
    CommentService commentService;

    /*
    * Gson 사용 시 LocalDateTime에 대한 문제가 있어서 역직렬화 설정을 해줘야 한다.
    * */
    private static Gson initDeserializerGson() {
        return new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }
        }).create();
    }

    @KafkaListener(topics = "example", groupId = "foo")
    public void consumer(String message) throws IOException {
        gson = initDeserializerGson();
        CommentResponseDto commentDto = gson.fromJson(message, CommentResponseDto.class);

        commentService.afterCreateComment(commentDto);
    }
}
