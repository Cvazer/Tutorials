package by.zti.main;

public class Integers implements Runnable {
	private String num = "";
	private boolean isRuning = true;

	@Override
	public void run() {
		while (isRuning) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {e.printStackTrace();}
			
			if (!num.contentEquals("")) {
				System.out.println("(Integer) - i gaot an " + Integer.parseInt(num) + "!");
				num = "";
			}
		}
		System.out.println("Integer - stop!");
	}

	public int getNum() {
		return Integer.parseInt(num);
	}

	public void setNum(String string) {
		this.num = string;
	}

	public void end() {
		this.isRuning = false;
	}

}
