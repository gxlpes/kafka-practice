package com.gxlpes.ecommerce.producers;

import com.gxlpes.ecommerce.kafka.KafkaDispatcher;
import com.gxlpes.ecommerce.models.Email;
import com.gxlpes.ecommerce.models.Order;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        try (var orderDispatcher = new KafkaDispatcher<Order>(); var emailDispatcher = new KafkaDispatcher<Email>()) {

            for (var i = 0; i < 10; i++) {
                var userId = UUID.randomUUID().toString();
                var orderId = UUID.randomUUID().toString();
                var amount = (Math.random() * 5000 + 1);

                var order = new Order(userId, orderId, amount);
                orderDispatcher.send("ECOMMERCE_NEW_ORDER", userId, order);

                var email = new Email("abc@email.com", "Thank you for your order! We are processing your order!");
                emailDispatcher.send("ECOMMERCE_SEND_EMAIL", userId, email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
