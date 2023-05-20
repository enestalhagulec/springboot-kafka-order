package net.etg.orderservice.controller;

import lombok.AllArgsConstructor;
import net.etg.basedomains.dto.Order;
import net.etg.orderservice.kafka.KafkaOrderEventProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private final KafkaOrderEventProducer kafkaOrderEventProducer;

    @PostMapping("/place")
    public ResponseEntity<String> placeOrder(@RequestBody Order order){
        kafkaOrderEventProducer.sendMessage(order);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Order placed");
    }

}
