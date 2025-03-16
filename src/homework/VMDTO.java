package homework;

import java.util.List;

public class VMDTO {
	
	private int change = 0;
	private int price;
	private int stock;
	
	public VMDTO() {}
	
	public VMDTO(int price, int stock) {
		this.price = price;
		this.stock = stock;
	}

	public int getChange() {
		return change;
	}

	public int getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}
	
	// 가격 입력 받기 전 재고 체크
	public boolean checkStock(String tmp, List<VMDTO> vmList, int cnt) {
		int idx = Integer.parseInt(tmp); // 너무 길어서 idx 변수에 담아 축약	
		if(vmList.get(idx).getStock() >= 0 & vmList.get(idx).getStock() >= cnt) {
			if(vmList.get(idx).getStock() != 0) {
				System.out.println("현재 해당 상품 재고 : " + vmList.get(idx).getStock());
				return true;
			}
			else {
				System.out.println("보유 재고가 없습니다.");
				return false;
			}
		}else {
			System.out.println("재고가 부족합니다.");
			return false;
		}
			
	}
	
	// 실제 출고
	public void release(String tmp, List<VMDTO> vmList, int cnt) {
		int idx = Integer.parseInt(tmp);
		vmList.set(idx, new VMDTO(vmList.get(idx).getPrice(), vmList.get(idx).getStock() - cnt));
		System.out.println("출고 완료");
	}
	
	// 합산 가격 (입력 받은 현금 - (입력한 개수 * 각 상품 가격))
	public void sum(String tmp, int cash, List<VMDTO> vmList, int cnt) {
		int idx = Integer.parseInt(tmp);
		change =  cash - (vmList.get(idx).getPrice() * cnt);
		if(change >= 0) {
			 System.out.println("잔액은 " + change +"원 입니다.");
			 release(tmp, vmList, cnt);
			 change = 0;
		}
		else System.out.println("현금이 부족합니다.");		
	}
	
}
