package org.example.model;

import lombok.Builder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Shipment implements Serializable
        {
            public Integer getShipmentId() {
                return shipmentId;
            }

            public void setShipmentId(Integer shipmentId) {
                this.shipmentId = shipmentId;
            }

            @Id
            @GeneratedValue(strategy = GenerationType.AUTO)
            // uuid if Primary key is String
            /*@GeneratedValue(generator = "uuid")
            @GenericGenerator(name = "uuid", strategy = "uuid2")*/
            private Integer shipmentId;
    private String shipmentDate;
    private String shipmentVersion;
    private String originLocation;
    private String destinationLocation;

    private static final long serialVersionUID = 1744050117179344127L;

    public String getShipmentVersion() {
        return shipmentVersion;
    }

    public void setShipmentVersion(String shipmentVersion) {
        this.shipmentVersion = shipmentVersion;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getOriginLocation() {
        return originLocation;
    }

    public void setOriginLocation(String originLocation) {
        this.originLocation = originLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }


}
