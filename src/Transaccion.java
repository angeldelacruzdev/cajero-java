import java.time.LocalDateTime;

public class Transaccion {

	private final String tipo;
	private final double monto;
	private final LocalDateTime fecha;
	private final String detalles;

	public Transaccion(String tipo, double monto, String detalles) {
		this.tipo = tipo;
		this.monto = monto;
		this.detalles = detalles;
		this.fecha = LocalDateTime.now();
	}
	
	
	public String getTipo() {
		return tipo;
	}
	
	public double getMonto() {
		return monto;
	}
	
	public LocalDateTime getFecha() {
		 return fecha;
	}
	
	public String getDetalle() {
		return detalles;
	}

}
