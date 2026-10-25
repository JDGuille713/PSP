package EjerciciosMultihilo.Ejercicio5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Peluqueria {
    private final int numSillas;
    private int clientesEsperando = 0;
    private int barberosDormidos = 0;

    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition clienteEspera = lock.newCondition();
    private final Condition barberoEspera = lock.newCondition();

    public Peluqueria(int numSillas) {
        this.numSillas = numSillas;
    }

    public boolean intentarEntrar(int idCliente) throws InterruptedException {
        lock.lock();
        try {
            if (clientesEsperando >= numSillas) {
                return false;
            }

            clientesEsperando++;
            System.out.println("-> Cliente " + idCliente + " se sienta. Sillas ocupadas: " + clientesEsperando + "/" + numSillas);

            if (barberosDormidos > 0) {
                barberoEspera.signal();
            }

            clienteEspera.await();
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void trabajarBarbero(int idBarbero) throws InterruptedException {
        while (true) {
            lock.lock();
            try {
                while (clientesEsperando == 0) {
                    barberosDormidos++;
                    System.out.println("[Barbero " + idBarbero + "] No hay clientes. Se va a dormir. (Barberos durmiendo: " + barberosDormidos + ")");
                    barberoEspera.await();
                    barberosDormidos--;
                }

                clientesEsperando--;
                System.out.println("[Barbero " + idBarbero + "] Llama a un cliente. Sillas libres restantes: " + (numSillas - clientesEsperando));

                clienteEspera.signal();

            } finally {
                lock.unlock();
            }

            Thread.sleep((long) (Math.random() * 1500 + 500));
            System.out.println("[Barbero " + idBarbero + "] Termina de afeitar a un cliente.");
        }
    }
}
