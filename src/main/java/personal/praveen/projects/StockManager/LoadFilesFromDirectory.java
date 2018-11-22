package personal.praveen.projects.StockManager;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.VFS;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

public class LoadFilesFromDirectory {
	
	private final String PATH = "*****";
	
	private void loadFiles() throws InvalidPasswordException, ClassNotFoundException, IOException, SQLException {
	    FileObject file = null;
	    ReadPdf pdfReader = new ReadPdf();
	      file = VFS.getManager().resolveFile(PATH);
	      FileObject[] filesInsideFolder = file.getChildren();
	      for (int i = filesInsideFolder.length-1; i >= 0 ; i--) {
	    	  System.out.println(filesInsideFolder[i].getName().getPath());
	    	  pdfReader.ParsePdf(filesInsideFolder[i].getName().getPath());
	        }
	}
	
	public static void main(String args[]) throws InvalidPasswordException, ClassNotFoundException, IOException, SQLException {
		LoadFilesFromDirectory files = new LoadFilesFromDirectory();
		files.loadFiles();
	}
}
	      
