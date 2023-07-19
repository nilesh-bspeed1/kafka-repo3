package org.example.controller;

import org.example.dao.ShipmentRepository;
import org.example.model.Shipment;
import org.example.producer.MessageProducer;
import org.example.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ShipmentController {

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @GetMapping("/connect")
    public String connect() {
        return "Hello Gradle BNSF";
    }

    @PostMapping("/send2")
    public String sendMessage2(@RequestParam("message") String message) {
        return "Message sent2: " + message;
    }


    @PostMapping("/send")
    public String sendMessage(@RequestParam("message") String message) {
        messageProducer.sendMessage("my-topic", message);
        return "Message sent: " + message;
    }

    @PostMapping(value = "/send-ship",consumes = {"application/json"},produces = {"application/json"})
       public String sendShipment(@RequestBody Shipment shipment) {

        Shipment shipment3 = new Shipment();
        shipment3.setShipmentDate(shipment.getShipmentDate());
        shipment3.setShipmentVersion(shipment.getShipmentVersion());
        shipment3.setOriginLocation(shipment.getOriginLocation());
        shipment3.setDestinationLocation(shipment.getDestinationLocation());
        shipmentService.createShipment(shipment3);

        messageProducer.sendShipment("my-topic", shipment);
        return "Shipment is sent: " + shipment.getShipmentDate() + ":" + shipment.getShipmentVersion() + ":" + shipment.getOriginLocation() + ":" + shipment.getDestinationLocation();
    }
}