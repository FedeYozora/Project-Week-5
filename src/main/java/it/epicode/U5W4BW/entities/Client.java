package it.epicode.U5W4BW.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.U5W4BW.enums.ClientType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
public class Client {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    private String businessName;
    private String vatNumber;
    private String email;
    private LocalDate creationDate;
    private LocalDate lastContactDate;
    private Double yearlyIncome;
    private String verifiedEmail;
    private String phoneNumber;
    private String contactEmail;
    private String contactName;
    private String contactSurname;
    private String contactPhoneNumber;
    private String clientLogo;
    @Enumerated(EnumType.STRING)
    private ClientType type;

    @JsonIgnore
    @OneToMany(mappedBy = "client", orphanRemoval = true)
    private Set<Invoice> invoices = new LinkedHashSet<>();

    @OneToOne(orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "registered_address_id")
    private Address registeredAddress;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "headquarters_address_id")
    private Address headquartersAddress;


    public Client(String businessName, String vatNumber, String email,
                  String verifiedEmail, String phoneNumber, String contactEmail,
                  String contactName, String contactSurname, String contactPhoneNumber,
                  ClientType type, Address registeredAddress, Address headquartersAddress) {
        this.businessName = businessName;
        this.vatNumber = vatNumber;
        this.email = email;
        this.verifiedEmail = verifiedEmail;
        this.phoneNumber = phoneNumber;
        this.contactEmail = contactEmail;
        this.contactName = contactName;
        this.contactSurname = contactSurname;
        this.contactPhoneNumber = contactPhoneNumber;
        this.type = type;
        this.registeredAddress = registeredAddress;
        this.headquartersAddress = headquartersAddress;
        this.creationDate = LocalDate.now();
        this.lastContactDate = LocalDate.now();

    }
}
