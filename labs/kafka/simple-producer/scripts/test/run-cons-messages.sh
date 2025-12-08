#!/bin/bash

sudo echo "----------------------------------"

TOPIC_NAME=$1

if [[ -z "$TOPIC_NAME" ]]; then
    echo "Usage: $0 <topic-name>"
    exit 1
fi

# ==========================
# Reset consumer group
# ==========================

sudo docker exec -ti "sp-kafka-server" bash -c "
		kafka-consumer-groups --bootstrap-server sp-kafka-server:9092 --group test-cons --topic \"$TOPIC_NAME\" \
		--reset-offsets --to-earliest --execute"

sudo echo ""

# ==========================
# Message consumer
# ==========================
sudo docker exec -ti "sp-kafka-server" bash -c "
		kafka-console-consumer --bootstrap-server sp-kafka-server:9092 --topic \"$TOPIC_NAME\" --group test-cons --from-beginning"
