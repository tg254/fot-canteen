package com.hello.FirstApp.repos;


import com.hello.FirstApp.modals.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends CrudRepository<Payment, Integer> {
}