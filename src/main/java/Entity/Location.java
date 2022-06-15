package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

@Entity
@Table(name = "nad")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;  
    @Column(name="ort")
    private String ort;
    @Column(name="pLZ")
    private int pLZ;
    @Column(name="straße")
    private String straße;
    @Column(name="hausnummer")
    private String hausnummer;
    
    
    public Location(String Ort, int PLZ, String Straße, String Hausnummer) {
        Ort = ort;
        PLZ = pLZ;
        Straße = straße;
        Hausnummer = hausnummer;
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




    public String getStraße() {
        return straße;
    }




    public void setStraße(String straße) {
        this.straße = straße;
    }




    public String getHausnummer() {
        return hausnummer;
    }




    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }




    @Override
    public String toString() {
        return "Location [Hausnummer=" + hausnummer + ", Ort=" + ort + ", PLZ=" + pLZ + ", Straße=" + straße + "]";
    }

    
}
