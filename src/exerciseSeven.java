import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class exerciseSeven {
	 public static void main(String[] args) throws Exception   
     {   
		 File zip = new File("C:\\exportChainItem\\EasyCHM.zip");
         unZip(zip,"C:\\temp\\zipout");   
     }
	public static void unZip(File zip, String root) throws Exception {
		   try {
		    FileInputStream fins = new FileInputStream(zip);
		    ZipInputStream zins = new ZipInputStream(fins);
		    ZipEntry ze = null;
		    byte ch[] = new byte[256];
		    while ((ze = zins.getNextEntry()) != null) {
		     String temp = ze.getName();
		     File zfile = new File(root + temp);
		     File fpath = new File(zfile.getParentFile().getPath());

		     if (ze.isDirectory()) {
		      if (!zfile.exists())
		       zfile.mkdirs();
		      zins.closeEntry();
		     } else {
		      if (!fpath.exists())
		       fpath.mkdirs();
		      FileOutputStream fouts = new FileOutputStream(zfile);
		      int i;
		      while ((i = zins.read(ch)) != -1)
		       fouts.write(ch, 0, i);
		      zins.closeEntry();
		      fouts.close();
		     }
		    }
		    fins.close();
		    zins.close();

		   } catch (Exception e) {
		    System.err.println("Exception from ZipUtil -> unZip() : "
		      + e.getMessage());
		    e.printStackTrace(System.err);
		    throw e;
		   }
	}
}
