package EjerciciosMultihilo.Ejercicio1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Cafeteria {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 3; i++) {
            executor.execute(new Pedido(i));
        }
        executor.shutdown();
    }
}
