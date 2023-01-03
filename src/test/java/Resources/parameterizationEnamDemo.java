package Resources;

public enum parameterizationEnamDemo {
	addPlaceAPI("/Library/Addbook.php"),
	getPlaceAPI("/Library/GetBook.php"),
	deletePlaceAPI("/Library/DeleteBook.php"),
	updatePlaceAPI("/Library/UpdateBook.php");
	private String res;
	parameterizationEnamDemo(String res){
		this.res=res;
	}
	public String getRes() {
		return res;
	}

}
