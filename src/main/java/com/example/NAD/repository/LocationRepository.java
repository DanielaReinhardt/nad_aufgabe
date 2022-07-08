package com.example.NAD.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.example.NAD.Entity.Location;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> { //name of the modelclass and the primary key

    Optional<Location> findById(Long id);

    
    

    
  


    
}
