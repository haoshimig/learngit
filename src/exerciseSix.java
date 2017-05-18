import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class exerciseSix {

	/**
	/**  
	 *  ��ѹZip�ļ�������  
	 * @author zhangyongbo  
	 *  
	 */   
	  
	    private static final int buffer = 2048;   
	  
	      public static void main(String[] args)   
	        {   
	            unZip("C:\\exportChainItem\\__20140325.zip");   
	        }   
	    /**  
	     * ��ѹZip�ļ�  
	     * @param path �ļ�Ŀ¼  
	     */  
	    public static void unZip(String path)   
	        {   
	         int count = -1;   
	         String savepath = "";   
	  
	         File file = null;   
	         InputStream is = null;   
	         FileOutputStream fos = null;   
	         BufferedOutputStream bos = null;   
	  
	         savepath = path.substring(0, path.lastIndexOf(".")) + File.separator; //�����ѹ�ļ�Ŀ¼   
	         new File(savepath).mkdir(); //��������Ŀ¼   
	         ZipFile zipFile = null;   
	         try  
	         {   
	             zipFile = new ZipFile(path); //���������������   
	             Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zipFile.entries();   
	  
	             while(entries.hasMoreElements())   
	             {   
	                 byte buf[] = new byte[buffer];   
	  
	                 ZipEntry entry = (ZipEntry)entries.nextElement();   
	  
	                 String filename = entry.getName();   
	                 boolean ismkdir = false;   
	                 if(filename.lastIndexOf("/") != -1){ //�����ļ��Ƿ�����ļ���   
	                    ismkdir = true;   
	                 }   
	                 filename = savepath + filename;   
	  
	                 if(entry.isDirectory()){ //������ļ����ȴ���   
	                    file = new File(filename);   
	                    file.mkdirs();   
	                     continue;   
	                 }   
	                 file = new File(filename);   
	                 if(!file.exists()){ //�����Ŀ¼�ȴ���   
	                    if(ismkdir){   
	                    new File(filename.substring(0, filename.lastIndexOf("/"))).mkdirs(); //Ŀ¼�ȴ���   
	                    }   
	                 }   
	                 file.createNewFile(); //�����ļ�   
	  
	                 is = zipFile.getInputStream(entry);   
	                 fos = new FileOutputStream(file);   
	                 bos = new BufferedOutputStream(fos, buffer);   
	  
	                 while((count = is.read(buf)) > -1)   
	                 {   
	                     bos.write(buf, 0, count);   
	                 }   
	                 bos.flush();   
	                 bos.close();   
	                 fos.close();   
	  
	                 is.close();   
	             }   
	  
	             zipFile.close();   
	  
	         }catch(IOException ioe){   
	             ioe.printStackTrace();   
	         }finally{   
	                try{   
	                if(bos != null){   
	                    bos.close();   
	                }   
	                if(fos != null) {   
	                    fos.close();   
	                }   
	                if(is != null){   
	                    is.close();   
	                }   
	                if(zipFile != null){   
	                    zipFile.close();   
	                }   
	                }catch(Exception e) {   
	                    e.printStackTrace();   
	                }   
	            }   
	        }   

}