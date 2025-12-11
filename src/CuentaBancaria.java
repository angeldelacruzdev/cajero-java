import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exceptions.FondosInsuficientesException;
import interfaces.Transfer;

public class CuentaBancaria implements Transfer<CuentaBancaria> {

	private final String numeroCuenta;
	protected double balance;
	private int pin;

	private final List<Transaccion> historial = new ArrayList<>();

	public CuentaBancaria(String numeroCuenta, double balanceInicial, int pin) {
		this.numeroCuenta = numeroCuenta;
		setBalance(balanceInicial);
		setPin(pin);

		agregarTransaccion("Apertura: ", balanceInicial, "Cuenta creada");
	}

	@Override
	public boolean transfer(CuentaBancaria desntino, double amount) {
		if (desntino == null) {
			agregarTransaccion("Transferencia fallida", 0, "Cuenta de destino inválida");
			throw new IllegalArgumentException("Cuenta de destino inválida.");
		}

		if (amount <= 0) {
			agregarTransaccion("Transferencia fallida", amount, "Monto inválido");
			return false;
		}

		try {
			this.retirar(amount);
		} catch (FondosInsuficientesException e) {

			System.out.println("Error al retirar: " + e.getMessage());
		}

		double comision = Banco.calcularComision(amount);
		desntino.balance += amount;

		desntino.agregarTransaccion("Transferencia recibida", amount - comision,
				"Transferencia desde cuenta " + this.getNumeroCuenta());
		desntino.agregarTransaccion("Comisión aplicada: ", comision, "  " + this.getNumeroCuenta());

		this.agregarTransaccion("Transferencia realizada", amount, "Enviada a cuenta " + desntino.getNumeroCuenta());

		return true;
	}

	protected void agregarTransaccion(String tipo, double monto, String detalles) {
		historial.add(new Transaccion(tipo, monto, detalles));
	}

	public List<Transaccion> obtenerHistorial() {
		return Collections.unmodifiableList(historial);
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	private void setBalance(double balance) {
		if (balance < 0) {
			throw new IllegalArgumentException("El balance no puede ser negativo.");
		}

		this.balance = balance;
	}

	private void setPin(int pin) {
		if (pin < 1000 || pin > 9999) {
			throw new IllegalArgumentException("El pin debe ser de 4 dígitos");
		}

		this.pin = pin;
	}

	public boolean validarPin(int pinIngresado) {
		boolean valido = this.pin == pinIngresado;
		if (!valido) {
			agregarTransaccion("PIN Fallido", 0, "Intento fallido de acceso");
		}
		return valido;
	}

	public double consultarBalance() {
		agregarTransaccion("Consulta", balance, "Consulta de balance");
		return balance;
	}

	public void depositar(double monto) {
		if (monto < 0) {
			throw new IllegalArgumentException("El monto debe ser mayor que cero.");
		}

		this.balance += monto;
		agregarTransaccion("Depósito", monto, "Depósito realizado");
	}

	public boolean retirar(double monto) throws FondosInsuficientesException {
		if (monto <= 0) {
			throw new IllegalArgumentException("El monto deber ser positivo.");
		}

		if (monto > balance) {
			return false;
		}

		balance -= monto;
		agregarTransaccion("Retiro", monto, "Retiro exitoso");
		return true;
	}

}
