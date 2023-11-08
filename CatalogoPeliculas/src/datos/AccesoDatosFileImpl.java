package datos;

import domain.Pelicula;
import excepciones.AccesoDatosEx;
import excepciones.EscrituraDatosEx;
import excepciones.LecturaDatosEx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccesoDatosFileImpl implements IAccesoDatos{
    @Override
    public boolean existe(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        return archivo.exists();
    }

    @Override
    public List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx {
        File archivo = new File(nombreRecurso);
        List<Pelicula> peliculas = new ArrayList<>();
        try (BufferedReader entrada = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = entrada.readLine()) != null){
                peliculas.add(new Pelicula(linea));
            }
        }catch (IOException ex){
            throw new LecturaDatosEx("Excepcion al listar peliculas: " + ex.getMessage());
        }
        return peliculas;
    }

    @Override
    public void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx {
        File archivo = new File(nombreRecurso);
        try (PrintWriter salida = new PrintWriter(new FileWriter(archivo, anexar))){
            salida.println(pelicula.getNombre());
            System.out.println("Se ha escrito informacion al archivo: " + pelicula.getNombre());
        }catch (IOException ex){
            throw new EscrituraDatosEx("Excepcion al escribir peliculas: " + ex.getMessage());
        }
    }

    @Override
    public String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx {
        File archivo = new File(nombreRecurso);
        String resultado = null;
        try (BufferedReader entrada = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int indice = 1;
            while ((linea = entrada.readLine()) != null){
                if (buscar != null && buscar.equalsIgnoreCase(linea)){
                    resultado = "Pelicula " + linea + " encontrada en el indice " + indice;
                }
                indice++;
            }
        }catch (IOException ex){
            throw new LecturaDatosEx("Excepcion al buscar pelicula: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public void crear(String nombreRecurso) throws AccesoDatosEx {
        File archivo = new File(nombreRecurso);
        try (PrintWriter salida = new PrintWriter(new FileWriter(archivo))){
            System.out.println("Se ha creado el archivo");
        }catch (IOException ex){
            throw new AccesoDatosEx("Excepcion al crear archivo: " + ex.getMessage());
        }
    }

    @Override
    public void borrar(String nombreRecurso) {
        File archivo = new File(nombreRecurso);
        if (archivo.exists()) archivo.delete();
        System.out.println("Se ha borrado el archivo");
    }
}
