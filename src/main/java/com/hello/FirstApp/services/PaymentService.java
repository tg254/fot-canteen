package com.hello.FirstApp.services;

import com.hello.FirstApp.modals.Payment;
import com.hello.FirstApp.repos.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepo paymentRepo;

    public List<Payment> readAllPayments(){
        return (List<Payment>) paymentRepo.findAll();
    }

    public Optional<Payment> readPayment(Integer id){
        return paymentRepo.findById(id);
    }

    public boolean createPayment(Payment payment){
        paymentRepo.save(payment);
        return true;
    }

    public boolean updatePayment(Payment payment){
        createPayment(payment);
        return true;
    }

    public boolean deletePayment(Integer id){
        paymentRepo.deleteById(id);
        return true;
    }
}
