package org.ebank.digitalbank.dtos;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
public class CustomerDTO {
    private Long id;
    private String prenom;
    private String nom;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date naissance;
}
