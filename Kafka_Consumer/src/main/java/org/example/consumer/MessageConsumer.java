package org.example.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.model.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class MessageConsumer {

    private String payload;
    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "my-topic", groupId = "my-group-id")
    public void listen(String message)
    {
        System.out.println("Received message: " + message);
        payload = message;
        latch.countDown();
    }

    @KafkaListener(topics = "my-topic", groupId = "my-group-id")
    @KafkaHandler(isDefault = true)
    public void listen2(Shipment shipment)
    {
        System.out.println("Received shipment2: " + shipment.toString());
        payload = shipment.toString();
        latch.countDown();
    }

    /*@KafkaListener(topics = "my-topic", groupId = "my-group-id")
    public void receive(ConsumerRecord<?, ?> consumerRecord, Shipment shipment)
    {
        LOGGER.info("Received shipment:", shipment);
        LOGGER.info("Received consumerRecord:", consumerRecord.toString());
        payload = consumerRecord.toString();
        latch.countDown();
    }*/

    public CountDownLatch getLatch() {
        return latch;
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

    public String getPayload() {
        return payload;
    }

}