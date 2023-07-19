package org.example.producer;

import org.example.model.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Shipment> kafkaTemplate2;

    public void sendMessage(String topic, String message) {
        //kafkaTemplate.send(topic, message);
    }

    public void sendShipment(String topic, Shipment shipment) {
        Shipment shipment3 = new Shipment();
        shipment3.setShipmentDate(shipment.getShipmentDate());
        shipment3.setShipmentVersion(shipment.getShipmentVersion());
        shipment3.setOriginLocation(shipment.getOriginLocation());
        shipment3.setDestinationLocation(shipment.getDestinationLocation());
        kafkaTemplate2.send(topic, shipment3);

    }

}
