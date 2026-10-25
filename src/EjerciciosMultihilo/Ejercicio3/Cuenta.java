package EjerciciosMultihilo.Ejercicio3;

public class Cuenta {
    private int saldo;

    public Cuenta(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized void ingresar(int cantidad) {
        saldo += cantidad;
    }

    public synchronized void retirar(int cantidad) {
        saldo -= cantidad;
    }

    public synchronized int getSaldo() {
        return saldo;
    }
}
