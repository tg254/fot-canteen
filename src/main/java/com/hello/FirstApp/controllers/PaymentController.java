package com.hello.FirstApp.controllers;

import com.hello.FirstApp.modals.Payment;
import com.hello.FirstApp.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/player/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Payment> getAllPayments(){
        return paymentService.readAllPayments();
    }

    @GetMapping(path ="{id}")
    public Optional<Payment> getPayment(@PathVariable("id") Integer id){
        return paymentService.readPayment(id);
    }

    @PostMapping
    public void postPayment(@NotNull @RequestBody Payment payment){
        paymentService.createPayment(payment);
    }

    @PutMapping(path = "{id}")
    public void putPayment(@PathVariable("id") Integer id, @NotNull @RequestBody Payment payment){
        payment.setTokenid(id);
        paymentService.updatePayment(payment);
    }

    @DeleteMapping(path = "{id}")
    public void deletePayment(@PathVariable("id") Integer id){
        paymentService.deletePayment(id);
    }
}
