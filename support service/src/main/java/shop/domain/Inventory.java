package shop.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.*;
import lombok.Data;
import shop.SupportServiceApplication;
import shop.domain.InventoryDecreased;
import shop.domain.InventoryIncreased;
import java.math.BigDecimal;
import java.util.Optional;

@Entity
@Table(name = "Inventory_table")
@Data
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    // ✅ price 타입을 String -> BigDecimal로 변경
    private BigDecimal price; 

    private Integer qty;

    public static InventoryRepository repository() {
        return SupportServiceApplication.applicationContext.getBean(InventoryRepository.class);
    }

    public static void decreaseInventory(DeliveryStarted deliveryStarted) {
        // ✅ Order ID가 null이 아니고, 숫자로 변환 가능한지 확인
        Optional.ofNullable(deliveryStarted.getOrderId())
                .map(orderId -> {
                    try {
                        return Long.valueOf(orderId);
                    } catch (NumberFormatException e) {
                        return null;
                    }
                })
                .ifPresent(orderId -> repository().findById(orderId).ifPresent(inventory -> {
                    if (inventory.getQty() != null && inventory.getQty() > 0) {
                        inventory.setQty(inventory.getQty() - 1); // 수량 감소
                        repository().save(inventory);

                        InventoryDecreased inventoryDecreased = new InventoryDecreased(inventory);
                        inventoryDecreased.publishAfterCommit();
                    }
                }));
    }

    public static void increaseInventory(DeliveryCancelled deliveryCancelled) {
        Optional.ofNullable(deliveryCancelled.getOrderId())
                .map(orderId -> {
                    try {
                        return Long.valueOf(orderId);
                    } catch (NumberFormatException e) {
                        return null;
                    }
                })
                .ifPresent(orderId -> repository().findById(orderId).ifPresent(inventory -> {
                    if (inventory.getQty() != null) {
                        inventory.setQty(inventory.getQty() + 1); // 수량 증가
                        repository().save(inventory);

                        InventoryIncreased inventoryIncreased = new InventoryIncreased(inventory);
                        inventoryIncreased.publishAfterCommit();
                    }
                }));
    }
}
