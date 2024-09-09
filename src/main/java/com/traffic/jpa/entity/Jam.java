package com.traffic.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Jam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String location;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private Severity severity;

    @Column(nullable = false)
    private String district;

    @Column
    private OffsetDateTime createdAt;

    @Column
    private OffsetDateTime updatedAt;


    @PrePersist
    public void onCreate() {
        OffsetDateTime now = OffsetDateTime.now();
        setCreatedAt(now);
    }

    @PreUpdate
    public void onUpdate() {
        setUpdatedAt(OffsetDateTime.now());
    }
}
