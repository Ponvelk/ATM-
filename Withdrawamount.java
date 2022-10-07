package example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Withdrawamount {
public void withdrawing(CustomerDetails cc,LoadCash l1,int amount) throws IOException, ClassNotFoundException {
	int totaatm=(l1.getC100()*100)+(l1.getC2000()*2000)+(l1.getC500()*500);
	if(amount>10000) {
		System.out.println("Invalid Amount(Maximum limit to withdraw is 10000");
	}
	else if(amount<100) {
		System.out.println("Invalid Amount(Minimum limit to withdraw is 100");
	}
	else if(amount>totaatm) {
		System.out.println("Amount insufficient in ATM");
	}
	else if(amount>cc.getBalance()) {
		System.out.println("Amount insufficient in your Account");
	}
	else if(amount<=totaatm) {
		cc.setBalance(cc.getBalance()-amount);
		FileOutputStream fi = new FileOutputStream("CustomerDetails");
		ObjectOutputStream oi = new ObjectOutputStream(fi);
		oi.writeObject(cc);
		FileInputStream fis = new FileInputStream("CustomerDetails");
		ObjectInputStream ois = new ObjectInputStream(fis);
		CustomerDetails cc1 = (CustomerDetails) ois.readObject();
		System.out.println("Available Balance :"+cc1.getBalance());
	}
	else if(amount<totaatm) {
		if(amount<=cc.getBalance()) {
			if(amount%2!=0) {
				int tot=amount;
				int c2000=amount/2000;
				if(l1.getC2000()>c2000) {
					l1.setC2000(l1.getC2000()-c2000);
					tot-=(c2000*2000);
				}
				int c500=(tot/500)-1;
				if(l1.getC500()>c500) {
					l1.setC500(l1.getC500()-c500);
					tot-=(c500*500);
				}
				int c100=tot/100;
				if(l1.getC100()>c100) {
					l1.setC100(l1.getC100()-c100);
					tot-=(c100*100);
				}
				if(tot==0) {
					System.out.println("2000 "+c2000+"500 "+c500+"100 "+c100);
				}
			}
		}
		else
			System.out.println("Insufficient Amount in Account");
	}
	else
		System.out.println("Insufficient Balance in ATM");
	
}
}
