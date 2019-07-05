package com.deon.iqconsumer;

import com.deon.iqconsumer.service.ConsumerService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestConsumerService {

    @Test
    void EmptyHostParamConstr() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            ConsumerService consumerService  = new ConsumerService ("");
        });
        assertEquals("Host cannot be empty", e.getMessage());
    }

    @Test
    void EmptyStringParamSend() {
        ConsumerService consumerService = new ConsumerService("localhost");

        Exception e = assertThrows(IllegalArgumentException.class, () -> consumerService.listen(""));
        assertEquals("queue cannot be empty", e.getMessage());
    }

    @Test
    void successfulConstr() {
        ConsumerService  consumerService  = new ConsumerService ("localhost");

        assertEquals("localhost", consumerService .getHost());
    }

}
