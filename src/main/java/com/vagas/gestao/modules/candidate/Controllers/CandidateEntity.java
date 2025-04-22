package com.vagas.gestao.modules.candidate.Controllers;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
@Entity(name = "candidates")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Pattern(regexp="\\S+", message = "O campo username nao pode conter espaco!")
    @NotBlank(message = "O campo username nao pode ser vazio!")
    private String username;
    @Email(message = "O campo deve conter um email válido")
    private String email;
    @Length(min=10, max=100, message = "O campo deve conter entre 10 e 20 caracteres")
    private String passoword;
    private String descripiton;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
 
    
}
