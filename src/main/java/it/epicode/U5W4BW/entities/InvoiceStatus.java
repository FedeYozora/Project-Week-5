package it.epicode.U5W4BW.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "invoice_status")
@Getter
@Setter
@NoArgsConstructor
public class InvoiceStatus {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(name = "id", nullable = false)
    private UUID id;
    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "invoiceStatus", orphanRemoval = true)
    private Set<Invoice> invoices = new LinkedHashSet<>();

    public InvoiceStatus(String status) {
        this.status = status;
    }
}
