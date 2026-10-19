package EjerciciosMultihilo.Ejercicio2;

public class Almacen {
    private boolean puertaOcupada = false;
    private int producto;
    private int contProducto = 0;
    private int noProducto = 0;
    private int noEntran = 0;

    public Almacen(int producto) {
        this.producto = producto;
    }

    public synchronized boolean intentarPuerta() {

    }
}
