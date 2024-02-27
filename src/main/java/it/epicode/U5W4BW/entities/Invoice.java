package it.epicode.U5W4BW.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
public class Invoice {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_number", nullable = false)
    private Long id;

    private LocalDate emissionDate;
    private Double amount;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "invoice_status_id")
    private InvoiceStatus invoiceStatus;

    public Invoice(Double amount, Client client, InvoiceStatus invoiceStatus) {
        this.emissionDate = LocalDate.now();
        this.amount = amount;
        this.invoiceStatus = invoiceStatus;
        this.client = client;
    }
}
