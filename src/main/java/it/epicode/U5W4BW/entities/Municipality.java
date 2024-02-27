package it.epicode.U5W4BW.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByPosition;
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
public class Municipality {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;
    @CsvBindByPosition(position = 0)
    private String provinceCode;
    @CsvBindByPosition(position = 1)
    private String provinceSerial;
    @CsvBindByPosition(position = 2)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "municipality", orphanRemoval = true)
    private Set<Address> addresses = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    public Municipality(String provinceCode, String provinceSerial, String name) {
        this.provinceCode = provinceCode;
        this.provinceSerial = provinceSerial;
        this.name = name;
    }
}
