#!/bin/bash

topics=(
  quotation-freight.calculated-freight
  quotation-freight.new-freight
)

for topicName in "${topics[@]}";
do
  echo '\n trying create topic: '$topicName;
  docker exec quotation-freight-broker kafka-topics --create --topic "$topicName" --bootstrap-server broker:9092 --replication-factor 1 --partitions 1 --if-not-exists ;
  echo '\n Done creating topic: '$topicName;
done