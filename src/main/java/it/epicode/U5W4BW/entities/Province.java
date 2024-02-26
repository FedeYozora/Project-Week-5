package it.epicode.U5W4BW.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Province {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    private String name;
    private String acronym;
    private String region;

    @OneToMany(mappedBy = "province", orphanRemoval = true)
    private Set<Municipality> municipalities = new LinkedHashSet<>();

    public Province(String name, String acronym, String region) {
        this.name = name;
        this.acronym = acronym;
        this.region = region;
    }

}
