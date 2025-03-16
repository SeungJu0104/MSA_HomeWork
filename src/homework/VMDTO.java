package homework;

import java.util.List;

public class VMDTO {
	
	private static final int MAX_STOCK = 30;
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
	
	public static int getMaxStock() {
		return MAX_STOCK;
	}

	// 가격 입력 받기 전 재고 체크
	public boolean checkStock(String tmp, List<VMDTO> vmList, int cnt) {
		int idx = Integer.parseInt(tmp);
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

	public void addStock(int idx, int cnt, List<VMDTO> vmList) {
		
		if(cnt > 0) {
			int stock = vmList.get(idx).getStock() + cnt;	
			if(stock <= MAX_STOCK) {
				vmList.set(idx, new VMDTO(vmList.get(idx).getPrice(), stock));
				System.out.println("재고 추가 완료했습니다.");
			} else System.out.println("최대 재고 수량 초과했습니다.");
		} else System.out.println("재고 수량을 다시 입력해주세요.");
			
	}

	public void changePrice(int idx, int price, List<VMDTO> vmList) {
		vmList.set(idx, new VMDTO(price, vmList.get(idx).getStock()));
		System.out.println("상품 금액이 " + price +"원으로 변경됐습니다.");
	}

	public void getStockList(List<VMDTO> vmList) {
		int i = 0;
		for(VMDTO vm : vmList) {
			System.out.printf("%d번 상품 재고 : %d | %d번 상품 가격 : %d \n", i, vm.getStock(), i, vm.getPrice());
			i++;
		}
	}
	
}
