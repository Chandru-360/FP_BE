package com.springboot.financialplanning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.financialplanning.dto.OtpDto;
import com.springboot.financialplanning.service.OtpService;

@RestController
public class OtpController {
	
	@Autowired
    private OtpService otpService;
	
	
	 @PostMapping("/generate-otp")
	    public String generateOTP(@RequestBody OtpDto otpDto) {
	        // Set the email address for the OTP DTO
	        String email = otpDto.getEmail();

	        // Generate OTP
	        String otp = otpService.generateOTP(email);

	        // Send OTP via email
	        otpService.sendOTPByEmail(email, otp);
	        return "OTP generated and sent successfully.";
	    }

	    @PostMapping("/verify-otp")
	    public String verifyOTP(@RequestBody OtpDto otpDto) {
	        // Get the OTP value from the service
	        String otpValue = otpService.getOtpValue(otpDto.getEmail());
	        // Verify if the provided OTP matches the generated OTP
	        if (otpValue != null && otpValue.equals(otpDto.getOtp())) {
	            return "OTP verified successfully.";
	        } else {
	            return "Invalid OTP. Please try again.";
	        }
	    }
}
