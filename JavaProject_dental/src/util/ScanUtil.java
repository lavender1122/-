package util;

import java.util.Scanner;

public class ScanUtil   {
	// 스캐너를 손쉽게 사용할 수 있는 static 메서드를 가지고있음
	static Scanner sc = new Scanner(System.in);
	
	public static int menu() {
		return nextInt("선택");
	}
	
	public static String nextLine(String print) { //문구 반드시 사용함
		System.out.print(print);
		return nextLine();
	}
	
	private static String nextLine() { //문구를 입력하지 않으면 스캐너 못쓰게 막음(private)
		return sc.nextLine();
	}
	
	public static int nextInt(String print) {
		System.out.print(print);
		return nextInt();
	}
	
	private static int nextInt() {
		while(true) {
			try { //숫자 잘못쓴경우 필터
				int result = Integer.parseInt(sc.nextLine());
				return result;
			}catch (NumberFormatException e) {
				System.out.println("잘못 입력!!");
			}
		}
	}
}
