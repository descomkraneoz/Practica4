package net.iessochoa.manuelmartinez.practica4;

import java.util.Objects;

public class Poblacion {
    private String Provincia;
    private String Localidad;
    private Float Valoracion;
    private String Comentarios;

    public Poblacion(String provincia, String localidad, Float valoracion, String comentarios) {
        Provincia = provincia;
        Localidad = localidad;
        Valoracion = valoracion;
        Comentarios = comentarios;
    }

    public Poblacion() {

    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String provincia) {
        Provincia = provincia;
    }

    public String getLocalidad() {
        return Localidad;
    }

    public void setLocalidad(String localidad) {
        Localidad = localidad;
    }

    public Float getValoracion() {
        return Valoracion;
    }

    public void setValoracion(Float valoracion) {
        Valoracion = valoracion;
    }

    public String getComentarios() {
        return Comentarios;
    }

    public void setComentarios(String comentarios) {
        Comentarios = comentarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poblacion poblacion = (Poblacion) o;
        return Provincia.equals(poblacion.Provincia) &&
                Localidad.equals(poblacion.Localidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Provincia, Localidad);
    }

    @Override
    public String toString() {
        return "Poblacion{" +
                "Provincia='" + Provincia + '\'' +
                ", Localidad='" + Localidad + '\'' +
                ", Valoracion=" + Valoracion +
                ", Comentarios='" + Comentarios + '\'' +
                '}';
    }
}
