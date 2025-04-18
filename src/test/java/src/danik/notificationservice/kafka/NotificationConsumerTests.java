package src.danik.notificationservice.kafka;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import src.danik.notificationservice.kafka.event.NotificationEvent;
import src.danik.notificationservice.types.NotificationType;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}",
        "spring.kafka.listener.auto-startup=false",
        "spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer",
        "spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer",
        "spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer",
        "spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer",
        "kafka.topic.notification=notifications"
})
@EmbeddedKafka(partitions = 1, topics = {"notifications"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class NotificationConsumerTests {

    @Autowired
    EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    KafkaTemplate<String, NotificationEvent> kafkaTemplate;

    private Consumer<String, NotificationEvent> consumer;

    @BeforeEach
    void setUp() {
        String groupId = "test-group-" + UUID.randomUUID();
        Map<String, Object> props = KafkaTestUtils.consumerProps(groupId, "false", embeddedKafkaBroker);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        consumer = new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new JsonDeserializer<>(NotificationEvent.class, false)
        ).createConsumer();

        embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumer, "notifications");
    }

    @AfterEach
    void tearDown() {
        if (consumer != null) {
            consumer.close();
        }
    }

    @Test
    void testSendingNotification() {
        NotificationEvent ev = NotificationEvent.builder()
                .eventId(1L)
                .notificationType(NotificationType.NEW_COMMENT_LIKE)
                .text("Test")
                .userId(2L)
                .build();
        kafkaTemplate.send("notifications", ev);
        kafkaTemplate.flush();

        ConsumerRecord<String, NotificationEvent> rec =
                KafkaTestUtils.getSingleRecord(consumer, "notifications", Duration.ofSeconds(15));

        assertThat(rec).isNotNull();
        assertThat(rec.value()).isEqualTo(ev);
    }
}
