package project.toy.webtoon_copy.kafka;

import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import project.toy.webtoon_copy.comments.CommentDto;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class KafkaProducer {

    private static final String TOPIC = "example";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private Gson gson;

    /*
     * Gson 사용 시 LocalDateTime에 대한 문제가 있어서 직렬화 설정을 해줘야 한다.
     * */
    private static Gson initSerializerGson() {
        return new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
            @Override
            public JsonElement serialize(LocalDateTime localDateTime, Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime));
            }
        }).create();
    }

    @Autowired
    public KafkaProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(CommentDto commentDto) {
        gson = initSerializerGson();
        String message = gson.toJson(commentDto);

        this.kafkaTemplate.send(TOPIC, message);
    }

}
