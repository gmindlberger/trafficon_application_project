package com.traffic.rest;

import com.traffic.jpa.entity.Jam;
import com.traffic.service.JamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrafficApi {

    private final JamService jamService;

    @Autowired
    public TrafficApi(JamService jamService) {
        this.jamService = jamService;
    }

    @PostMapping("/create")
    public ResponseEntity<Jam> createJam(@RequestBody Jam jam) {
        Jam createdJam = jamService.create(jam);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdJam);
    }

    @GetMapping("/getAllJams")
    public ResponseEntity<List<Jam>> getAllJams() {
        List<Jam> jams = jamService.findAll();
        if (jams.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(jams);
    }

    @GetMapping("/getJamsByDistrict/{district}")
    public ResponseEntity<List<Jam>> getJamsByDistrict(@PathVariable String district) {
        List<Jam> jams = jamService.findByDistrict(district);
        if (jams.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(jams);
    }

    @DeleteMapping("/deleteAllJams")
    public ResponseEntity<Void> deleteAllJams() {
        jamService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteJamByLocation/{location}")
    public ResponseEntity<Void> deleteJam(@PathVariable String location) {
        jamService.delete(location);
        return ResponseEntity.noContent().build();
    }
}

