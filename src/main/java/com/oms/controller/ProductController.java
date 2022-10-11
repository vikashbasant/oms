package com.oms.controller;

import com.oms.dto.ProductDTO;
import com.oms.dto.Response;
import com.oms.service.OSMService;
import com.oms.service.OSMServiceFactory;
import com.oms.service.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    OSMServiceFactory factory;

    @PostMapping("/addProduct")
    public ResponseEntity<Response> addProduct(@RequestBody ProductDTO productDTO, @RequestHeader HttpHeaders headers){
        OSMService service = factory.getService(ServiceType.ADD_PRODUCT);
        Response response = service.executeService(productDTO, headers);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/gatAllProducts")
    public ResponseEntity<Response> getAllProducts(@RequestHeader HttpHeaders headers){
        OSMService service = factory.getService(ServiceType.GET_ALL_PRODUCT);
        Response response = service.executeService("", headers);
        return ResponseEntity.ok(response);
    }
}
