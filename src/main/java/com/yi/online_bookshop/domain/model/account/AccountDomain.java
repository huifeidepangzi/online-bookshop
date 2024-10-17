package com.yi.online_bookshop.domain.model.account;

import java.security.SecureRandom;
import java.text.DecimalFormat;

import com.yi.online_bookshop.domain.model.user.User;

import lombok.Data;

@Data
public class AccountDomain {

    private Long id;

    private User user;

    private String accountNumber;

    AccountDomain(User user, String accountNumber) {
        this.user = user;
        this.accountNumber = accountNumber;
    }

    AccountDomain(User user) {
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
