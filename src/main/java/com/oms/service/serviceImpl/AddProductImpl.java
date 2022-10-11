package com.oms.service.serviceImpl;

import com.oms.dto.ProductDTO;
import com.oms.dto.Response;
import com.oms.entity.Product;
import com.oms.repository.ProductRepository;
import com.oms.service.OSMService;
import com.oms.service.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class AddProductImpl implements OSMService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ServiceType getServiceType() {
        return ServiceType.ADD_PRODUCT;
    }

    @Override
    public <T> Response executeService(T t, HttpHeaders headers) {
        ProductDTO productDTO = (ProductDTO) t;

        Product pro = new Product();
        pro.setName(productDTO.getName());
        pro.setAvailableQuantity(productDTO.getAvailableQuantity());
        pro.setPrice(productDTO.getPrice());

        Product product = productRepository.save(pro);

        Response response = new Response();
        response.setStatus("Success");
        response.setMessage("Product Add Successfully");
        response.setData(product);
        return response;
    }
}
