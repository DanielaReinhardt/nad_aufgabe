package com.example.NAD.Controller;

import java.io.IOException;
import java.util.List;
import com.example.NAD.Entity.Location;
import com.example.NAD.repository.LocationRepository;
import com.example.NAD.util.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


public class JobController {

    @Autowired
    private LocationRepository repository;
    @Autowired // je Attribut angeben
    private FileReader reader;

    @GetMapping("/init")
    public String init() throws NullPointerException, IOException {
        reader.read();

        return "index";

    }

    @GetMapping("/")
    public String getAll(Model model) {
        List<Location> locations = repository.findAll();
        model.addAttribute("locations", locations); // Objekt

        return "index";

    }

    @GetMapping("/location/{id}")
    public String findById(@PathVariable Long id, Model model) {
        //if-Anweisung einbringen falls id nicht existiert (optional, da exception)
        Location locations = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Location Id" + id));
        model.addAttribute("locations", locations);

        return "location";
    }

    @GetMapping("/delete")
    public String deleteLocation(@RequestParam Long id, Model model) {
        Location location = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Location Id" + id));
        repository.deleteById(id);
        model.addAttribute("location", location);
       
        return "confirm";
    }

    @GetMapping("/updateform")
    public String updateLocation(@RequestParam long id, Model model) {

        Location updateLocation = repository.findById(id).get();
        
        model.addAttribute("location", updateLocation);
        
        return "update";
    }
    //Putmapping funktioniert nicht bzw nur wenn die Applicationproperties geändert wurden
    @PutMapping("/update/{id}")
    public String changeLocation(@PathVariable long id,
                @RequestParam String ort, @RequestParam Integer pLZ, @RequestParam String strasse, @RequestParam String hausnummer, Model model){      
            
            Location changeLocation = repository.findById(id).get();
            //bindet die Werte in die Location über den RequestBody
            changeLocation.setOrt(ort);
            changeLocation.setpLZ(pLZ);
            changeLocation.setStrasse(strasse);
            changeLocation.setHausnummer(hausnummer);

        changeLocation = repository.save(changeLocation); 
        
        model.addAttribute("location", changeLocation);

        return "confirm";
    }
 
    // geht nicht mit Postmapping und bei createform kommt 404 (Whitelabel Error Page)
    @RequestMapping("/create")
    public String createLocation(@ModelAttribute Location location, Model model) {

        Location loca = new Location();

        model.addAttribute("location ", loca);

        return "create";

    }

   
    @PostMapping("/create")
    public String changeLoca(@RequestParam String ort, @RequestParam Integer pLZ,
            @RequestParam String strasse, @RequestParam String hausnummer, Model model) {
        Location location = new Location();
           
        location.setOrt(ort);
        location.setpLZ(pLZ);
        location.setStrasse(strasse);
        location.setHausnummer(hausnummer);
        location = repository.save(location);

        model.addAttribute("location", location);

        return "confirm";

    }

}
