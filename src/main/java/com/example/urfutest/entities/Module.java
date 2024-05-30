package com.example.urfutest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

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

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "standard_id")
    private Dict standard;
}
