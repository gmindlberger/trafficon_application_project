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

	@BeforeEach
	void setUp() {
	}

	@Test
	void contextLoads() {
	}

	@Test
	void testDeleteAll() {
		doNothing().when(jamRepository).deleteAll();
		jamService.deleteAll();
		verify(jamRepository, times(1)).deleteAll();
	}

	@Test
	void testFindAll() {
		Jam jam = new Jam();
		when(jamRepository.findAll()).thenReturn(Collections.singletonList(jam));
		List<Jam> jams = jamService.findAll();
		assertEquals(1, jams.size());
		assertEquals(jam, jams.getFirst());
	}

	@Test
	void testCreateNewJam() {
		Jam jam = new Jam();
		jam.setLocation("TestLocation");
		when(jamRepository.findByLocation("TestLocation")).thenReturn(null);
		when(jamRepository.save(jam)).thenReturn(jam);
		Jam createdJam = jamService.create(jam);
		assertEquals(jam, createdJam);
	}

	@Test
	void testCreateExistingJam() {
		Jam jam = new Jam();
		jam.setLocation("TestLocation");
		jam.setActive(false);
		when(jamRepository.findByLocation("TestLocation")).thenReturn(jam);
		when(jamRepository.save(jam)).thenReturn(jam);
		Jam createdJam = jamService.create(jam);
		assertTrue(createdJam.isActive());
	}

	@Test
	void testActivateJam() {
		Jam jam = new Jam();
		jam.setActive(false);
		when(jamRepository.save(jam)).thenReturn(jam);
		Jam activatedJam = jamService.activate(jam);
		assertTrue(activatedJam.isActive());
	}

	@Test
	void testDeactivateJamByLocation() {
		Jam jam = new Jam();
		jam.setLocation("TestLocation");
		jam.setActive(true);
		when(jamRepository.findByLocation("TestLocation")).thenReturn(jam);
		when(jamRepository.save(jam)).thenReturn(jam);
		Jam deactivatedJam = jamService.deactivate("TestLocation");
		assertFalse(deactivatedJam.isActive());
	}

	@Test
	void testDeactivateJam() {
		Jam jam = new Jam();
		jam.setActive(true);
		when(jamRepository.save(jam)).thenReturn(jam);
		Jam deactivatedJam = jamService.deactivate(jam);
		assertFalse(deactivatedJam.isActive());
	}
}
