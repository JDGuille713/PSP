package EjerciciosMultihilo.Ejercicio3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Principal {
    public static void main(String[] args) {
        Cuenta cuenta = new Cuenta(100);

        ExecutorService executor = Executors.newFixedThreadPool(64);

        for (int i = 0; i < 40; i++) {
            executor.submit(new Movimiento(cuenta, 100, true));
        }

        for (int i = 0; i < 20; i++) {
            executor.submit(new Movimiento(cuenta, 50, true));
        }

        for (int i = 0; i < 60; i++) {
            executor.submit(new Movimiento(cuenta, 20, true));
        }

        for (int i = 0; i < 40; i++) {
            executor.submit(new Movimiento(cuenta, 100, false));
        }

        for (int i = 0; i < 20; i++) {
            executor.submit(new Movimiento(cuenta, 50, false));
        }

        for (int i = 0; i < 60; i++) {
            executor.submit(new Movimiento(cuenta, 20, false));
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("Saldo inicial: 100 €");
        System.out.println("Total Ingresos: (40*100) + (20*50) + (60*20) = 6.200€");
        System.out.println("Total Retiradas: (40*100) + (20*50) + (60*20) = 6.200€");
        System.out.println("Saldo final: " + cuenta.getSaldo() + "€");

        if (cuenta.getSaldo() == 100) {
            System.out.println("El saldo final es exactamente 100€.");
        } else {
            System.out.println("El saldo final no coincide con lo esperado.");
        }
    }
}
