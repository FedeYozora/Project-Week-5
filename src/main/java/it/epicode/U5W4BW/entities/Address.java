package it.epicode.U5W4BW.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    private String street;
    private String streetNumber;
    private String city;
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "municipality_id")
    private Municipality municipality;


}
