package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.SignupDto.PaymentDetailsDtO;
import com.app.entity.PaymentDetails;
import com.app.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
  
    @PostMapping("/add")
    public ResponseEntity<PaymentDetailsDtO> addPaymentDetails(
            @RequestParam String mobileNumber,  
            @RequestBody PaymentDetails paymentDetails){

        PaymentDetailsDtO responseDtO = paymentService.addPaymentDetails(mobileNumber, paymentDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDtO);
    }



    @GetMapping("/{id}")
    public ResponseEntity<PaymentDetails> getPaymentDetailsById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentDetailsById(id));
    }

    @GetMapping("/by-mobile/{mobileNo}")
    public ResponseEntity<PaymentDetailsDtO> getPaymentDetailsByMobile(@PathVariable String mobileNo) {
        return ResponseEntity.ok(paymentService.getPaymentDetailsByMobile(mobileNo));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PaymentDetails> updatePaymentDetails(
            @PathVariable Long id, 
            @RequestBody PaymentDetails updatedDetails) {
        return ResponseEntity.ok(paymentService.updatePaymentDetails(id, updatedDetails));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePaymentDetails(@PathVariable Long id) {
        paymentService.deletePaymentDetails(id);
        return ResponseEntity.ok("Payment details with ID " + id + " deleted successfully.");
    }
}
