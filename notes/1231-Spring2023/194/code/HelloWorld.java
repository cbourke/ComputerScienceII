package uno.ece;

/**
 * This is a basic hello world program
 * <a href="https://en.wikipedia.org/wiki/Java_(programming_language)>more info on Java</a>
 * 
 * Author: Chris Bourke
 * Date: 2023-01-24
 * 
 * Variables can be denoted with <code>x</code>
 * 
 * @author cbourke
 *
 */
public class HelloWorld {

	public static void main(String[] args) {
		
		//print Hello World:		
		System.out.print("Hello World!");
		/* make some dummy variables
		 * so we can use them
		 */
		
		int x = 42;
		double y = 3.14159;
		String s = "Goodbye";
		System.out.printf("x = %d, y = %10.2f, s = %s\n", x, y, s);
	}

}
