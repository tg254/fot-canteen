package com.hello.FirstApp.controllers;

import com.hello.FirstApp.modals.Order;
import com.hello.FirstApp.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrder(){
        return orderService.readAllOrders();
    }

    @GetMapping(path ="{id}")
    public Optional<Order> getOrder(@PathVariable("id") Integer id){
        return orderService.readOrder(id);
    }

    @PostMapping
    public void postOrder(@NotNull @RequestBody Order item){
        orderService.createOrder(item);
    }

    @PutMapping(path = "{id}")
    public void putOrder(@PathVariable("id") Integer id, @NotNull @RequestBody Order item){
        item.setId(id);
        orderService.updateOrder(item);
    }

    @DeleteMapping(path = "{id}")
    public void deleteOrder(@PathVariable("id") Integer id){
        orderService.deleteOrder(id);
    }
}
