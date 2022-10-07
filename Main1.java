package example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main1 {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.println("===============================================");
		System.out.println("1.Check balance");
		System.out.println("2.Withdraw");
		System.out.println("3.Transfer");
		System.out.println("4.ATM balance");
		System.out.println("5.Eixt");
		System.out.println("===============================================");
		CustomerDetails c1 = new CustomerDetails(101, "Suresh", 2343, 25234);
		CustomerDetails c2 = new CustomerDetails(102, "Ganesh", 5432, 34123);
		CustomerDetails c3 = new CustomerDetails(103, "Magesh", 7854, 26100);
		CustomerDetails c4 = new CustomerDetails(104, "Naresh", 2345, 80000);
		CustomerDetails c5 = new CustomerDetails(105, "Harish", 1907, 103400);
		FileOutputStream fi = new FileOutputStream("CustomerDetails");
		ObjectOutputStream oi = new ObjectOutputStream(fi);
		oi.writeObject(c1);
		oi.writeObject(c2);
		oi.writeObject(c3);
		oi.writeObject(c4);
		oi.writeObject(c5);
		LoadCash l1 = new LoadCash(20, 100, 100);
		FileOutputStream f1 = new FileOutputStream("Denominations");
		ObjectOutputStream o1 = new ObjectOutputStream(f1);
		o1.writeObject(l1);
		f1.close();
		o1.close();
		FileInputStream fis1 = new FileInputStream("Denominations");
		ObjectInputStream ois1 = new ObjectInputStream(fis1);
		LoadCash l2 = (LoadCash) ois1.readObject();
		while (true) {
			System.out.println("===============================================");
			System.out.println("Enter the choice:");
			int choice = sc.nextInt();
			System.out.println("===============================================");
			if (choice == 5) {
				System.out.println("Thank you for Contacting Mariyamman Bank.");
				break;
			}
			switch (choice) {
			case 1: {
				System.out.println("Enter the acc.no to check the balance:");
				int checkacc = sc.nextInt();
				FileInputStream fis = new FileInputStream("CustomerDetails");
				ObjectInputStream ois = new ObjectInputStream(fis);
				CustomerDetails cc = (CustomerDetails) ois.readObject();
				if (checkacc <= 105) {
					while (cc != null) {
						if (cc.getAccno() == checkacc) {
							System.out.println("Balance you Have:" + cc.getBalance());
							break;
						}
						cc = (CustomerDetails) ois.readObject();
					}
				} else
					System.out.println("Invalid Acc.no");
				oi.close();
				fis.close();
				break;
			}
			case 2: {
				System.out.println("Enter the acc.no and pin: ");
				int waccno = sc.nextInt();
				int wpin = sc.nextInt();
				FileInputStream fis = new FileInputStream("CustomerDetails");
				ObjectInputStream ois = new ObjectInputStream(fis);
				CustomerDetails cc = (CustomerDetails) ois.readObject();
				while (cc != null ) {
					if (cc.getAccno() == waccno) {
						if (cc.getPin() == wpin) {
							System.out.println("Enter the Amount to be WithDrawed:");
							int withdrawamo = sc.nextInt();
							Withdrawamount w1 = new Withdrawamount();
							w1.withdrawing(cc, l2, withdrawamo);
							break;
						} else {
							System.out.println("Invaid Pin");
						}
					} else {
						System.out.println("Invalid Acc.no");
						break;
					}
					cc = (CustomerDetails) ois.readObject();
				}
				oi.close();
				break;
			}
			case 3: {
				System.out.println("Enter the Account.no of Sender:");
				int sender = sc.nextInt();
				System.out.println("Enter the Account.no of Reciver:");
				int Reciver = sc.nextInt();
				System.out.println("Enter the Amount to be send:");
				int amount = sc.nextInt();
				int flag = 0;
				int sbalance = 0, rbalance = 0;
				FileInputStream fis = new FileInputStream("CustomerDetails");
				ObjectInputStream ois = new ObjectInputStream(fis);
				CustomerDetails cc = (CustomerDetails) ois.readObject();
				if ((sender >= 101 && sender <= 105) && (Reciver >= 101 && Reciver <= 105)) {
					while (cc != null) {
						if (cc.getAccno() == sender) {
							if (amount <= cc.getBalance()) {
								sbalance = cc.getBalance() - amount;
								cc.setBalance(sbalance);
								FileOutputStream fi1 = new FileOutputStream("CustomerDetails");
								ObjectOutputStream oi1 = new ObjectOutputStream(fi1);
								oi.writeObject(cc);
							} else {
								flag = 1;
								System.out.println("Insuffient Balance in you Account");
							}
							break;
						}
						if (cc.getAccno() == Reciver) {
							rbalance = cc.getBalance() + amount;
							cc.setBalance(rbalance);
							FileOutputStream fi1 = new FileOutputStream("CustomerDetails");
							ObjectOutputStream oi1 = new ObjectOutputStream(fi1);
							oi.writeObject(cc);
							break;
						}
						cc = (CustomerDetails) ois.readObject();
					}
					oi.close();
					fis.close();
					if (flag == 0) {
						System.out.println("Transfer Successfull");
						System.out.println("Available Balance in your Account is :" + sbalance);
					}

				} else
					System.out.println("Check the Sender and Reciver Acc.no");
				break;
			}
			case 4: {
				int total = (l1.getC100() * 100) + (l1.getC2000() * 2000) + (l1.getC500() * 500);
				System.out.println("Available Balance:" + total);
				break;
			}
			}
		}
		ois1.close();
	}
}
