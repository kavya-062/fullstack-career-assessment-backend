package com.career.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// Added import
import jakarta.persistence.Transient;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String education;

    // NEW FIELD (for MySQL login tracking)
    @Column(name="login_time")
    private LocalDateTime loginTime;

    // OTP field (not stored in DB)
    @Transient
    private String otp;

    public Student(){}

    public Long getId(){ return id; }

    public String getName(){ return name; }

    public void setName(String name){ this.name = name; }

    public String getEmail(){ return email; }

    public void setEmail(String email){ this.email = email; }

    public String getPassword(){ return password; }

    public void setPassword(String password){ this.password = password; }

    public String getEducation(){ return education; }

    public void setEducation(String education){ this.education = education; }

    // NEW getter setter
    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    // OTP getter setter
    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}