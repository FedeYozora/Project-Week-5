package it.epicode.U5W4BW.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_number", nullable = false)
    private Long id;

    private LocalDate emissionDate;
    private Double amount;
    private String status;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "client_id")
    private Client client;

    public Invoice(Double amount, Client client) {
        this.emissionDate = LocalDate.now();
        this.amount = amount;
        this.status = "Emitted";
        this.client = client;
    }
}
