package rs.raf.projekat1.models;

public class Rashod {

    private int id;
    private String naslov;
    private int suma;
    private String opis;
    private boolean audioChecked;

    public Rashod(int id, String naslov, int suma) {
        this.id = id;
        this.naslov = naslov;
        this.suma = suma;
        audioChecked=false;
        opis="";
    }

    public int getSuma() {
        return suma;
    }

    public String getNaslov() {
        return naslov;
    }

    public int getId() {
        return id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public boolean isAudioChecked() {
        return audioChecked;
    }

    public void setAudioChecked(boolean audioChecked) {
        this.audioChecked = audioChecked;
    }

}
