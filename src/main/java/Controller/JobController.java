package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import Entity.Location;
import repository.LocationRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class JobController {
 
    @Autowired
    private LocationRepository repository;


@GetMapping("/")
public String getAll(){
    repository.findAll();

    return "index";
    
}    

@GetMapping("/location/{id}")
public String findById(@PathVariable Long id){
    repository.findById(id).get();

    return "location";
}


@DeleteMapping("/delete")
public String deleteLocation(@PathVariable("id") long id, Model model){
    Location location = repository.findById(id).
        orElseThrow(() -> new IllegalArgumentException("Invalid Location Id" + id));
        repository.delete(location);
        return "delete";
}

@PutMapping("/update/{id}") 
public String updateLocation(@PathVariable long id,@RequestParam String ort, int pLZ, String straße, String hausnummer) {
 
    Location updateLocation = repository.findById(id).get();
    if (ort != null){
        updateLocation.setOrt(ort);
    }
    if(pLZ != 0){
        updateLocation.setpLZ(pLZ);
    }
    if(straße != null){
        updateLocation.setStraße(straße);
    }
    if(hausnummer != null){
        updateLocation.setHausnummer(hausnummer);
        
    }
    repository.save(updateLocation);
   return "update";
}

@PostMapping("/create")
public String createLocation(@RequestParam String ort, int pLZ, String straße, String hausnummer) {
    if(ort == null || pLZ == 0 || straße == null || hausnummer == null ){
        repository.save(new Location(ort, pLZ, straße, hausnummer));
        
    }
    return "create";
}
}


    
