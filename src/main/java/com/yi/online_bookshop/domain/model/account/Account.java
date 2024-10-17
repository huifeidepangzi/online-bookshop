package com.yi.online_bookshop.domain.model.account;

import java.security.SecureRandom;
import java.text.DecimalFormat;

import com.yi.online_bookshop.domain.model.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "accounts")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    Account(User user) {
        this.user = user;
        this.accountNumber = this.generateAccountNumber();
    }

    private String generateAccountNumber() {
        SecureRandom random = new SecureRandom();

        // Generate a random number between 0 and 99999999 (inclusive)
        int randomNumber = random.nextInt(100000000); // Generates up to 8 digits

        // Format the number to ensure it has 8 digits (pad with leading zeros if necessary)
        DecimalFormat df = new DecimalFormat("00000000");
        String formattedNumber = df.format(randomNumber);

        // Concatenate "A-" with the formatted number
        return "A-" + formattedNumber;
    }
}
