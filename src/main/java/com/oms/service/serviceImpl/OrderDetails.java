package com.oms.service.serviceImpl;

import com.oms.dto.GetOrderIdDTO;
import com.oms.dto.Response;
import com.oms.entity.Order;
import com.oms.repository.OrderRepository;
import com.oms.service.OSMService;
import com.oms.service.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetails implements OSMService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public ServiceType getServiceType() {
        return ServiceType.GET_ORDER_DETAILS;
    }

    @Override
    public <T> Response executeService(T t, HttpHeaders headers) {

        GetOrderIdDTO orderDTO = (GetOrderIdDTO) t;

        Optional<Order> byId = orderRepository.findById(orderDTO.getId());

        Response response = new Response();
        if(byId.isPresent()){
            response.setData(byId.get());
        }else{
            response.setData(null);
        }
        response.setStatus("Success");
        response.setMessage("Fetch Order Details Successfully");
        return response;

    }
}
