import java.time.LocalDate;

public class CuentaAhorro extends CuentaBancaria {

	private final double LIMITE_DIARIO = 10000;
	private double retiradoHoy = 0;
	private LocalDate fechaUltimoRetiro = LocalDate.now();

	public CuentaAhorro(String numeroCuenta, double balanceInicial, int pin) {
		super(numeroCuenta, balanceInicial, pin);
		
	}

	@Override
	public boolean retirar(double monto) {
	 
		if (!LocalDate.now().equals(fechaUltimoRetiro)) {
			retiradoHoy = 0;
			fechaUltimoRetiro = LocalDate.now();
		}

		if (monto <= 0) {
			agregarTransaccion("Retiro fallido", monto, "Monto inválido");
		}

		if (monto > balance) {
			agregarTransaccion("Retiro fallido", monto, "Fondos insuficientes");
			return false;
		}

		if (retiradoHoy + monto > LIMITE_DIARIO) {
			agregarTransaccion("Retiro fallido", monto, "Excede el límite diario de " + LIMITE_DIARIO);
			return false;
		}

		balance -= monto;
		retiradoHoy += monto;

		agregarTransaccion("Retiro", monto, "Retiro exitoso. Llevas retirado hoy: " + retiradoHoy);

		return true;
	}

}
