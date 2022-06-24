package com.example.NAD.Controller;

import java.util.List;

import com.example.NAD.Entity.Location;
import com.example.NAD.repository.LocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobRestController {

    @Autowired
    private LocationRepository repository;
   
    @GetMapping("/locations")
    public List<Location> getAll() {
        List<Location> locations = repository.findAll();
        
        return locations;

    }
    
    @GetMapping("/locations/{id}")
    public Location findById(@PathVariable Long id){
       Location location = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Location Id" + id));
        return location;
        //nachlesen:stacktrace ausschalten: springboot stacktrace 500
    }

    @PostMapping("/locations")
    public Location create(@RequestBody Location location){
        //nachlesen wegen Annotations Spring https://spring.io/guides/tutorials/rest/
        Location location2 = repository.save(location);

        return location2;
    }

}
