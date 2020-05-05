package apps.udenar.edu.co.rutasnar.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id_usuario")
    @Expose
    private String idUsuario;
    @SerializedName("nom_usuario")
    @Expose
    private String nomUsuario;
    @SerializedName("clave_usuario")
    @Expose
    private String claveUsuario;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("{id: %s, nombre: %s, clave: %s}", idUsuario, nomUsuario, claveUsuario);
    }

    public User(String idUsuario, String nomUsuario, String claveUsuario) {
        this.idUsuario = idUsuario;
        this.nomUsuario = nomUsuario;
        this.claveUsuario = claveUsuario;
    }
}
