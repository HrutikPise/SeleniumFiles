package Resources;

public enum EndToEndTestingEnumCucumber {
	Login("/api/ecom/auth/login"),
	AddProduct("/api/ecom/product/add-product"),
	OrderProduct("/api/ecom/order/create-order"),
	ViewProduct("/api/ecom/order/get-orders-details"),
	DeleteOrder("/api/ecom/order/delete-order/{order}"),
	DeleteProduct("/api/ecom/product/delete-product/{product_Id}"),
	AddToCart("/api/ecom/user/add-to-cart"),
	DeleteAddToProduct("/api/ecom/user/remove-from-cart/{userId}/{productId}");
	private String order;
	EndToEndTestingEnumCucumber(String order){
		this.order=order;
	}
	
	public String getOrder() {
		return order;
	}
	
	
}
