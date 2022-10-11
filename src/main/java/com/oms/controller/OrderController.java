package com.oms.controller;

import com.oms.dto.GetOrderIdDTO;
import com.oms.dto.OrderDTO;
import com.oms.dto.Response;
import com.oms.service.OSMService;
import com.oms.service.OSMServiceFactory;
import com.oms.service.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OSMServiceFactory factory;

    @PostMapping("/placeOrder")
    public ResponseEntity<Response> OrderPlace(@RequestBody OrderDTO orderDTO, @RequestHeader HttpHeaders headers){
        OSMService service = factory.getService(ServiceType.ORDER_PLACE);
        Response response = service.executeService(orderDTO, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getOrderDetails")
    public ResponseEntity<Response> getOrderDetails(@RequestBody GetOrderIdDTO getOrderIdDTO, @RequestHeader HttpHeaders headers){
        OSMService service = factory.getService(ServiceType.GET_ORDER_DETAILS);
        Response response = service.executeService(getOrderIdDTO, headers);
        return ResponseEntity.ok(response);
    }



}
