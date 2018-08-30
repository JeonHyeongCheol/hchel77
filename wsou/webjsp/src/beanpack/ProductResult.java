package beanpack;

public class ProductResult {
	private Product product;
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getSaleResult() {
		return product.getJungga() - product.getSale(); 
	}
	
	public String getResult() {
		return product.getName() + "는 " + getSaleResult() + "원 입니다";
	}
}
