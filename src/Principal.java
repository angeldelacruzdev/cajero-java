
public class Principal {
	public static void main(String[] args) {
		
		CuentaBancaria corriente = new CuentaCorriente("002",50000, 1234);
		CajeroATM cajero = new CajeroATM(corriente);
		
		CuentaBancaria ahorro = new CuentaAhorro("001",50000, 1234);
		CajeroATM cajero2 = new CajeroATM(ahorro);
		
		try {
			cajero.iniciarSession(1234);
			
			cajero.realizarTransferencia(corriente, 500);
			cajero.realizarRetiro(4000);
			cajero.realizarRetiro(3000);
				
				
	
			
			cajero.mostrarBalance();
			cajero.mostrarHistorial();
			
			
			
			cajero2.mostrarBalance();
			cajero2.mostrarHistorial();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
