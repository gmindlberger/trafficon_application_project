package com.traffic.rest;

import com.traffic.jpa.entity.Jam;
import com.traffic.service.JamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrafficApi {

    JamService jamService;

    @Autowired
    public TrafficApi(JamService jamService) {
        this.jamService = jamService;
    }

    @GetMapping("/all")
    List<Jam> getAllJams() {
        return jamService.findAll();
    }

    @GetMapping("/deleteAll")
    void deleteAllJams() {
        jamService.deleteAll();
    }

    @PostMapping("/create")
    ResponseEntity<Jam> createJam(@RequestBody Jam jam) {
        return ResponseEntity.ok(jamService.create(jam));
    }

    @PutMapping("/deactivate/{location}")
    ResponseEntity<Jam> deactivateJam(@PathVariable String location) {
        return ResponseEntity.ok(jamService.deactivate(location));
    }

    @PutMapping("/deactivate")
    ResponseEntity<Jam> deactivateJam(@RequestBody Jam jam) {
        return ResponseEntity.ok(jamService.deactivate(jam));
    }
}
