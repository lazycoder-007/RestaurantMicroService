package com.wynk.project.proxy;

import com.wynk.project.Models.DeliveryPerson;
import com.wynk.project.exceptions.ServiceConnectionError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DeliverServiceProxy {
    private static String HOST = "http://localhost:";
    private static String PORT = "8080";
    private static String CONNECTION_ERROR = "Unable to connect to Delivery Service";

    @Autowired
    RestTemplate restTemplate;

    public List<DeliveryPerson> getDeliverPersonList() {
        String deliveryPersonStatusURL = HOST + PORT + "/getStatus";
        List<DeliveryPerson> deliveryPersonList = new ArrayList<>();
        try {
            ResponseEntity<DeliveryPerson[]> responseEntity = restTemplate.exchange(deliveryPersonStatusURL,
                    HttpMethod.GET, HttpEntity.EMPTY, DeliveryPerson[].class);
            deliveryPersonList = Arrays.asList(responseEntity.getBody());
        } catch (Exception e) {
            throw new ServiceConnectionError(CONNECTION_ERROR);
        }
        return deliveryPersonList;
    }

    public void sendOrderToDeliveryPerson(Integer orderId, Integer deliveryPersonId) {
        String sendOrderURL = HOST + PORT + "/acceptOrder?orderId=" + orderId + "&deliveryPersonId=" + deliveryPersonId;
        try {
            restTemplate.getForObject(sendOrderURL, String.class);
        } catch (Exception e) {
            throw new ServiceConnectionError(CONNECTION_ERROR);
        }
    }
}
