package com.example.medatlas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "study")
@NoArgsConstructor
@AllArgsConstructor
public class Study {
    /**
     * Id	GUID	Идентификатор
     * ExternalId*	GUID	Внешний идентификатор исследования
     * Name*	string 250	Название
     * Description	string max	Описание
     * PreviewFrame	string 250	Кадр для превью
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "external_id", unique = true)
    private String externalId;

    @Column(name = "name", length = 250, unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "preview_frame", length = 250)
    private String previewFrame;

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private List<Series> seriesList;

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private List<InstanceData> instanceDataList;

    @Override
    public boolean equals(Object o) {
        // Check if the current object is being compared to itself
        if (this == o) return true;

        // Check if the compared object is null or of a different class
        if (o == null || getClass() != o.getClass()) return false;
        Study study = (Study) o;
        // Сравниваем по id, если он не null
        if (!Objects.equals(id, study.id)) return false;

        // Если id у обоих объектов равны null, сравниваем по externalId
        return Objects.equals(externalId, study.externalId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Study{" +
                "id=" + id +
                ", externalId=" + externalId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", previewFrame='" + previewFrame + '\'' +
                '}';
    }
}