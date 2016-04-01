package by.zti.main;

public class Strings implements Runnable {
	private String str = "";
	private boolean isRuning = true;

	@Override
	public void run() {
		while (isRuning) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {e.printStackTrace();}
			
			if (!str.contentEquals("")) {
				System.out.println("(String) - i gaot '" + str + "'!");
				str = "";
			}
		}
		System.out.println("String - stop!");
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public void end() {
		this.isRuning = false;
	}
}
