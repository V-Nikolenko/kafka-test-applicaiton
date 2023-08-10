package example.kafka.producer;

import example.kafka.UserEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<Integer, UserEvent> kafkaTemplate;
    private final String topicName;

    public KafkaProducer(KafkaTemplate<Integer, UserEvent> kafkaTemplate,
                         @Value("${kafka.topic.test-topic-name}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void send(UserEvent event) {
        int topicPartition = 1;
        int eventKey = event.getUserId();
        Long eventTimestamp = event.getEventTime().toEpochMilli();

        kafkaTemplate.send(topicName, topicPartition, eventTimestamp, eventKey, event)
                .addCallback(this::onSuccess, this::onFailure);
    }

    private void onSuccess(Object result) {
        logger.info("successfully sended. {} ", result);
    }

    private void onFailure(Throwable exception) {
        logger.info("failed successfully.", exception);
    }
}
