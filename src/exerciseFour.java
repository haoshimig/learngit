import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class exerciseFour {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		unZip("C://exportChainItem//EasyCHM.zip");
	}
	
	public static void unZip(String unZipfileName) throws IOException{//unZipfileName需要解压的zip文件名 
        FileOutputStream fileOut; 
        File file; 
        InputStream inputStream; 
        byte[] buf=new byte[1024]; 
        ZipFile zipFile=new ZipFile(unZipfileName);

        try{ 
            for(Enumeration entries = zipFile.entries(); entries.hasMoreElements();){ 
                ZipEntry entry = (ZipEntry)entries.nextElement(); 
                file = new File(entry.getName()); 

                if(entry.isDirectory()){ 
                    file.mkdirs(); 
                } 
                else{ 
                    //如果指定文件的目录不存在,则创建之. 
                    File parent = file.getParentFile(); 
                    if(!parent.exists()){ 
                        parent.mkdirs(); 
                    } 

                    inputStream = zipFile.getInputStream(entry); 

                    fileOut = new FileOutputStream(file); 
                    int readedBytes;
					while((readedBytes = inputStream.read(buf) ) > 0){ 
                        fileOut.write(buf , 0 , readedBytes ); 
                    } 
                    fileOut.close(); 

                    inputStream.close(); 
                }    
            } 
            zipFile.close(); 
        }catch(IOException ioe){ 
            ioe.printStackTrace(); 
        } 
    } 


}
