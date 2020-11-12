import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
		Parser parser = new Parser();
		String myCmd;
		String[] myArgs;
		
		while(true) {
			// Taking user input
			Scanner input = new Scanner(System.in);
			System.out.print("$ ");
			
			// checking if command and arguments are valid
			if( parser.parse( input.nextLine() ) ) {
				
				// getting the command and its arguments values
				myCmd = parser.getCmd();
				myArgs = parser.getArguments();
				
				
			}
			
		}
	}

}
