package net.iessochoa.manuelmartinez.practica4;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Poblacion implements Parcelable {
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

    /**
     * Hacemos Parcelable a Poblacion, implementamos los metodos y los sobreescribimos
     */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.Provincia);
        parcel.writeString(this.Localidad);
        parcel.writeFloat(this.Valoracion);
        parcel.writeString(this.Comentarios);

    }

    protected Poblacion(Parcel in) {
        this.Provincia = in.readString();
        this.Localidad = in.readString();
        this.Valoracion = in.readFloat();
        this.Comentarios = in.readString();
    }

    public static final Parcelable.Creator<Poblacion> CREATOR = new Parcelable.Creator<Poblacion>() {
        @Override
        public Poblacion createFromParcel(Parcel source) {
            return new Poblacion(source);
        }

        @Override
        public Poblacion[] newArray(int size) {
            return new Poblacion[size];
        }
    };
}
