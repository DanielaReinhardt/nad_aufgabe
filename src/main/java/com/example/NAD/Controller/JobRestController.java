package com.example.NAD.Controller;

import java.util.List;

import com.example.NAD.Entity.Location;
import com.example.NAD.repository.LocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


@CrossOrigin
@RestController
@RequestMapping("/locations")
public class JobRestController {

    @Autowired
    private LocationRepository repository;

    @GetMapping
    public List<Location> getAll() {
        List<Location> locations = repository.findAll();

        return locations;

    }

    @GetMapping("/{id}")
    public Location findById(@PathVariable Long id) {
        Location location = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Location Id" + id));
        return location;
        // nachlesen:stacktrace ausschalten: springboot stacktrace 500

    }

    @PostMapping
    public Location create(@RequestBody Location location) {
        // nachlesen wegen Annotations Spring https://spring.io/guides/tutorials/rest/
        Location location2 = repository.save(location); //nicht mit new Location, das macht Spring

        return location2;

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLocation(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.ok().build();
        // Alex fragen, was ResponseEntity ist. Benötigt man das speziell für React?
        //Klasse, wie ein Wrapper; MetaDaten mitzugeben (Ok oder nicht), neben dem Objekt werden extra Infos mitgegeben
        //könnte man auch bei GetMapping machen
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location location) {
        Location currenLocation = repository.findById(id).orElseThrow(RuntimeException::new);
        currenLocation.setOrt(location.getOrt());
        currenLocation.setpLZ(location.getpLZ());
        currenLocation.setStrasse(location.getStrasse());
        currenLocation.setHausnummer(location.getHausnummer());
        
        return ResponseEntity.ok(currenLocation);
    }

}
