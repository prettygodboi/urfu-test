package com.example.urfutest.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Сущность образовательной программы
 * @author Shevlyakov D.P.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "educational_program")
public class EducationalProgram {
    @Id
    @Column
    @UuidGenerator
    private UUID id;

    @NotEmpty(message = "Название не должно быть пустым")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Шифр не должен быть пустым")
    @Column(name = "cypher")
    private String cypher;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Dict level;

    @ManyToOne
    @JoinColumn(name = "standard_id")
    private Dict standard;

    @ManyToOne
    @JoinColumn(name = "institute", referencedColumnName = "id")
    private Institute institute;

    @ManyToOne
    @JoinColumn(name = "head", referencedColumnName = "id")
    private Head head;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "educational_program2module",
            joinColumns = @JoinColumn(name = "educational_program_id"),
            inverseJoinColumns = @JoinColumn(name = "module_id")
    )
    private List<Module> modules = new ArrayList<>();

    @Column(name = "accreditationtime")
    @NotNull(message = "Введите дату следующей аккредитации")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date accreditationTime;
}
