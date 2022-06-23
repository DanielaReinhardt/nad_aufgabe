package com.example.NAD.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.NAD.Entity.Location;
import com.example.NAD.repository.LocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileReader {

    @Autowired
    private LocationRepository repository;

    private static File INPUT_FILE = new File("src/main/resources/Liste.txt"); // alternativ gesamter Pfad
    // static Elemente werden komplett groß geschrieben

    public void read() throws IOException, NullPointerException {
        String ort;
        int pLZ;
        String strasse;
        String hausnummer;

        //List<Location> locations = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        lines = Files.readAllLines(INPUT_FILE.toPath());
        for (String line : lines) {
            String[] lineSplit = line.split("\t"); // soll als Tabulator gelten

            ort = lineSplit[0];
            pLZ = Integer.parseInt(lineSplit[1]);
            strasse = lineSplit[2];
            hausnummer = lineSplit[3];
            //locations.add(new Location(ort, pLZ, straße, hausnummer));
            
            repository.save(new Location(ort, pLZ, strasse, hausnummer));
            
            //repository.saveAll(locations);
        }
        
        
        // for(Location loc : locations){
        //     System.out.println(loc.getOrt());
        //     System.out.println(loc.getpLZ());
        //     System.out.println(loc.getStraße());
        //     System.out.println(loc.getHausnummer());
           
        // }
    
    }
}

// auslesen der Daten in Datei (Liste.txt) mit BufferedReader/ FileReader
// vermutlich erstmal in Liste speichern und die Liste dann in Datenbank
// speichern (nach Formatieren/ Splitten usw)
// formatieren, dass die Einträge sind: Ort, PLZ, Straße, Hausnummer

// Daten in Datenbank speichern
// Datei muss nicht weiter verwendet werden, da die Daten anschließend aus der
// Datenbank ausgelesen werden
