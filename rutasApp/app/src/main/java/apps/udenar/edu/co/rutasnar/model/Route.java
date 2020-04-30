package apps.udenar.edu.co.rutasnar.model;

import java.util.Map;

public class Route {
    private String id_ruta;
    private String id_municipio;
    private String nom_ruta;
    private String desc_ruta;
    private String img_ruta;
    private String tiempo_ruta;

    public Route(Map<String, String> mapInfo) {
        id_ruta = mapInfo.get("id_ruta");
        id_municipio = mapInfo.get("id_municipio");
        nom_ruta = mapInfo.get("nom_ruta");
        desc_ruta = mapInfo.get("desc_ruta");
        img_ruta = mapInfo.get("img_ruta");
        tiempo_ruta = mapInfo.get("tiempo_ruta");
    }

    public String getId_ruta() {
        return id_ruta;
    }

    public void setId_ruta(String id_ruta) {
        this.id_ruta = id_ruta;
    }

    public String getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(String id_municipio) {
        this.id_municipio = id_municipio;
    }

    public String getNom_ruta() {
        return nom_ruta;
    }

    public void setNom_ruta(String nom_ruta) {
        this.nom_ruta = nom_ruta;
    }

    public String getDesc_ruta() {
        return desc_ruta;
    }

    public void setDesc_ruta(String desc_ruta) {
        this.desc_ruta = desc_ruta;
    }

    public String getImg_ruta() {
        return "http://181.55.121.253:8880/public/uploads/"+img_ruta;
    }

    public void setImg_ruta(String img_ruta) {
        this.img_ruta = img_ruta;
    }

    public String getTiempo_ruta() {
        return tiempo_ruta;
    }

    public void setTiempo_ruta(String tiempo_ruta) {
        this.tiempo_ruta = tiempo_ruta;
    }
}
