package com.example.NAD.Entity;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    
    private String ort;
    private int pLZ;
    private String strasse;
    private String hausnummer;
    
    
    public Location(String Ort, int PLZ, String Strasse, String Hausnummer) {
        this.ort = Ort;
        this.pLZ = PLZ;
        this.strasse = Strasse;
        this.hausnummer = Hausnummer;
    }
    
    public Location(){

    }
   


    public Location(Location location) {
    }

    public Long getId() {
        return id;
    }




    public void setId(Long id) {
        this.id = id;
    }





    public String getOrt() {
        return ort;
    }




    public void setOrt(String ort) {
        this.ort = ort;
    }




    public int getpLZ() {
        return pLZ;
    }




    public void setpLZ(int pLZ) {
        this.pLZ = pLZ;
    }




    public String getStrasse() {
        return strasse;
    }




    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }




    public String getHausnummer() {
        return hausnummer;
    }




    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }




    @Override
    public String toString() {
        return "Location [Hausnummer=" + hausnummer + ", Ort=" + ort + ", PLZ=" + pLZ + ", Straße=" + strasse + "]";
    }

    
}
