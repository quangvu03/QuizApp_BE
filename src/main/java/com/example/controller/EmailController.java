package com.example.controller;

import com.example.helper.EmailHelper;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import jakarta.inject.Inject;

@Controller("api/email")
public class EmailController {

    @Inject
    private final EmailHelper emailHelper;

    @Inject
    public EmailController(EmailHelper emailHelper) {
        this.emailHelper = emailHelper;
    }

    // API để gửi OTP qua email
    @Post(value = "/sendotp", consumes = MediaType.APPLICATION_JSON)
    public String sendOtp(@QueryValue("otp") int otp, @QueryValue("email") String email) {
        try {
            // Validate email và otp
            if (email == null || email.isEmpty()) {
                return "Error: Email address is required.";
            }

            // Tạo tiêu đề email
            String subject = "Your OTP Code for Verification";

            // Tạo nội dung email (không dùng template, viết trực tiếp)
            String body = "Hello,\n\n" +
                    "We received a request to verify your account. Your One-Time Password (OTP) is:\n\n" +
                    "🔒 " + otp+ " 🔒\n\n" +
                    "Please use this code to complete your verification. The code is valid for 10 minutes.\n" +
                    "If you did not request this, please ignore this email.\n\n" +
                    "Best regards,\n" +
                    "Your App Team";

            // Gửi email với OTP
            emailHelper.sendMail(email, subject, body);

            // Trả về thông báo thành công
            return "OTP sent successfully to " + email + ". Please check your inbox (and spam/junk folder).";
        } catch (Exception e) {
            // Trả về thông báo lỗi nếu gửi email thất bại
            return "Failed to send OTP: " + e.getMessage();
        }
    }
}