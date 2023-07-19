package org.example.service;

import org.example.dao.ShipmentRepository;
import org.example.model.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;

    // Business logic methods
    // create a new Shipment
    public void createShipment(Shipment shipment)
    {
        Shipment shipment3 = new Shipment();

        shipment3.setShipmentDate(shipment.getShipmentDate());
        shipment3.setShipmentVersion(shipment.getShipmentVersion());
        shipment3.setOriginLocation(shipment.getOriginLocation());
        shipment3.setDestinationLocation(shipment.getDestinationLocation());

        shipmentRepository.save(shipment3);
        System.out.println("Shipment is saved successfully in DB");
    }
}
