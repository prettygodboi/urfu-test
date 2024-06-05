package com.example.urfutest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Сущность ответственного лица
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "head")
public class Head {
    @Id
    @Column(name = "id")
    @UuidGenerator
    private UUID id;

    @Column(name = "fullname")
    private String fullName;

    @OneToMany(mappedBy = "head")
    List<EducationalProgram> heads = new ArrayList<>();
}
