package stack;

public class StackOverflowException extends Exception {
	
	public StackOverflowException() {
		super("Pilha cheia");
	}

}
