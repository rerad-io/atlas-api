package com.example.medatlas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "anatomical_structure", indexes = {@Index(name = "idx_name", columnList = "name")})
@NoArgsConstructor
@AllArgsConstructor
public class AnatomicalStructure {
    /**
     * Subject*	AnatomicalStructureSubject   (Тема анатом.Структура)	Тема
     * Name*	string 250	Название структуры
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private AnatomicalStructureSubject anatomicalStructureSubject;

    @Column(name = "name", length = 250, unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnatomicalStructure that = (AnatomicalStructure) o;
        return Objects.equals(anatomicalStructureSubject, that.anatomicalStructureSubject) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anatomicalStructureSubject, name);
    }

    @Override
    public String toString() {
        return "AnatomicalStructure{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "description='" + description + '\'' +
                '}';
    }
}