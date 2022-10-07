package example;

import java.io.Serializable;

public class LoadCash implements Serializable{
 
	 private int c2000;
	 private int c500;
	 private int c100;
	 
	public LoadCash(int c2000, int c500, int c100) {
		this.c2000=c2000;
		this.c500=c500;
		this.c100=c100;
	}

	public int getC2000() {
		return c2000;
	}

	public void setC2000(int c2000) {
		this.c2000 = c2000;
	}

	public int getC500() {
		return c500;
	}

	public void setC500(int c500) {
		this.c500 = c500;
	}

	public int getC100() {
		return c100;
	}

	public void setC100(int c100) {
		this.c100 = c100;
	}
	
}
