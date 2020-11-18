package ui.windows;

/**
 * Handles the error frames that might appear throughout the program.
 */
public class ErrorWindow {

    private final String errorMessage; //error message to be displayed

    /**
     * default constructor. specifies a default error message.
     */
    public ErrorWindow() {
        this.errorMessage = "There is an Error!";
    }

    /**
     * constructor that specifies the error message.
     * @param errorMessage message to be displayed for the user
     */
    public ErrorWindow(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * triggers the error window
     */
    public void popError() {
        System.out.println(this.errorMessage);
    }

}
