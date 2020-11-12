import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Terminal {
	
	private static String pwd = "C:\\"; // default
	private static File dir = null;
	
	public void cd(String path) throws IOException {
		
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
		
	}
	
	public void pwd() {
		System.out.println(pwd);
	}
}
