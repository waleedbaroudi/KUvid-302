package ui.windows;

public class ErrorWindow {
	
	private String error;
	
	
	public ErrorWindow() {
		
		this.error = "There is an Error!";
		
	}
	
	public ErrorWindow(String error) {
		this.error = error;
	}
	
	
	public void popError() {
		
		System.out.println(this.error);
		
	}

}
