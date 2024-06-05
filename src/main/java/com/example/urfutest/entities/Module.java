package com.example.urfutest.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Сущность модуля
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "module")
public class Module {
    @Id
    @Column(name = "id")
    @UuidGenerator
    private UUID id;

    @NotEmpty(message = "Заполните назвоние модуля")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Заполните тип модуля")
    @Column(name = "type")
    private String type;

    @ManyToMany(mappedBy = "modules", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<EducationalProgram> programs = new ArrayList<>();
}
