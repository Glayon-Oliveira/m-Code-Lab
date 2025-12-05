#!/bin/bash

sudo echo ""

CASE_NAME=$1
TOPIC_NAME=$2

if [[ -z "$CASE_NAME" || -z "$TOPIC_NAME" ]]; then
    echo "Usage: $0 <single-consumer|multi-consumer> <topic-name>"
    exit 1
fi

# ==========================
# Test profiles
# ==========================
case "$CASE_NAME" in
    single-consumer)
        CONTAINER_NAME="sc-kafka-server"
        MESSAGE_COUNT=10
        ;;
    multi-consumer)
        CONTAINER_NAME="msc-kafka-server"
        MESSAGE_COUNT=15
        ;;
    *)
        echo "Invalid case: $CASE_NAME"
        echo "Use: single-consumer or multi-consumer"
        exit 1
        ;;
esac

echo "Running test case: $CASE_NAME"
echo "Container: $CONTAINER_NAME"
echo "Messages: $MESSAGE_COUNT"
echo "Topic: $TOPIC_NAME"
echo "----------------------------------"

# ==========================
# Message loop
# ==========================
for i in $(seq 1 $MESSAGE_COUNT); do
    sudo docker exec -ti "$CONTAINER_NAME" bash -c "
        echo \"Message n$i\" | kafka-console-producer --bootstrap-server ${CONTAINER_NAME}:9092 --topic \"$TOPIC_NAME\"
    "
done
