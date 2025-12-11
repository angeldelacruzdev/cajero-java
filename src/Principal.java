
public class Principal {
	public static void main(String[] args) {
		
		CuentaBancaria corriente = new CuentaCorriente("002",10000000, 1234);
		CajeroATM cajero = new CajeroATM(corriente);
		
		CuentaBancaria ahorro = new CuentaAhorro("001",50, 1234);
		CajeroATM cajero2 = new CajeroATM(ahorro);
			
		try {
			cajero.iniciarSession(1234);
			System.out.println("\n--- Cuenta Corriente ---");
			cajero.realizarTransferencia(ahorro, 50000);
	 
			
			cajero.mostrarBalance();
			 
		} catch (Exception e) {
			System.out.println(e.getMessage() );
		}
		
		try {
			cajero2.iniciarSession(1234);
	 
			
			System.out.println("\n--- Cuenta Ahorros ---");
			cajero2.mostrarBalance();
			cajero2.realizarRetiro(2500);
			cajero2.realizarRetiro(2500);
			cajero2.mostrarBalance();
		 
			cajero2.realizarRetiro(2500);
			cajero2.realizarRetiro(2500);
			cajero2.realizarRetiro(2500);
			cajero2.mostrarBalance();
			cajero2.mostrarHistorial();
		 
		} catch (Exception e) {
			System.out.println(e.getMessage() );
		}
	}
}
