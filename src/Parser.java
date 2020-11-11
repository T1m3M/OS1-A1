
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
			"clear"};
	
	String[] spInput;

	public boolean parse(String input) {
		
		// Split input to parse easily
		spInput = input.split(" ");
		
		// Searching if it's a valid command
		for(int i=0; i < allCmds.length; i++) {
			if(allCmds[i].equals(spInput[0])) {
				this.cmd = spInput[0];
				return true;
			}
		}
		
		return false;
	}
	
	
}
