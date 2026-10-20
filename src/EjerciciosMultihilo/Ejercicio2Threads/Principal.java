package EjerciciosMultihilo.Ejercicio2Threads;

public class Principal {
    public static void main(String[] args) {

        Almacen almacen = new Almacen(100);
        Cliente[] clientes = new Cliente[300];

        for (int i = 0; i < 300; i++) {
            clientes[i] = new Cliente(almacen);
            clientes[i].setName("Cliente " + (i + 1));
            clientes[i].start();
        }

        for (int i = 0; i < 300; i++) {
            try {
                clientes[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        almacen.mostrar();
    }
}

