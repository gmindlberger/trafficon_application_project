package com.traffic.jpa.repository;

import com.traffic.jpa.entity.Jam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JamRepository extends JpaRepository<Jam, Long> {
    Jam findByLocation(String location);

    List<Jam> findByDistrict(String district);
}
