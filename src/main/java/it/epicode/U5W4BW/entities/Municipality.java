package it.epicode.U5W4BW.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String provinceCode;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "municipality", orphanRemoval = true)
    private Set<Address> addresses = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    public Municipality(String provinceCode, String name) {
        this.provinceCode = provinceCode;
        this.name = name;
    }
}
