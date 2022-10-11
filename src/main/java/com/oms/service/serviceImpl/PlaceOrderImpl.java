package com.oms.service.serviceImpl;

import com.oms.dto.OrderDTO;
import com.oms.dto.Response;
import com.oms.dto.ResponseOrderDTO;
import com.oms.entity.Customer;
import com.oms.entity.Order;
import com.oms.entity.Product;
import com.oms.entity.ShopingCart;
import com.oms.repository.CustomerRepository;
import com.oms.repository.OrderRepository;
import com.oms.repository.ProductRepository;
import com.oms.service.OSMService;
import com.oms.service.ServiceType;
import com.oms.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PlaceOrderImpl implements OSMService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public ServiceType getServiceType() {
        return ServiceType.ORDER_PLACE;
    }

    @Override
    public <T> Response executeService(T t, HttpHeaders headers) {
        OrderDTO orderDTO = (OrderDTO) t;

        float amount = getCartAmount(orderDTO.getCartItems());

        Customer customer = new Customer(orderDTO.getCustomerName(), orderDTO.getCustomerEmail());
        Integer customerIdFromDB = isCustomerPresent(customer);
        if(customerIdFromDB != null){
            customer.setId(customerIdFromDB);
        }else{
            customer = saveCustomer(customer);
        }

        Order order = new Order(orderDTO.getOrderDescription(), customer, orderDTO.getCartItems());
        order = saveOrder(order);

        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO();
        responseOrderDTO.setAmount(amount);
        responseOrderDTO.setDate(DateUtil.getCurrentDateTime());
        responseOrderDTO.setInvoiceNumber(new Random().nextInt(10000));
        responseOrderDTO.setOrderId(order.getId());
        responseOrderDTO.setOrderDescription(orderDTO.getOrderDescription());

        Response response = new Response();
        response.setStatus("Success");
        response.setMessage("Order Placed Successfully");
        response.setData(responseOrderDTO);
        return response;


    }

    public float getCartAmount(List<ShopingCart> shopingCartList){

        float totalCartAmount = 0f;
        float singleCartAmount = 0f;
        int availableQuantity = 0;

        for(ShopingCart cart : shopingCartList){
            int productId = cart.getProductId();
            Optional<Product> product = productRepository.findById(productId);

            if(product.isPresent()){
                Product pro = product.get();

                if(pro.getAvailableQuantity() < cart.getQuantity()){
                    singleCartAmount = pro.getPrice() * pro.getAvailableQuantity();
                    cart.setQuantity(pro.getAvailableQuantity());
                }else{
                    singleCartAmount = cart.getQuantity() * pro.getPrice();
                    availableQuantity = pro.getAvailableQuantity() - cart.getQuantity();
                }

                totalCartAmount += singleCartAmount;
                pro.setAvailableQuantity(availableQuantity);
                availableQuantity = 0;
                cart.setProductName(pro.getName());
                cart.setAmount(singleCartAmount);

                productRepository.save(pro);
            }

        }
        return totalCartAmount;
    }


    public Integer isCustomerPresent(Customer customer){
        Customer customer1 = customerRepository.getCustomerByEmailAndName(customer.getEmail(), customer.getName());
        return customer1 != null ? customer1.getId() : null;
    }

    public  Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }
}
