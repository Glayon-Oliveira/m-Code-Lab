#!/bin/bash

TOPIC_NAME=$1

if [[ -z "$TOPIC_NAME" ]]; then
    echo "Uso: $0 <nome-do-topico>"
    exit 1
fi

for i in {1..5}; do
    sudo docker exec -ti sc-kafka-server bash -c "
        echo \"Message n$i\" | kafka-console-producer --bootstrap-server sc-kafka-server:9092 --topic \"$TOPIC_NAME\"
    "
done
