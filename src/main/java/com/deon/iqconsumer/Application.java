package com.deon.iqconsumer;

import com.deon.iqconsumer.service.ConsumerService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Application {


    public static void main(String[] args) {
        ConsumerService consumerService = new ConsumerService("localhost");

                    consumerService.listen("IQQueue");
    }

}
