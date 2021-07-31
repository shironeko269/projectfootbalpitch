package com.edu.fud.projectfootbalpitch.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    //profile
    @RequestMapping("/profile")
    public String openProfile(Principal principal){
        String username = principal.getName();
        return "user/profile";
    }
    @RequestMapping("/edit-profile")
    public String openUpdateProfile(){
        return "user/updateProfile";
    }
    @RequestMapping("/change-password")
    public String openChangePassword(){
        return "user/changePass";
    }
    //cart
    @RequestMapping("/cart")
    public String openCart(){
        return "user/cart";
    }
    //invoice
    @RequestMapping("/history-invoice")
    public String openHistoryInvoice(){
        return "user/historyInvoice";
    }
    @RequestMapping("/history-invoice-detail")
    public String openHistoryInvoiceDetail(){
        return "user/historyInvoiceDetail";
    }
    @RequestMapping("/history-invoice-booking")
    public String openHistoryInvoiceBooking(){
        return "user/historyInvoiceBooking";
    }
    @RequestMapping("/history-invoice-booking-service")
    public String openHistoryInvoiceBookingService(){
        return "user/historyInvoiceDetailService";
    }
    //checkout
    @RequestMapping("/checkout")
    public String openCheckOut(){
        return "user/ProceedToCheckout";
    }
}
