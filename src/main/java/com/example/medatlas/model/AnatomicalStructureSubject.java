package com.example.medatlas.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "anatomical_structure_subject")
@NoArgsConstructor
@AllArgsConstructor
public class AnatomicalStructureSubject {
    /**
     * Name*	string 250	Название темы
     * Color*	string 6	HEX цвет
     */
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @OneToMany(mappedBy = "subject")
    private List<AnatomicalStructure> anatomicalStructures;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnatomicalStructureSubject anatomicalStructureSubject = (AnatomicalStructureSubject) o;
        if (!name.equals(anatomicalStructureSubject.name)) return false;
        return color.equals(anatomicalStructureSubject.color);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + color.hashCode();
        return result;
    }
}