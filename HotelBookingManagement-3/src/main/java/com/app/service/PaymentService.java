package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.SignupDto.PaymentDetailsDtO;
import com.app.entity.PaymentDetails;
import com.app.entity.Signup;
import com.app.repository.PaymentRepository;
import com.app.repository.SignupRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private SignupRepository signUpRepository;

	private Signup SignUpDto;
                   
    public PaymentDetailsDtO addPaymentDetails(String mobileNumber, PaymentDetails paymentDetails) {
        // Find the user (Signup) by mobile number
        Signup signUp = ((Optional<Signup>) SignupRepository.findByMobileNo(mobileNumber))
                .orElseThrow(() -> new RuntimeException("User with mobile number " + mobileNumber + " not found"));

        // Associate the payment details with the user
        paymentDetails.setSignUp(signUp);

        // Save payment details
        PaymentDetails savedPaymentDetails = paymentRepository.save(paymentDetails);

        // Return a DTO with the signup and payment details
        return new PaymentDetailsDtO(savedPaymentDetails);
    }
  
    
    // Get payment details by mobile number
    public PaymentDetailsDtO getPaymentDetailsByMobile(String mobileNo) {
        Signup signUp = ((Optional<Signup>) SignupRepository.findByMobileNo(mobileNo))
                                        .orElseThrow(() -> new RuntimeException("User with mobile number " + mobileNo + " not found"));


        // Fetch all payment details associated with the user
        List<PaymentDetails> allTrips = paymentRepository.findBySignUp(SignUpDto);

        // Separate upcoming and past trips
        List<PaymentDetailsDtO> upcomingTrips = allTrips.stream()
                .filter(trip -> trip.getCheckIn().isAfter(LocalDate.now()))
                .map(PaymentDetailsDtO::new)
                .collect(Collectors.toList());

        List<PaymentDetailsDtO> pastTrips = allTrips.stream()
                .filter(trip -> trip.getCheckOut().isBefore(LocalDate.now()))
                .map(PaymentDetailsDtO::new)
                .collect(Collectors.toList());

        // Return the DTO with user, upcoming, and past payment details
        return new PaymentDetailsDtO(signUp, upcomingTrips, pastTrips);

    }
    // Update existing payment details
    public PaymentDetails updatePaymentDetails(Long id, PaymentDetails updatedPaymentDetails) {
        // Find the payment details by ID
        PaymentDetails existingPaymentDetails = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment details not found for id " + id));

        // Update fields of existing payment details with the new data
        existingPaymentDetails.setHotelName(updatedPaymentDetails.getHotelName());
        existingPaymentDetails.setAddress(updatedPaymentDetails.getAddress());
        existingPaymentDetails.setCheckIn(updatedPaymentDetails.getCheckIn());
        existingPaymentDetails.setCheckOut(updatedPaymentDetails.getCheckOut());
        existingPaymentDetails.setPrice(updatedPaymentDetails.getPrice());
        existingPaymentDetails.setPayment(updatedPaymentDetails.getPayment());
        existingPaymentDetails.setHotelImg(updatedPaymentDetails.getHotelImg());

        // Save the updated payment details
        return paymentRepository.save(existingPaymentDetails);
    }

    // Delete payment details by ID
    public void deletePaymentDetails(Long id) {
        // Find the payment details by ID
        PaymentDetails paymentDetails = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment details not found for id " + id));

        // Delete the payment details
        paymentRepository.delete(paymentDetails);
    }

    // Get payment details by ID
    public PaymentDetails getPaymentDetailsById(Long id) {
        // Find payment details by ID
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment details not found for id " + id));
    }
}
