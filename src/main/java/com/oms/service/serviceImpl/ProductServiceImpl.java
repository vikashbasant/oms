package com.oms.service.serviceImpl;

import com.oms.dto.Response;
import com.oms.entity.Product;
import com.oms.repository.ProductRepository;
import com.oms.service.OSMService;
import com.oms.service.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements OSMService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ServiceType getServiceType() {
        return ServiceType.GET_ALL_PRODUCT;
    }

    @Override
    public <T> Response executeService(T t, HttpHeaders headers) {
        List<Product> allProducts = productRepository.findAll();

        Response response = new Response();
        response.setStatus("Success");
        response.setMessage("Fetch All Product From DataBase");
        response.setData(allProducts);
        return response;
    }
}
