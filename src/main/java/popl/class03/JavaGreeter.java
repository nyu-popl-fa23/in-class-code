package popl.class03;

public class JavaGreeter {
	public static void main(String[] args) {
		// access message field of Scala object ScalaGreeter
		System.out.println(ScalaGreeter.message() + " from Java");
	}	
}