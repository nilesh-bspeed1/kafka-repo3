Kafka Producer Consumer example
-------------------------------------
H2 DB Console
http://localhost:8091/h2-console/
SHIPMENT Table structure
SHIPMENT_ID, SHIPMENT_DATE, SHIPMENT_VERSION, DESTINATION_LOCATION, ORIGIN_LOCATION 

Start zookeeper-server
Start kafka-server
Start kafka-console-consumer
eg. kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic my-topic

Start Kafka-Producer on port 8091
Start Kafka-Consumer on port 8092

Test in Postman
POST http://localhost:8091/send-ship
Body raw
{
"shipmentDate": "30 June 2023",
"shipmentVersion": "v30",
"originLocation": "Pune30",
"destinationLocation": "Hyderabad30"
}