
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

	public boolean parse(String input) {
		
		// Split input to parse easily
		spInput = input.split(" ");
		
		for(int i=0; i < allCmds.length; i++) {
			if(allCmds[i].equals(spInput[0])) {
				cmd = spInput[0];
				
				if(cmd.equals("ls") || cmd.equals("more") || cmd.equals("args") ||
						cmd.equals("date") || cmd.equals("help") || cmd.equals("pwd") ||
						cmd.equals("clear") || cmd.equals("exit")) {
					if(spInput.length - 1 == 0)
						return true;
				}
				
				else if(cmd.equals("cd") || cmd.equals("cat") || cmd.equals("mkdir") ||
						cmd.equals("rmdir") || cmd.equals("rm")) {
					if(spInput.length - 1 == 1) {
						args = new String[1];
						args[0] = spInput[1];
						
						return true;
					}
				}
				
				else if(cmd.equals("cp") || cmd.equals("mv")) {
					if(spInput.length - 1 == 2) {
						args = new String[2];
						args[0] = spInput[1];
						args[1] = spInput[2];
						return true;
					}
				}
				
				else {
					System.out.println("ERROR: WRONG ARGUMENTS!");
					return false;
				}
				
			}
		}
		
		System.out.println("ERROR: UNKNOWN COMMAND!");
		return false;
	}
	
	
}
