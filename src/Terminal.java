import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Terminal {
	
	private static String pwd = "C:\\"; // default
	private static File file = null;
	
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
	
	public void cat(String[] paths) throws FileNotFoundException {
		Scanner reader = null;
		String data;
		
		// Iterating through each file
		for(int i=0; i < paths.length; i++) {
			file = new File(paths[i]);
			
			// Resolving relative files
			if( !(file.isAbsolute()) )
				file = new File(pwd + "\\" + paths[i]);
				
			
			// Checking if it's a file and it exists
			if(file.exists() && file.isFile()) {
				
				reader = new Scanner(file);
				
				// Reading the file line by line and printing
				while(reader.hasNextLine()) {
					data = reader.nextLine();
					System.out.println(data);
				}
				
			} else {
				System.out.println("ERROR: " + file.getName() + " file doesn't exists!");
			}
			
			
		}
	}
	
	public void rm(String[] paths) {

		for(int i=0; i < paths.length; i++) {
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
}
