package com.vagas.gestao.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity(name = "company")
@Data
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Pattern(regexp="\\S+", message = "O campo username nao pode conter espaco!")
    private String password;

    @NotBlank(message = "O campo username nao pode ser vazio!")
    private String username;

    @Email(message = "O campo deve conter um email válido")
    private String email;

    private String website;
    private String name;
    private String description;
    
    private LocalDateTime createdAt;
}
