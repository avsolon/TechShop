package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String clientName;
    @Column(nullable = false)
    private String clientSurname;
    @Column(nullable = false)
    private LocalDate birthday;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private LocalDateTime registrationDate;
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    public Client(){
        this.registrationDate = LocalDateTime.now(); // Инициализация текущей даты и времени
    }
}
