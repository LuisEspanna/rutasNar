package apps.udenar.edu.co.rutasnar.model;

import java.util.Map;

import apps.udenar.edu.co.rutasnar.ApiUtils;

public class Event {
    private String id_evento;
    private String id_municipio;
    private String nom_evento;
    private String desc_evento;
    private String img_evento;
    private String fecha_evento;
    private String disponible;
    private String latitud;
    private String longitud;

    public Event(String jsonInfo) {

    }

    public Event(Map<String, String> mapInfo) {
        id_evento = mapInfo.get("id_evento");
        id_municipio = mapInfo.get("id_municipio");
        nom_evento = mapInfo.get("nom_evento");
        desc_evento = mapInfo.get("desc_evento");
        img_evento = mapInfo.get("img_evento");
        fecha_evento = mapInfo.get("fecha_evento");
        disponible = mapInfo.get("disponible");
        latitud = mapInfo.get("latitud");
        longitud = mapInfo.get("longitud");
    }

    public String getId_evento() {
        return id_evento;
    }

    public void setId_evento(String id_evento) {
        this.id_evento = id_evento;
    }

    public String getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(String id_municipio) {
        this.id_municipio = id_municipio;
    }

    public String getNom_evento() {
        return nom_evento;
    }

    public void setNom_evento(String nom_evento) {
        this.nom_evento = nom_evento;
    }

    public String getDesc_evento() {
        return desc_evento;
    }

    public void setDesc_evento(String desc_evento) {
        this.desc_evento = desc_evento;
    }

    public String getImg_evento() {
        return ApiUtils.IMAGES_URL +img_evento;
    }

    public void setImg_evento(String img_evento) {
        this.img_evento = img_evento;
    }

    public String getFecha_evento() {
        return fecha_evento;
    }

    public void setFecha_evento(String fecha_evento) {
        this.fecha_evento = fecha_evento;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
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

    public String getDescEventSumary() {
        if(desc_evento.length()>200){
            return desc_evento.substring(0, 200) + "...";
        }
        return desc_evento;
    }
}
