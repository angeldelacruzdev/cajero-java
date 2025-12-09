
public class CuentaCorriente extends CuentaBancaria {

	public CuentaCorriente(String numeroCuenta, double balanceInicial, int pin) {
		super(numeroCuenta, balanceInicial, pin);
	}
	
	
	
	@Override
	public boolean retirar(double monto) {
			
		if(monto < 0 ) {
			agregarTransaccion("Retiro fallido", monto, "Monto inválido");
			
			return false;
		}
		
		if (monto > balance) {
			agregarTransaccion("Retiro fallido", monto, "Fondos insuficientes");
			return false;
		}

		
		
		balance -= monto;
	    agregarTransaccion("Retiro", monto, "Retiro exitoso (sin límite)");
		return true;
		
	}

}
