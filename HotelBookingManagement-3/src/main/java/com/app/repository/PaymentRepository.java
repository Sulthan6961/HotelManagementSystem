package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.PaymentDetails;
import com.app.entity.Signup;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDetails, Long> {
    List<PaymentDetails> findBySignUp(Signup signUp);

	List<PaymentDetails> findBySignUp(PaymentDetails signUp);
}
