package com.traffic.jpa.repository;

import com.traffic.jpa.entity.Jam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JamRepository extends JpaRepository<Jam, Long> {
    Jam findByLocation(String location);
}
