package by.zti.main;

import java.util.Scanner;

public class Main {
	public static Strings s = new Strings();
	public static Integers i = new Integers();
	public static Thread ts = new Thread(s);
	public static Thread ti = new Thread(i);
	public static Scanner scn = new Scanner(System.in);
	public static String com = "";
	public static boolean isRunning = true;
	public static boolean autoRevive = false;

	public static void main(String[] args) {
		ts.start();
		ti.start();
		System.out.println("Program starts...\nType /help to see all commands");
		while (isRunning) {
			scn = new Scanner(System.in);
			System.out.println("----------------------||----------------------");
			System.out.print("Enter command: ");
			com = scn.next();
			checkCommand();
			autoRevive();
		}
		s.end();
		i.end();
		scn.close();
		System.out.println("Exit!");
	}

	private static void autoRevive() {
		if (autoRevive) {
			if (!ts.isAlive()) {
				revive("String");
			}
			if (!ti.isAlive()) {
				revive("Integer");
			}
		}
	}

	public static void checkCommand() {
		switch (com.toLowerCase()) {

		case "/exit":
			isRunning = false;
			break;

		case "/help":
			System.out.println("/help - list of all commands");
			System.out.println("/exit - exit the program");
			System.out.println("/int <Integer> - pass next int to Integers thread");
			System.out.println("/str <String> - pass next string to Strings thread");
			System.out.println("/stats - all threads 'alive' status");
			System.out.println("/revive <'Integer','String'> - restarts named Thread");
			System.out.println("/auto_revive - switches auto revive thread option");
			break;

		case "/int":
			if (!ti.isAlive()) {
				System.out.println("ERROR - Integer thread is dead!");
				break;
			}
			String iarg = scn.next();
			i.setNum(iarg);
			hold(100);
			break;

		case "/str":
			if (!ts.isAlive()) {
				System.out.println("ERROR - String thread is dead!");
				break;
			}
			scn.skip(" ");
			String sarg = scn.nextLine();
			s.setStr(sarg);
			hold(100);
			break;

		case "/stats":
			System.out.println("Integer thread alive status: " + ti.isAlive());
			System.out.println("String thread alive status: " + ts.isAlive());
			break;

		case "/revive":
			revive(scn.next());
			break;

		case "/auto_revive":
			autoRevive = !autoRevive;
			System.out.println("Auto Revive otipon is now: " + autoRevive);
			break;

		default:
			System.out.println("ERROR - Unknown command!");
			break;
		}
	}

	public static void revive(String arg) {
		switch (arg.toLowerCase()) {
		case "integer":
			if (ti.isAlive()) {
				System.out.println("ERROR - Integer thread is still alive!");
				break;
			}
			i = new Integers();
			ti = new Thread(i);
			ti.start();
			hold(100);
			if (ts.isAlive()) {
				System.out.println("Integer thread is revived!");
			}
			break;

		case "string":
			if (ts.isAlive()) {
				System.out.println("ERROR - String thread is still alive!");
				break;
			}
			s = new Strings();
			ts = new Thread(s);
			ts.start();
			hold(100);
			if (ts.isAlive()) {
				System.out.println("String thread is revived!");
			}
			break;

		default:
			System.out.println("ERROR - unknown thread!");
			break;
		}
	}

	private static void hold(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
