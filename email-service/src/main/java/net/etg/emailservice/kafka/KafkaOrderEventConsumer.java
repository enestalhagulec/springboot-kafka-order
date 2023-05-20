package net.etg.emailservice.kafka;

import net.etg.basedomains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderEventConsumer {

    private final static Logger LOGGER = LoggerFactory.getLogger(KafkaOrderEventConsumer.class);
    @KafkaListener(topics = "${spring.kafka.topic.name}",
    groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent){
        LOGGER.info("order event -> " + orderEvent.toString());
    }
}
