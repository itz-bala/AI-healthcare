package main.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import main.Entity.Billing;
import main.Entity.PaymentStatus;

public interface BillingRepository extends JpaRepository<Billing,Long>{
	
	
	List<Billing>findByBillingDateBetween(LocalDateTime start,LocalDateTime end);
	
	long countByPaymentStatus(PaymentStatus paymentStatus);
	
	List<Billing>findByPatientId(Long patientId);

}
