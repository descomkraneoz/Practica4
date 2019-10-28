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
        super();
        this.Provincia = provincia;
        this.Localidad = localidad;
        this.Valoracion = valoracion;
        this.Comentarios = comentarios;
    }

    public Poblacion() {
        super();

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
     * use la herramienta http://www.parcelabler.com/
     */

    protected Poblacion(Parcel in) {
        Provincia = in.readString();
        Localidad = in.readString();
        Valoracion = in.readByte() == 0x00 ? null : in.readFloat();
        Comentarios = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Provincia);
        dest.writeString(Localidad);
        if (Valoracion == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeFloat(Valoracion);
        }
        dest.writeString(Comentarios);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Poblacion> CREATOR = new Parcelable.Creator<Poblacion>() {
        @Override
        public Poblacion createFromParcel(Parcel in) {
            return new Poblacion(in);
        }

        @Override
        public Poblacion[] newArray(int size) {
            return new Poblacion[size];
        }
    };
}
