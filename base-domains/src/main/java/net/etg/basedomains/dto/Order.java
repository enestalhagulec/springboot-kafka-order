package net.etg.basedomains.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Order {

    private String orderId;
    private String name;
    private int quantity;
    private BigDecimal price;

}
