import java.io.IOException;
import java.util.Scanner;

public class main {
	
	// Global variable to change command behavior if it has operator
	public static boolean hasOperator = false;

	public static void main(String[] args) throws IOException {
		
		Parser parser = new Parser();
		Terminal terminal = new Terminal();
		
		String myCmd;
		String[] myArgs;

		Scanner input = new Scanner(System.in);
		String userInput;
		
		while(true) {
			// Taking user input
			System.out.print("$ ");
			userInput = input.nextLine();
			
			// checking if command and arguments are valid
			while( parser.parse(userInput) ) {
				
				// getting the command and its arguments values
				myCmd = parser.getCmd();
				myArgs = parser.getArguments();
				
				hasOperator = parser.opExist();
				
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
					input.close();
					terminal.exit();
					break;
					
				case "|":
					terminal.pipe(myArgs);
					break;

				// ================ TAWATY ================
				case ">":
					terminal.redirect(myArgs[0]);
					break;
					
				case ">>":
					terminal.redirectAppend(myArgs[0]);
					break;
					
				case "mv":
					terminal.mv(myArgs[0], myArgs[1]);
					break;
					
				case "args":
					terminal.args(myArgs[0]);
					break;
					
				case "date":
					terminal.date();
					break;

				// ================ BAHAA ================
				case "cp":
					terminal.cp(myArgs[0], myArgs[1]);
					break;
					
				case "mkdir":
					terminal.mkdir(myArgs);
					break;
					
				case "rmdir":
					terminal.rmdir(myArgs);
					break;
					
				case "help":
					terminal.help();
					break;
					
				case "clear":
					terminal.clear();
					break;
				}
				
				// Checking if there's an operator & Parsing the next command
				if(hasOperator) {
					userInput = userInput.substring(parser.getNextCmdPos());
				}
				
				else {
					break;
				}
			}
			
		}
	}

}
