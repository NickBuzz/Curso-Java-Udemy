package servicio;

public interface ICatalogoPeliculas {
    String NOMBRE_RECURSO = "peliculas.txt";

    void agregarPelicula(String nombre);

    void listarPeliculas();

    void buscarPelicula(String buscar);

    void iniciarCatalogoPeliculas();
}
