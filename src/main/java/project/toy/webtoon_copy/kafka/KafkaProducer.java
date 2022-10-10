package project.toy.webtoon_copy.kafka;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import project.toy.webtoon_copy.comments.RootCommentDto;

@Service
public class KafkaProducer {

    private static final String TOPIC = "example";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(RootCommentDto commentDto) {
        Gson gson = new Gson();
        String message = gson.toJson(commentDto);
        this.kafkaTemplate.send(TOPIC, message);
    }

}
