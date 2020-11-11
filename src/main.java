import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
		Parser parser = new Parser();
		
		while(true) {
			// Taking user input
			Scanner input = new Scanner(System.in);
			System.out.print("$ ");
			parser.parse(input.nextLine());
			
		}
	}

}
