package entidades;

/**
 * Created by LuisCambronero on 20/04/2016.
 */
public class Usuario {
    private int    usuId;
    private String usuNombre;
    private String usuNumero;
    private String usuEstadoAccion;
    private String usuEstadoConexion;
    private String usuImg;
    private String usuEstado;
    private String usuError;

    public int getUsuId() {
        return usuId;
    }

    public void setUsuId(int usuId) {
        this.usuId = usuId;
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

    public String getUsuNumero() {
        return usuNumero;
    }

    public void setUsuNumero(String usuNumero) {
        this.usuNumero = usuNumero;
    }

    public String getUsuEstadoAccion() {
        return usuEstadoAccion;
    }

    public void setUsuEstadoAccion(String usuEstadoAccion) {
        this.usuEstadoAccion = usuEstadoAccion;
    }

    public String getusuEstadoConexion() {
        return usuEstadoConexion;
    }

    public void setusuEstadoConexion(String usuEstadoConexion) {
        this.usuEstadoConexion = usuEstadoConexion;
    }

    public String getUsuImg() {
        return usuImg;
    }

    public void setUsuImg(String usuImg) {
        this.usuImg = usuImg;
    }

    public String getUsuEstado() {
        return usuEstado;
    }

    public void setUsuEstado(String usuEstado) {
        this.usuEstado = usuEstado;
    }

    public String getUsuError() {
        return usuError;
    }

    public void setUsuError(String usuError) {
        this.usuError = usuError;
    }

    public Usuario() {
    }

    public Usuario(String usuError) {
        this.usuError = usuError;
    }

    public Usuario(int usuId, String usuNombre, String usuNumero, String usuEstadoAccion, String usuEstadoConexion, String usuEstado) {
        this.usuId = usuId;
        this.usuNombre = usuNombre;
        this.usuNumero = usuNumero;
        this.usuEstadoAccion = usuEstadoAccion;
        this.usuEstadoConexion = usuEstadoConexion;
        this.usuEstado = usuEstado;
    }
}
