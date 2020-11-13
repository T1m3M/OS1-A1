import java.io.IOException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws IOException {
		
		Parser parser = new Parser();
		Terminal terminal = new Terminal();
		
		String myCmd;
		String[] myArgs;

		Scanner input = null;
		String userInput;
		
		while(true) {
			// Taking user input
			input = new Scanner(System.in);
			System.out.print("$ ");
			
			userInput = input.nextLine();
			
			// checking if command and arguments are valid
			while( parser.parse(userInput) ) {
				
				// getting the command and its arguments values
				myCmd = parser.getCmd();
				myArgs = parser.getArguments();
				
				// Calling the specified terminal command
				switch(myCmd) {
				case "cd":
					terminal.cd(myArgs[0]);
					break;
					
				case "ls":
					terminal.ls();
					break;
					
				case "cat":
					terminal.cat(myArgs);
					break;
					
				case "rm":
					terminal.rm(myArgs);
					break;
					
				case "pwd":
					terminal.pwd();
					break;
					
				case "exit":
					terminal.exit();
					break;
				}
				
				// Checking if there's an operator & Parsing the next command
				if(parser.opExist())
					userInput = userInput.substring(parser.getNextCmdPos());
				else {
					System.out.print("$ ");
					userInput = input.nextLine();
				}
			}
			
		}
	}

}
