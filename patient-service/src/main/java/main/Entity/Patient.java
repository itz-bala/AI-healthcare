package main.Entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name="patient")
@Data
public class Patient {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="name must be")
	private String name;
	
	@Email(message="invalid email")
	private String email;
	
	
	
	private String phonenum;
	
	private String gender;
	
	private Integer age;

	
	
	

    private Boolean firstVisit;
     
    @CreationTimestamp
    private LocalDate registrationDate;
    
    private boolean active=true;

}
