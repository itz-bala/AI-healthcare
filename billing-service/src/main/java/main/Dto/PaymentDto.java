package main.Dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import main.Entity.PaymentMethod;

@Data
public class PaymentDto {
	@NotNull(message="paid amount is required")
	@DecimalMin(value="0.0",inclusive=true,message="payment status must be required")
    private Double paidAmount;

     @NotNull(message="payment method is required")
    private PaymentMethod paymentMethod;

}
