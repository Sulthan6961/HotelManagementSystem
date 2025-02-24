package com.app.SignupDto;

import java.util.List;

import com.app.entity.PaymentDetails;
import com.app.entity.Signup;

public class PaymentDetailsDtO {
    private Signup signUp;
    private PaymentDetailsDtO paymentDetailsList; 
    private List<PaymentDetailsDtO> upcomingTrips;
    private List<PaymentDetailsDtO> pastTrips;


    // Default Constructor
    public PaymentDetailsDtO(PaymentDetails savedPaymentDetails) {}

 // Constructor for adding payment details 
    public PaymentDetailsDtO(Signup signUp, PaymentDetailsDtO paymentDetailsList) {
        this.signUp = signUp;
        this.paymentDetailsList = paymentDetailsList;
        this.upcomingTrips = null;
        this.pastTrips = null;
    }
    
 // Constructor for getting details 
    public PaymentDetailsDtO(Signup signUp, List<PaymentDetailsDtO> upcomingTrips, List<PaymentDetailsDtO> pastTrips) {
        this.signUp = signUp;
        this.paymentDetailsList = null;
        this.upcomingTrips = upcomingTrips;
        this.pastTrips = pastTrips;
    }

    // Getters and Setters
    public Signup getSignUp() {
        return signUp;
    }

    public void setSignUp(Signup signUp) {
        this.signUp = signUp;
    }

    public PaymentDetailsDtO getPaymentDetailsList() {
        return paymentDetailsList;
    }

    public void setPaymentDetailsList(PaymentDetailsDtO paymentDetailsList) {
        this.paymentDetailsList = paymentDetailsList;
    }

    public List<PaymentDetailsDtO> getUpcomingTrips() {
        return upcomingTrips;
    }

    public void setUpcomingTrips(List<PaymentDetailsDtO> upcomingTrips) {
        this.upcomingTrips = upcomingTrips;
    }

    public List<PaymentDetailsDtO> getPastTrips() {
        return pastTrips;
    }

    public void setPastTrips(List<PaymentDetailsDtO> pastTrips) {
        this.pastTrips = pastTrips;
    }
}