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

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "institute")
public class Institute {
    @Id
    @Column(name = "id")
    @UuidGenerator
    private UUID id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "institute")
    private List<EducationalProgram> programList = new ArrayList<>();
}
