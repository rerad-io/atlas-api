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
@Table(name = "series", uniqueConstraints = {
        @UniqueConstraint(name = "uk_series_number_per_study", columnNames = {"study_id", "number"})
})
@NoArgsConstructor
@AllArgsConstructor
public class Series {
    /**
     * Id	GUID	Идентификатор
     * Study*	Study (Исследование)	Ссылка на объект Исследование
     * Number*	int	Номер исследование
     * Name	string 250	Описание
     * PreviewFrame	string 250	Кадр для превьюшки
     * InstanceCount*	int	Количество кадров
     * SagitalFrame	string 250	Кадр для превьюшки сагитальной плоскости
     * CoronalFrame	string 250	Кадр для превьюшки корональной плоскости
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(targetEntity = Study.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id", referencedColumnName = "id")
    private Study study;

    @Column(name = "number")
    private int number;

    @Column(name = "name", length = 250, unique = true)
    private String name;

    @Column(name = "preview_frame", length = 250)
    private String previewFrame;

    @Column(name = "instance_count")
    private int instanceCount;

    @Column(name = "sagital_frame", length = 250)
    private String sagitalFrame;

    @Column(name = "coronal_frame", length = 250)
    private String coronalFrame;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
    private List<InstanceData> instanceDataList;

    // Equals method to compare two Series objects
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Series series = (Series) o;
        return number == series.number &&
                Objects.equals(id, series.id) &&
                Objects.equals(name, series.name) &&
                Objects.equals(previewFrame, series.previewFrame) &&
                Objects.equals(instanceCount, series.instanceCount) &&
                Objects.equals(sagitalFrame, series.sagitalFrame) &&
                Objects.equals(coronalFrame, series.coronalFrame) &&
                Objects.equals(study, series.study);
    }

    // HashCode method to generate a hash code for a Series object
    @Override
    public int hashCode() {
        return Objects.hash(id, number, name, previewFrame, instanceCount, sagitalFrame, coronalFrame, study);
    }

    // ToString method to convert a Series object to a string representation
    @Override
    public String toString() {
        return "Series{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", previewFrame='" + previewFrame + '\'' +
                ", instanceCount=" + instanceCount +
                ", sagitalFrame='" + sagitalFrame + '\'' +
                ", coronalFrame='" + coronalFrame + '\'' +
                ", study=" + study +
                '}';
    }
}