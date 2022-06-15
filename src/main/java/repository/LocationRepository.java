package repository;

import org.springframework.stereotype.Repository;

import Entity.Location;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {


    
}
