package apps.udenar.edu.co.rutasnar.model;

public class Municipality {
    private String id_municipio;
    private String nom_municipio;
    private String latitud;
    private String longitud;

    public String getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(String id_municipio) {
        this.id_municipio = id_municipio;
    }

    public String getNom_municipio() {
        return nom_municipio;
    }

    public void setNom_municipio(String nom_municipio) {
        this.nom_municipio = nom_municipio;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
