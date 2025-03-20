package shop.domain;

import java.util.*;
import lombok.*;
import shop.domain.*;
import shop.infra.AbstractEvent;

@Data
@ToString
public class OrderCancelled extends AbstractEvent {

    private Long id;
    private String customerId;
    private String productId;
    private Integer price;
    private Integer qty;
    private String address;
}
