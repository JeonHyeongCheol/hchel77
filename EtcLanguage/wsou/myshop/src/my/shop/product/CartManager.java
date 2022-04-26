package my.shop.product;

import java.util.HashMap;
import java.util.Hashtable;

public class CartManager {
	private Hashtable hCart = new Hashtable<>();
	
	// 해당 클라이언트에서만 사용하기 때문에 DB를 사용하지 않고 자바에 있는 HashTable에서 담아서 사용.
	public void addCart(OrderBean obean) { // 장바구니 담기
		String product_no = obean.getProduct_no(); // 상품번호를 담음.
		int quantity = Integer.parseInt(obean.getQuantity()); // 주문수량을 담음.
		if(quantity > 0) {
			// 동일 상품 주문 시에는 주문 수량만 증가
			if(hCart.containsKey(product_no)) { // 동일한 상품번호가 있는지 확인.
				OrderBean temp = (OrderBean)hCart.get(product_no); // 가방을 읽음. 그 안에는 product_no와 quantity가 들어있음.
				// 넣을 때는 put, 가져올 때는 get
				quantity += Integer.parseInt(temp.getQuantity()); // 기존에 있던 값과 들어온 값을 더함.
				temp.setQuantity(Integer.toString(quantity)); // quantity가 String이기 때문에 바꿔줌.
				hCart.put(product_no, temp); // hash table에 더한 값을 넣어줌.
				System.out.println("주문수 : " + quantity);
			} else { // 새로운 상품일 때
				hCart.put(product_no, obean); // Hashtable에 상품번호를 담음. product_no에 obean을 담는다.				
			}
		}
	}
	
	public Hashtable getCartList() { // 장바구니 목록
		return hCart;
	}
	
	public void updateCart(OrderBean obean) { // 장바구니 업데이트
		String product_no = obean.getProduct_no();
		hCart.put(product_no, obean); // 장바구니에 넣을 시 계속 추가.
	}
	
	public void deleteCart(OrderBean obean) { // 장바구니 삭제
		String product_no = obean.getProduct_no();
		hCart.remove(product_no); // 장바구니에 있는 값 삭제.
	}
	
	
}
