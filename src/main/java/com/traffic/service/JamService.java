package com.traffic.service;


import com.traffic.jpa.entity.Jam;
import com.traffic.jpa.repository.JamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
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

    public Jam create(Jam jam) {
        Jam existent = jamRepository.findByLocation(jam.getLocation());
        return existent != null ? activate(existent) : jamRepository.save(jam);
    }

    public Jam activate(Jam jam) {
        return updateStatus(jam, true);
    }

    public Jam deactivate(String location) {
        Jam existent = jamRepository.findByLocation(location);
        return existent != null ? deactivate(existent) : null;
    }

    public Jam deactivate(Jam jam) {
        return updateStatus(jam, false);
    }

    private Jam updateStatus(Jam jam, boolean status) {
        jam.setActive(status);
        jam.setUpdatedAt(OffsetDateTime.now());
        return jamRepository.save(jam);
    }
}
