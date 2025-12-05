# Simple Consumer

A laboratory for studying:

- Basic Kafka configuration
- Message consumption using **Spring Kafka**

Two Docker Compose setups are available:

- **docker-compose.yml**: single-consumer environment
- **docker-compose.multiconsumer.yml**: multi-consumer environment (three consumers in the same group)

To send test messages, use:

```
scripts/test/run-prod-messages.sh
```

The script provides two test cases:

- single-consumer: targets the environment from **docker-compose.yml**
- multi-consumer: targets the environment from **docker-compose.multiconsumer.yml** and sends more messages to observe distribution among multiple consumers

Without a Spring producer — consumption only.
