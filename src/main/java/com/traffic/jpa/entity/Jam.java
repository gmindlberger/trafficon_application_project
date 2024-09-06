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
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private OffsetDateTime startTime;

    @Column
    private OffsetDateTime endTime;

    @Column
    private String location;

    @Column
    private boolean active;

    @Column
    @Enumerated(EnumType.STRING)
    private Severity severity;

    @Column
    private OffsetDateTime createdAt;

    @Column
    private OffsetDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        OffsetDateTime now = OffsetDateTime.now();
        setCreatedAt(now);
        setUpdatedAt(now);
        active = true;
    }
}
