 package example;

import java.io.Serializable;

public class CustomerDetails implements Serializable{

	private int accno;
	private String accholder;
	private int pin;
	private int balance; 
	public CustomerDetails(int acc, String holder, int pin2, int balance2) {
		this.accno=acc;
		this.accholder=holder;
		this.pin=pin2;
		this.balance=balance2;
	}
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public String getAccholder() {
		return accholder;
	}
	public void setAccholder(String accholder) {
		this.accholder = accholder;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
    
}
