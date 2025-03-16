package homework;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class VM {
	
	private static boolean run = true;
	private static List <VMDTO> vmList;
	public static Scanner scn = new Scanner(System.in);
	
	// 상품 선택
	public static String selectPrint() {
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.println("0 - 4 중 과자를 하나 선택해주세요. (자판기 종료 : 5)");
		System.out.print("종류 > ");
		return (scn.nextLine()).trim();	
	}
	
	// 개수 선택
	public static int cntPrint() {
		
		System.out.println("개수를 선택해주세요.");
		System.out.print("개수 > ");
		
		return VM.exception(scn.nextLine().trim());
	}
	
	// 현금 입력
	private static int insertCash() {

		System.out.println("현금을 투입해주세요.");
		System.out.print("금액 > ");
		
		return VM.exception(scn.nextLine().trim());
	}
	
	// 입력 받은 값 예외 처리
	// 정수 이외의 값은 parseInt 과정에서 예외 발생 -> 숫재 재입력 출력문으로 예외 처리 후 실행.
	// 음수 또는 0은 -1을 리턴시켜서 숫자 재입력 출력문 처리
	public static int exception(String val) {
		int cnt = -1;
		try { 
			if(Integer.parseInt(val) > 0) cnt = Integer.parseInt(val);
		} catch(Exception e) {
			System.out.println("숫자를 다시 입력해주세요.");
		}	
		return cnt;
	}
	
	public static List<VMDTO> getVmList() {
		return VM.vmList;
	}
	
	public static void vmProgram(String tmp, VMDTO vDto) {
		
		int cntPrint;
		int cash;
		
		cntPrint = VM.cntPrint();
		
		// 배드 코드 -> 개선 필요
		if(cntPrint != -1) {
			if(vDto.checkStock(tmp, VM.getVmList(), cntPrint)) {
				cash = VM.insertCash();
				if(cash != -1) {
					vDto.sum(tmp, cash, VM.getVmList(), cntPrint);
				} else {
					System.out.println("숫자를 입력해주세요.");
				}
			}
		} else System.out.println("숫자를 입력해주세요.");
	}

	public static void main(String[] args) {

		VMDTO vDto = new VMDTO();
		VM.vmList = new LinkedList<>(Arrays.asList( // 과자 가격, 재고 초기화
				new VMDTO(1000, 10),
				new VMDTO(2000, 20),
				new VMDTO(3000, 5),
				new VMDTO(4000, 10),
				new VMDTO(5000, 30))
		);
		
//		for(VMDTO dto : vmList) {
//			System.out.println(dto.getPrice() + " | " + dto.getStock());
//		}
		
		program:
		while(run) {
			String tmp = VM.selectPrint();
			switch(tmp) {
				case "0", "1", "2", "3", "4" -> {
					VM.vmProgram(tmp, vDto);
				}
				case "5" -> {
					System.out.println("자판기 종료");
					break program;
				}
				default -> System.out.println("잘못된 상품 번호를 입력하셨습니다."); // default로 selectPrint 예외까지 처리
			}
		}		
	}
}
