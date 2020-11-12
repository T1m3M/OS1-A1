import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

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
	
	public void cat(String[] paths) {
		
	}
	
	public void rm(String sourcePath) {
		
		file = new File(pwd + sourcePath);

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
	
	public void pwd() {
		System.out.println(pwd);
	}
}
