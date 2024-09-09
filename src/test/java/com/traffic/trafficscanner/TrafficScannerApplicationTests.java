package com.traffic.trafficscanner;

import com.traffic.jpa.entity.Jam;
import com.traffic.jpa.repository.JamRepository;
import com.traffic.service.JamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TrafficScannerApplicationTests {

    @Autowired
    private JamService jamService;

    @MockBean
    private JamRepository jamRepository;

    private Jam sampleJam;

    @BeforeEach
    void setUp() {
        sampleJam = new Jam();
        sampleJam.setLocation("TestLocation");
        sampleJam.setDistrict("TestDistrict");
    }

    @Test
    void contextLoads() {
    }

    @Test
    void shouldDeleteAllJams() {
        jamService.deleteAll();
        verify(jamRepository, times(1)).deleteAll();
    }

    @Test
    void shouldFindAllJams() {
        when(jamRepository.findAll()).thenReturn(Collections.singletonList(sampleJam));
        List<Jam> jams = jamService.findAll();
        assertEquals(1, jams.size());
        assertEquals(sampleJam, jams.getFirst());
    }

    @Test
    void shouldCreateNewJamWhenLocationDoesNotExist() {
        when(jamRepository.findByLocation("TestLocation")).thenReturn(null);
        when(jamRepository.save(sampleJam)).thenReturn(sampleJam);

        Jam createdJam = jamService.create(sampleJam);

        assertEquals(sampleJam, createdJam);
        verify(jamRepository, times(1)).save(sampleJam);
    }

    @Test
    void shouldUpdateExistingJamWhenLocationExists() {
        when(jamRepository.findByLocation("TestLocation")).thenReturn(sampleJam);
        when(jamRepository.save(sampleJam)).thenReturn(sampleJam);

        Jam updatedJam = jamService.create(sampleJam);

        assertEquals(sampleJam, updatedJam);
        verify(jamRepository, times(1)).save(sampleJam);
    }

    @Test
    void shouldDeleteJamByLocation() {
        when(jamRepository.findByLocation("TestLocation")).thenReturn(sampleJam);

        jamService.delete("TestLocation");

        verify(jamRepository, times(1)).delete(sampleJam);
    }

    @Test
    void shouldFindJamsByDistrict() {
        when(jamRepository.findByDistrict("TestDistrict")).thenReturn(Collections.singletonList(sampleJam));

        List<Jam> jams = jamService.findByDistrict("TestDistrict");

        assertEquals(1, jams.size());
        assertEquals(sampleJam, jams.getFirst());
    }
}
