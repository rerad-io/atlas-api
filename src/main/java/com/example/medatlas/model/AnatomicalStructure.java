package com.example.medatlas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "anatomical_structure")
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
    private AnatomicalStructureSubject subject;

    @Column(name = "name", length = 250, unique = true)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnatomicalStructure that = (AnatomicalStructure) o;
        return Objects.equals(subject, that.subject) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, name);
    }
}