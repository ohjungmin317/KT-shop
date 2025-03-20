package shop.domain;

import java.math.BigDecimal;

import javax.persistence.*;
import lombok.Data;
import shop.OrderServiceApplication;
import shop.domain.OrderCancelled;
import shop.domain.OrderPlaced;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String customerId;
    private String productId;
    private BigDecimal price;
    private Integer qty;
    private String address;
    private String status;

    @PostPersist
    public void onPostPersist() {
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
    }

    @PostRemove
    public void onPostRemove() {
        OrderCancelled orderCancelled = new OrderCancelled(this);
        orderCancelled.publishAfterCommit();
    }

    public static OrderRepository repository() {
        return OrderServiceApplication.applicationContext.getBean(OrderRepository.class);
    }

    public static void sendMail(InventoryIncreased inventoryIncreased) {
        repository().findById(inventoryIncreased.getId()).ifPresent(order -> {
            order.setStatus("ORDER_CANCELLED");
            repository().save(order);
        });
    }

    public static void updateStatus(DeliveryStarted deliveryStarted) {
        repository().findById(Long.valueOf(deliveryStarted.getOrderId())).ifPresent(order -> {
            order.setStatus("DELIVERY_STARTED");
            repository().save(order);
        });
    }

    public static void updateStatus(DeliveryCancelled deliveryCancelled) {
        repository().findById(Long.valueOf(deliveryCancelled.getOrderId())).ifPresent(order -> {
            order.setStatus("DELIVERY_CANCELLED");
            repository().save(order);
        });
    }
}