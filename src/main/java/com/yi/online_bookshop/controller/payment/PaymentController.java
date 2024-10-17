package com.yi.online_bookshop.controller.payment;

import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yi.online_bookshop.api_response.GeneralApiResponse;
import com.yi.online_bookshop.dto.payment.CreatePaymentDTO;
import com.yi.online_bookshop.service.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Operation(summary = "Create a payment", description = "Creates a new payemnt with the given details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Payment created successfully"),
        @ApiResponse(responseCode = "400", description = "Bad input dataset")
    })
    @PostMapping("/create")
    public ResponseEntity<GeneralApiResponse> createPayment(@Valid @RequestBody CreatePaymentDTO createPaymentDTO) {
        Map<String, String> responseData = Map.of(
                "account_number", createPaymentDTO.getAccountNumber()
        );
        try {
            paymentService.createPaymentByAccountNumber(createPaymentDTO);
        } catch (EmptyResultDataAccessException e) {
            GeneralApiResponse response = new GeneralApiResponse(
                    HttpStatus.BAD_REQUEST, "Account is not existing with given account number.", responseData
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        GeneralApiResponse response = new GeneralApiResponse(
                HttpStatus.CREATED, "Data created successfully.", responseData
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
