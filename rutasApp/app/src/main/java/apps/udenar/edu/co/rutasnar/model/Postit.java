package apps.udenar.edu.co.rutasnar.model;

import androidx.annotation.NonNull;

public class Postit {
    private String id_actividad;
    private String id_usuario;
    private String nom_actividad;
    private String id_ruta;
    private String id_evento;

    public Postit(String id_actividad, String id_usuario, String nom_actividad, String id_ruta, String id_evento) {
        this.id_actividad = id_actividad;
        this.id_usuario = id_usuario;
        this.nom_actividad = nom_actividad;
        this.id_ruta = id_ruta;
        this.id_evento = id_evento;
    }

    public String getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(String id_actividad) {
        this.id_actividad = id_actividad;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNom_actividad() {
        return nom_actividad;
    }

    public void setNom_actividad(String nom_actividad) {
        this.nom_actividad = nom_actividad;
    }

    public String getId_ruta() {
        return id_ruta;
    }

    public void setId_ruta(String id_ruta) {
        this.id_ruta = id_ruta;
    }

    public String getId_evento() {
        return id_evento;
    }

    public void setId_evento(String id_evento) {
        this.id_evento = id_evento;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("{id_actividad: %s, nom_actividad: %s}", id_actividad, nom_actividad);
    }
}
