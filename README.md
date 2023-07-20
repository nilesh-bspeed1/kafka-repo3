Kafka Producer Consumer example
-------------------------------------
-------------------------------------
H2 DB Console
http://localhost:8091/h2-console/
SHIPMENT Table structure
SHIPMENT_ID, SHIPMENT_DATE, SHIPMENT_VERSION, ORIGIN_LOCATION, DESTINATION_LOCATION  
-----------------------------------
Start zookeeper-server
Start kafka-server
Start kafka-console-consumer
eg. kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic my-topic

Start Kafka-Producer on port 8091
Start Kafka-Consumer on port 8092
--------------------------------------
Test Kafka-Producer in Postman
POST http://localhost:8091/send-ship
Body raw
{
"shipmentDate": "30 June 2023",
"shipmentVersion": "v30",
"originLocation": "Pune30",
"destinationLocation": "Hyderabad30"
}
Response:
Shipment object is saved into H2 Database & sent to my-topic.

Test Kafka-Consumer
Shipment object is Received by Kafka-Consumer.
-----------------------------------------
IntegrationTests

\Kafka_Producer\src\test\java\org\example\controller\ShipmentControllerIntegrationTest
Test steps : Send Shipment object to Kafka_Producer.
Test result : Shipment object is saved into H2 Database & sent to my-topic.
Verified Shipment object values in H2 Database successfully.

\Kafka_Consumer\src\test\java\org\example\KafkaIntegrationTest
Test steps : Send Shipment object to my-topic.
Test result : Verified the Payload attributes successfully.

