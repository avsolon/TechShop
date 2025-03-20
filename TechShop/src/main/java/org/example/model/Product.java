package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Integer availableStock; // кол-во товаров
    @Column(nullable = false)
    private LocalDate lastUpdateDate; // дата последней закупки
    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;
    @OneToOne
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;
}
