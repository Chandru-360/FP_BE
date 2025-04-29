package com.springboot.financialplanning.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

	@Autowired
    private JavaMailSender javaMailSender;

	private Map<String, String> otpMap = new HashMap<>();

    public String generateOTP(String email) {
        // Generate a 6-digit OTP
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000);
        String otp = String.valueOf(otpValue);
  
        otpMap.put(email, otp);
     
        System.out.println(otpValue);
        return otp;
    }

    public void sendOTPByEmail(String email, String otp) {
    	
        // Create a SimpleMailMessage instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Your OTP");
        mailMessage.setText("Your OTP is: " + otp);

        // Send email
        javaMailSender.send(mailMessage);
    }

    public String getOtpValue(String email) {
    	String otpValue = otpMap.get(email);
        return otpValue;
    }
   
}
