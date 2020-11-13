import java.util.ArrayList;

public class Parser {
	String[] args;
	String cmd;
	
	// All valid commands
	public static String[] allCmds = {
			"cd",
			"ls",
			"cp",
			"cat",
			"more",
			"mkdir",
			"rmdir",
			"mv",
			"rm",
			"args",
			"date",
			"help",
			"pwd",
			"clear",
			"exit"};
	
	String[] spInput;
	ArrayList<String> elements;
	String part;
	
	boolean openQuotes;
	boolean opExist; // for operators

	public boolean parse(String input) {
		
		elements = new ArrayList<String>();
		
		part = "";
		openQuotes = false;
		opExist = false;
		
		for(int n=0; n < input.length(); n++) {
			
			// If there's a space and not inside quotes
			if(input.charAt(n) == ' ' && !openQuotes) {
				elements.add(part);
				part = "";
				continue;
			}
			
			// If there's a start quote
			else if (input.charAt(n) == '"' && !openQuotes) {
				openQuotes = true;
				continue;
			}

			// If there's an end quote
			else if (input.charAt(n) == '"' && openQuotes) {
				openQuotes = false;
				elements.add(part);
				part = "";
				n += 1; // to escape the space after end quote
				continue;
			}
			
			// If there's an operator
			else if (input.charAt(n) == '|' || input.charAt(n) == '>') {
				opExist = true;
				break;
			}
			
			// Store a character
			part += input.charAt(n);
			
			// Checking if that's the last element
			if (n == input.length() - 1)
				elements.add(part);
		}
		
		spInput = new String[elements.size()];
		for(int x=0; x < elements.size(); x++)
			spInput[x] = elements.get(x);
		

		for(int z=0; z < elements.size(); z++)
			System.out.println(elements.get(z));
		
		System.out.println(opExist);
		
		// Is it a valid command?
		for(int i=0; i < allCmds.length; i++) {
			if(allCmds[i].equals(spInput[0])) {
				cmd = spInput[0];
				
				// Are these valid arguments?
				if(cmd.equals("ls") || cmd.equals("more") || cmd.equals("args") ||
						cmd.equals("date") || cmd.equals("help") || cmd.equals("pwd") ||
						cmd.equals("clear") || cmd.equals("exit")) {
					if(spInput.length - 1 == 0)
						return true;

					else {
						System.out.println("ERROR: " + cmd + " requires no arguments!");
						return false;
					}
				}
				
				else if(cmd.equals("cd") || cmd.equals("mkdir") ||
						cmd.equals("rmdir") || cmd.equals("rm")) {
					if(spInput.length - 1 == 1) {
						// Storing arguments
						args = new String[1];
						args[0] = spInput[1];
						
						return true;
					}
					
					else {
						System.out.println("ERROR: " + cmd + " requires 1 argument!");
						return false;
					}
				}
				
				else if(cmd.equals("cp") || cmd.equals("mv")) {
					if(spInput.length - 1 == 2) {
						// Storing arguments
						args = new String[2];
						args[0] = spInput[1];
						args[1] = spInput[2];
						return true;
					}
					
					else {
						System.out.println("ERROR: " + cmd + " requires 2 arguments!");
						return false;
					}
				}
				
				else if(cmd.equals("cat")) {
					if(spInput.length - 1 >= 1) {
						args = new String[spInput.length - 1];
						
						// Storing arguments
						for(int j=0; j < spInput.length - 1; j++)
							args[j] = spInput[j+1];

						return true;
					}
					
					else {
						System.out.println("ERROR: " + cmd + " requires atleast 1 argument!");
						return false;
					}
				}
				
			}
		}
		
		
		
		System.out.println("ERROR: UNKNOWN COMMAND!");
		return false;
		
	}

	public String getCmd() {return cmd;}
	public String[] getArguments() {return args;}
	public boolean opExist() {return opExist;}
	
}
