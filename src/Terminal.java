import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Terminal {
	
	private static String pwd = "C:\\"; // default
	private static File file = null;
	private static List<Byte> stdin = new ArrayList<Byte>();
	
	public void cd(String path) throws IOException {
		
		// checking if it's for home directory
		if(path.equals("~"))
			pwd = "C:\\";
		
		else {
			file = new File(path);

			// Checking if path is relative or absolute
			if( !(file.isAbsolute()) )
				file = new File(pwd + "\\" + path);

			// Checking if it exists
			if(file.exists()) {
				
				// Checking if it's a directory and not a file
				if(file.isDirectory()) {
					
					// canonical resolves .. in paths by default
					pwd = file.getCanonicalPath();
					
				} else {
					System.out.println("ERROR: only directories can be accessed!");
				}
				
			} else {
				System.out.println("ERROR: no such file or directory!");
			}	
		}

	}
	
	public void ls() {
		File[] files = new File(pwd).listFiles();
		
		for (int i=0; i < files.length; i++) {
			
			// Don't print hidden files
			if(files[i].isHidden()) continue;

			// Print files and concatenate a slash if directory
			System.out.print(files[i].getName());
			if(files[i].isDirectory())
				System.out.print("\\");
			
			System.out.print("\n");	
        }
	}
	
	public void cat(String[] paths) throws IOException {
		byte[] fileContent;
		
		// Iterating through each file
		for(int i=0; i < paths.length; i++) {
			file = new File(paths[i]);
			
			// Resolving relative files
			if( !(file.isAbsolute()) )
				file = new File(pwd + "\\" + paths[i]);
				
			
			// Checking if it's a file and it exists
			if(file.exists() && file.isFile()) {
				
				fileContent = Files.readAllBytes(file.toPath());

				
				// Iterates through each bytes
				for(int j=0; j < fileContent.length; j++) {
					
					// If there's an operator redirect to stdin
					if(main.hasOperator)
						stdin.add(fileContent[j]);
					
					// Printing each char to the screen
					else {
						System.out.print((char)(fileContent[j] & 0xFF));	
					}
				}
				
			} else {
				System.out.println("ERROR: " + file.getName() + " file doesn't exists!");
			}
			
			
		}
		
	}
	
	public void rm(String[] paths) {

		for(int i=0; i < paths.length; i++) {
			file = new File(paths[i]);
			
			// Resolving relative files
			if( !(file.isAbsolute()) )
				file = new File(pwd + "\\" + paths[i]);
	
			// Checking if it exists
			if(file.exists()) {
				
				// Checking if it's a directory and not a file
				if(file.isFile()) {
					
					// Deleting the file
					file.delete();
					
				} else {
					System.out.println("ERROR: only files can be deleted!");
				}
				
			} else {
				System.out.println("ERROR: no such file or directory!");
			}
		}

	}
	
	public void pwd() {
		System.out.println(pwd);
	}
	
	public void exit() {
		System.exit(0);
	}
	
	/***************** start operators *****************/
	public void pipe(String[] args) {
		
		switch(args[0]) {
		case "more":
			more();
			break;
		}
		
		// Reseting standard input
		stdin = new ArrayList<Byte>();
	}
	
	
	// ================ TAWATY ================
	public void redirect(String sourcePath) {
		
		// Code Here ">" operator
		
		// Reseting standard input
		stdin = new ArrayList<Byte>();
	}
	
	public void redirectAppend(String sourcePath) {
		
		// Code Here ">>" operator
		
		// Reseting standard input
		stdin = new ArrayList<Byte>();
	}
	/***************** end operators *****************/
	
	public void more() {
		// Code Here
	}
	
	public void mv(String sourcePath, String destinationPath) {
		// Code Here
	}
	
	public void args(String command) {
		// Code Here
	}
	
	public void date() {
		// Code Here
	}
	

	// ================ BAHAA ================
		public static void cp(String sourcePath, String destinationPath) {
		File Source = new File(sourcePath);
		File Destination = new File(destinationPath);
		
		if( !(Source.isAbsolute()) )
			Source = new File(pwd + "\\" + sourcePath );
	
		if( !(Destination.isAbsolute()) )
			Destination = new File(pwd + "\\" + destinationPath);	

		   if(Source.exists())
		   {
			   try {
					Files.copy(Source.toPath(),Destination.toPath());
				} catch (IOException e) {
					System.out.println("Error: file/directory already exists!");
				}	 
			   
		   }
		
				
				
	}
	
	public void mkdir(String[] paths) {
		
		for(int i=0; i < paths.length; i++) 
		{
			 boolean flag = true;	
			file = new File(paths[i]);
			 if( !(file.isAbsolute()) )
			 {
					file = new File(pwd + "\\" + paths[i]);
			 }
		   	if(!file.exists()) 
			{
		   		file.mkdirs();
		   		flag = false;
			}
		   	else
		   	{
		   		System.out.println("Dirictor already exists.");
		   	}
		}			
		
	}
	
	public void rmdir(String[] paths) {
		for(int i=0; i < paths.length; i++) {	
			file = new File(paths[i]);
			 if( !(file.isAbsolute()) )
			 {
					file = new File(pwd + "\\" + paths[i]);
			 }
		   	if(file.exists()) 
			{
		   		if(file.isDirectory())
		   		{
		   			file.delete();	
		   		}
		   		
			}
		   	else
		   	{
		   		System.out.println("This directory already not exists.");
		   	}
		}
	}
	
	public void help() {
            System.out.println("cd        -     change the directory.");
	    System.out.println("ls        -     view the contents of a directory.");
	    System.out.println("cp        -     copy files from the current directory to a different directory.");
	    System.out.println("cat       -     Prints all contents in files.");
	    System.out.println("mkdir	  -     make a new directory.");
	    System.out.println("rmdir	  -     delete a directory.");
	    System.out.println("mv        -     move files, although it can also be used to rename files.");
	    System.out.println("rm        -     delete directories and the contents within them.");
	    System.out.println("args      -     List all command arguments.");
	    System.out.println("date      -     Current date/time.");
	    System.out.println("help      -     Display all command to help you.");
	    System.out.println("pwd       -     return an absolute (full) path.");
	    System.out.println("clear     -     Clear console.");
	    System.out.println("exit      -     Stop all.");
	    
	
		
	}
	

		/*
	 * The Eclipse Console does not support the interpretation of the clear screen and other ANSI escape sequences which would be required for that. 
	 * Also, the ANSI Escape in Console Eclipse plug-in does not support clear screen.
	 */

		public void clear() {
		  
		    for(int i=0;i<50;++i)
		    {
		    	System.out.println();
		    }
			
			///Thread.sleep(3000); 
			//System.out.flush();
		    ///final static String ESC = "\033[";
		    ///System.out.print(ESC + "2J"); 
			
		    
		   } 
	
}
