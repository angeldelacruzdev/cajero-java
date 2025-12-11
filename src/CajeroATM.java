import exceptions.FondosInsuficientesException;

public class CajeroATM {
	CuentaBancaria cuenta;

	public CajeroATM(CuentaBancaria cuenta) {
		this.cuenta = cuenta;
	}

	public void iniciarSession(int pin) {
		if (!cuenta.validarPin(pin)) {
			throw new IllegalArgumentException("PIN incorrecto. Acceso denegado.");
		}

		System.out.println("Acceso concedido.");
	}

	public void mostrarBalance() {
		System.out.println("Su balance es: " + cuenta.consultarBalance());
	}

	public void realizarTransferencia(CuentaBancaria destino, double amount) {
		boolean exito = cuenta.transfer(destino, amount);

		if (exito) {
			System.out.println("Transerencia completa.");
		} else {
			System.out.println("Transerencia fallida.");
		}
	}

	public void realizarDeposito(double monto) {
		cuenta.depositar(monto);
		System.out.println("Depósito realizado correctamente.");
	}

	public void realizarRetiro(double monto) throws FondosInsuficientesException {
		boolean exito = cuenta.retirar(monto);
		if (exito) {
			System.out.println("Retiro realizado.");
		} else {
			throw new FondosInsuficientesException("Fondos insuficientes.");
		}
	}

	public void mostrarHistorial() {
		System.out.println("\n--- HISTORIAL DE TRANSACCIONES ---");
		for (Transaccion t : cuenta.obtenerHistorial()) {
			System.out.println(
					t.getFecha() + " | " + t.getTipo() + " | " + "Monto: " + t.getMonto() + " | " + t.getDetalle());
		}
	}
}
