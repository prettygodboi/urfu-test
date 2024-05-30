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
@Table(name = "dict")
public class Dict {
    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "bcode")
    private String bcode;

    @ManyToOne()
    @JoinColumn(name = "parent_id")
    private Dict parent_dict;

    @Column(name = "deleted")
    private Boolean deleted;
}
