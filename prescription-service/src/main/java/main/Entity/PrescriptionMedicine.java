package main.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="prescription_medicine_table")
@Data
public class PrescriptionMedicine {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	
	private String medicineName;
	
	private String  dosage;
	
	private String frequency;
	
	private Integer durationDays;

    private String instructions;
    
    @NotNull(message="must be medicinefees")
    private Double medicineFees;
    
    
    
   
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="prescription_id")
    @JsonBackReference
    private Prescription prescription;

}
