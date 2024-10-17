package com.yi.online_bookshop.dto.payment;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yi.online_bookshop.enums.PaymentType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatePaymentDTO {

    @NotEmpty(message = "Account number is required")
    private String accountNumber;

    @NotNull(message = "Payment type is required")
    private PaymentType paymentType;

    @NotNull(message = "Amount is required")
    @Min(0)
    @Max(100000)
    private BigDecimal amount;

    @NotNull(message = "Payment date is required in format YYYY-MM-DD")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;
}
