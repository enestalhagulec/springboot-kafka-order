package net.etg.orderservice.kafka;

import lombok.AllArgsConstructor;
import net.etg.basedomains.dto.Order;
import net.etg.basedomains.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class KafkaOrderEventProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaOrderEventProducer.class);

    private NewTopic topic;
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendMessage(Order order){
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setMessage("Order is placed");
        orderEvent.setStatus("PENDING");
        orderEvent.setOrder(order);
        LOGGER.info(String.format("Order event -> %s",orderEvent.toString()));
        Message<OrderEvent> message = MessageBuilder
                .withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC,topic.name())
                .build();
        kafkaTemplate.send(message);
    }



}
