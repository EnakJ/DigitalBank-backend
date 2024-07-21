package org.ebank.digitalbank.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prenom;
    private String nom;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date naissance;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<AccountBank> account;
}
