package example.kafka;

import example.kafka.consumer.KafkaConsumer;
import example.kafka.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class EventPublishingJob {

    private static final Logger logger = LoggerFactory.getLogger(EventPublishingJob.class);

    private final KafkaProducer kafkaProducer;
    private final SecureRandom random = new SecureRandom();

    public EventPublishingJob(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Scheduled(cron = "*/15 * * * * *")
    public void publishRandomEvent() {
        logger.info("Job started");

        UserEvent event = new UserEvent(random.nextInt(), "RealFirstName", "RealLastName");

        kafkaProducer.send(event);
    }
}
