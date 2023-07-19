package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.consumer.MessageConsumer;
import org.example.model.Shipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.messaging.converter.MessageConversionException;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

@SpringBootTest
@EmbeddedKafka (partitions = 1, bootstrapServersProperty = "spring.kafka.bootstrap-servers", brokerProperties = { "listeners=PLAINTEXT://localhost:9093", "port=9093" })
class KafkaIntegrationTest {

    @Autowired
    private MessageConsumer consumer;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Shipment> kafkaTemplate2;

    private ObjectMapper objectMapper = new ObjectMapper();
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @BeforeEach
    void setup() {
        consumer.resetLatch();
    }

    @Test
    public void testMessageReceived() throws Exception {

        String data = "Simple Message by KafkaProducer";
        String topic = "my-topic";
        Shipment shipment3 = new Shipment();
        shipment3.setShipmentDate("28 June 2023");
        shipment3.setShipmentVersion("v28");
        shipment3.setOriginLocation("Delhi");
        shipment3.setDestinationLocation("Chennai");
        kafkaTemplate2.send(topic, shipment3);

        consumer.getLatch().await(10, TimeUnit.SECONDS);
        String payload = "";

        try {
            payload = consumer.getPayload();
            System.out.println("payload:" +  payload);
        }
        catch (MessageConversionException me)
        {
            System.out.println("messageConversionException:" + me);

        }

        //assertNotNull(payload);
        assertThat(payload, containsString("Delhi"));


    }

}
