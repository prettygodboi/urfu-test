package com.example.urfutest.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "educational_program")
public class EducationalProgram {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @NotEmpty(message = "Название не должно быть пустым")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Шифр не должен быть пустым")
    @Column(name = "cypher")
    private String cypher;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private Level level;

    @Enumerated(EnumType.STRING)
    @Column(name = "standard")
    private Standard standard;

    @ManyToOne
    @JoinColumn(name = "institute", referencedColumnName = "id")
    private Institute institute;

    @ManyToOne
    @JoinColumn(name = "head", referencedColumnName = "id")
    private Head head;

    @Column(name = "accreditationTime")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date accreditationTime;
}
