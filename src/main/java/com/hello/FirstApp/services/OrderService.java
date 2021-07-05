package com.hello.FirstApp.services;

import com.hello.FirstApp.modals.Order;
import com.hello.FirstApp.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    public List<Order> readAllOrders(){
        return (List<Order>) orderRepo.findAll();
    }

    public Optional<Order> readOrder(Integer id){
        return orderRepo.findById(id);
    }

    public boolean createOrder(Order item){
        orderRepo.save(item);
        return true;
    }

    public boolean updateOrder(Order item){
        createOrder(item);
        return true;
    }

    public boolean deleteOrder(Integer id){
        orderRepo.deleteById(id);
        return true;
    }
}
