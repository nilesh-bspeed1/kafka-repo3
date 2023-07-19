package org.example.controller;

import org.example.ProducerAppMain;
import org.example.dao.ShipmentRepository;
import org.example.model.Shipment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ProducerAppMain.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ShipmentControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Test
    public void testCreateShipment() {
        Shipment shipment4 = new Shipment();
        shipment4.setShipmentId(5);
        shipment4.setShipmentDate("27 June 2023");
        shipment4.setShipmentVersion("v4");
        shipment4.setOriginLocation("Pune");
        shipment4.setDestinationLocation("Hyderabad");
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/send-ship", shipment4, String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());

    }

    @Test
    public void testShipmentDestinationLocation()
    {
        Shipment shipment2 = (Shipment) shipmentRepository.findById(1).orElse(null);;
        assertEquals("Hyderabad", shipment2.getDestinationLocation());
    }

    @Test
    public void testShipmentVersion()
    {
        Shipment shipment2 = (Shipment) shipmentRepository.findById(1).orElse(null);;
        assertEquals("v4", shipment2.getShipmentVersion());
    }
}
