package shop.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import shop.config.kafka.KafkaProcessor;
import shop.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DeliveryCancelled'"
    )
    public void wheneverDeliveryCancelled_SendSms(
        @Payload DeliveryCancelled deliveryCancelled
    ) {
        DeliveryCancelled event = deliveryCancelled;
        System.out.println(
            "\n\n##### listener SendSms : " + deliveryCancelled + "\n\n"
        );
        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DeliveryStarted'"
    )
    public void wheneverDeliveryStarted_SendSms(
        @Payload DeliveryStarted deliveryStarted
    ) {
        DeliveryStarted event = deliveryStarted;
        System.out.println(
            "\n\n##### listener SendSms : " + deliveryStarted + "\n\n"
        );
        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderPlaced'"
    )
    public void wheneverOrderPlaced_SendSms(@Payload OrderPlaced orderPlaced) {
        OrderPlaced event = orderPlaced;
        System.out.println(
            "\n\n##### listener SendSms : " + orderPlaced + "\n\n"
        );
        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderCancelled'"
    )
    public void wheneverOrderCancelled_SendSms(
        @Payload OrderCancelled orderCancelled
    ) {
        OrderCancelled event = orderCancelled;
        System.out.println(
            "\n\n##### listener SendSms : " + orderCancelled + "\n\n"
        );
        // Sample Logic //

    }
}
//>>> Clean Arch / Inbound Adaptor
