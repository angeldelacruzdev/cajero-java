import interfaces.BancoInterface;

public class Banco implements BancoInterface {

		private static final double COMISION_TRANSFERENCIA = 0.0015; // 0.15%
		
		private Banco() {}
		
		public static double calcularComision(double monto) {
			return monto * COMISION_TRANSFERENCIA;
		}
	
}
