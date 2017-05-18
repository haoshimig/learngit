import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


public class exerciseOne {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        readZipFile("C://exportChainItem/EasyCHM.zip");
	}
	public static void readZipFile(String file) throws Exception {  
        ZipFile zf = new ZipFile(file);  
        Reader reader=new InputStreamReader(new FileInputStream(file),"GBK");
        BufferedReader bReader=new BufferedReader(reader);
        StringBuffer sb=new StringBuffer();
        String readLine="";
        while ((readLine=bReader.readLine())!=null) {
			sb.append(readLine);
		}
        InputStream in=new ByteArrayInputStream(sb.toString().getBytes());
        //InputStream in = new BufferedInputStream(new FileInputStream(file));  
        ZipInputStream zin = new ZipInputStream(in);  
        ZipEntry ze;  
        while ((ze = zin.getNextEntry()) != null) {  
            if (ze.isDirectory()) {  
                // System.out.print("directory - " + ze.getName() + " : "   
                // + ze.getSize() + " bytes");   
                // System.out.println();   
            } else {  
                System.err.println("file - " + ze.getName() + " : "  
                        + ze.getSize() + " bytes");  
                long size = ze.getSize();  
                if (size > 0) {  
                    BufferedReader br = new BufferedReader(  
                            new InputStreamReader(zf.getInputStream(ze)));  
                    String line;  
                    while ((line = br.readLine()) != null) {  
                        System.out.println(line);  
                    }  
                    br.close();  
                }  
                System.out.println();  
            }  
        }  
        zin.closeEntry();  
    }   


}
