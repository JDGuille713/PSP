package EjerciciosMultihilo.Ejercicio2;

public class Almacen {
    private boolean puertaOcupada = false;
    private int producto;
    private int contProducto = 0;
    //private int noProducto = 0;
    private int noEntran = 0;

    public Almacen(int producto) {
        this.producto = producto;
    }

    public synchronized boolean intentarPuerta() {
        if (!puertaOcupada) {
            puertaOcupada = true;
            return true;
        }
        return false;
    }

    public synchronized void salir() {
        puertaOcupada = false;
    }

    public synchronized boolean cogerProducto() {
        if (producto > 0) {
            producto--;
            contProducto++;
            return true;
        }
      //  noProducto++;
        return false;
    }

    public synchronized void clienteDesiste() {
        noEntran++;
    }

    public synchronized void mostrar() {
        System.out.println(contProducto + " clientes se llevan producto.");
        //System.out.println(noProducto + " clientes han entrado y no tienen producto.");
        System.out.println(noEntran + " clientes se niegan a entrar.");
        System.out.println("Productos restantes: " + producto);
    }
}
