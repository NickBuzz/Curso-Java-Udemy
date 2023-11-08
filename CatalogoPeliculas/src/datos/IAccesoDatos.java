package datos;

import domain.Pelicula;
import excepciones.AccesoDatosEx;
import excepciones.EscrituraDatosEx;
import excepciones.LecturaDatosEx;

import java.util.List;

public interface IAccesoDatos {
    public abstract boolean existe(String nombreArchivo) throws AccesoDatosEx;

    public abstract List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx;

    public abstract void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx;

    public abstract String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx;

    public abstract void crear(String nombreRecurso) throws AccesoDatosEx;

    public abstract void borrar(String nombreRecurso) throws AccesoDatosEx;
}
