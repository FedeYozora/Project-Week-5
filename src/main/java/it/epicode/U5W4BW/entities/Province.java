package it.epicode.U5W4BW.entities;

import com.opencsv.bean.CsvBindByPosition;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "provinces")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Province {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;
    @CsvBindByPosition(position = 1)
    private String name;
    @CsvBindByPosition(position = 0)
    private String acronym;
    @CsvBindByPosition(position = 2)
    private String region;

    @OneToMany(mappedBy = "province", orphanRemoval = true)
    @ToString.Exclude
    private Set<Municipality> municipalities = new LinkedHashSet<>();

    public Province(String name, String acronym, String region) {
        this.name = name;
        this.acronym = acronym;
        this.region = region;
    }

}
