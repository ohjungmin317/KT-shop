package shop.domain;

import javax.persistence.*;
import lombok.Data;
import shop.SupportServiceApplication;
import shop.domain.DeliveryCancelled;
import shop.domain.DeliveryStarted;

@Entity
@Table(name = "Delivery_table")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderId;
    private String address;
    private String customerId;
    private String productId;
    private Integer qty;
    private String status;

    public static DeliveryRepository repository() {
        return SupportServiceApplication.applicationContext.getBean(DeliveryRepository.class);
    }

    public static void startDelivery(OrderPlaced orderPlaced) {
        Delivery delivery = new Delivery();
        delivery.setOrderId(orderPlaced.getId().toString());
        delivery.setAddress(orderPlaced.getAddress());
        delivery.setCustomerId(orderPlaced.getCustomerId());
        delivery.setProductId(orderPlaced.getProductId());
        delivery.setQty(orderPlaced.getQty());
        delivery.setStatus("DELIVERY_STARTED");
        
        repository().save(delivery);
        
        DeliveryStarted deliveryStarted = new DeliveryStarted(delivery);
        deliveryStarted.publishAfterCommit();
    }

    public static void cancelDelivery(OrderCancelled orderCancelled) {
        repository().findById(Long.valueOf(orderCancelled.getProductId())).ifPresent(delivery -> {
            delivery.setStatus("DELIVERY_CANCELLED");
            repository().save(delivery);
            
            DeliveryCancelled deliveryCancelled = new DeliveryCancelled(delivery);
            deliveryCancelled.publishAfterCommit();
        });
    }
}
