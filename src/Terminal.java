import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Terminal {
	
	private static String pwd = "C:\\"; // default
	private static File dir = null;
	
	public void cd(String path) throws IOException {
		
		// checking if it's for home directory
		if(path.equals("~"))
			pwd = "C:\\";
		
		else {
			dir = new File(path);

			// Checking if path is relative or absolute
			if( !(dir.isAbsolute()) )
				dir = new File(pwd + "\\" + path);

			// Checking if it exists
			if(dir.exists()) {
				
				// Checking if it's a directory and not a file
				if(dir.isDirectory()) {
					
					// canonical resolves .. in paths by default
					pwd = dir.getCanonicalPath();
					
				} else {
					System.out.println("ERROR: only directories can be accessed!");
				}
				
			} else {
				System.out.println("ERROR: no such file or directory!");
			}	
		}

	}
	
	public void ls() {
		
	}
	
	public void cat(String[] paths) {
		
	}
	
	public void rm(String sourcePath) {
		
		dir = new File(pwd + sourcePath);

		// Checking if it exists
		if(dir.exists()) {
			
			// Checking if it's a directory and not a file
			if(dir.isFile()) {
				
				// Deleting the file
				dir.delete();
				
			} else {
				System.out.println("ERROR: only files can be deleted!");
			}
			
		} else {
			System.out.println("ERROR: no such file or directory!");
		}

	}
	
	public void pwd() {
		System.out.println(pwd);
	}
}
