package EjerciciosMultihilo.Ejercicio2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Principal {
    public static void main(String[] args) {

        Almacen almacen = new Almacen(100);

        // Pool de 300 hilos para que los 300 clientes esten a la vez en la puerta
        ExecutorService executor = Executors.newFixedThreadPool(300);

        for (int i = 1; i <= 300; i++) {
            executor.execute(new Cliente(i, almacen));
        }

        executor.shutdown();   // no se aceptan mas tareas

        // Esperar a que terminen todas las tareas antes de mostrar el resumen
        while (executor.isTerminated() == false) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        almacen.mostrar();
    }
}