package com.traffic.service;

import com.traffic.jpa.entity.Jam;
import com.traffic.jpa.repository.JamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JamService {

    private final JamRepository jamRepository;

    @Autowired
    public JamService(final JamRepository jamRepository) {
        this.jamRepository = jamRepository;
    }

    public void deleteAll() {
        jamRepository.deleteAll();
    }

    public List<Jam> findAll() {
        return jamRepository.findAll();
    }

    public List<Jam> findByDistrict(String district) {
        return jamRepository.findByDistrict(district);
    }

    public Jam create(Jam jam) {
        Jam existentJam = jamRepository.findByLocation(jam.getLocation());
        if (existentJam != null) {
            return updateExistingJam(existentJam, jam);
        } else {
            return jamRepository.save(jam);
        }
    }

    private Jam updateExistingJam(Jam existentJam, Jam newJam) {
        existentJam.setDescription(newJam.getDescription());
        existentJam.setSeverity(newJam.getSeverity());
        return jamRepository.save(existentJam);
    }

    public void delete(String location) {
        Jam existentJam = jamRepository.findByLocation(location);
        if (existentJam != null) {
            jamRepository.delete(existentJam);
        }
    }
}
