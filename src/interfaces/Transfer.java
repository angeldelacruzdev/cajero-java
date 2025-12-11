package interfaces;

public interface Transfer<CuentaBancaria> {
 
	boolean transfer(CuentaBancaria desntino, double amount);
}
