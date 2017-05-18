import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class exerciseFive {

	/**
	 * @param args
	 */
	 static final int BUFFER = 2048;   
	 static boolean flag = false;  
	      
	    public static void main(String args[])throws IOException{  
	        File file= new File("D:/Temp");  
	        ZipSubdirectory(file);  
	        FileInputStream file1 = new FileInputStream(ZipSubdirectory(file));  
	        System.out.println(file1.toString());  
	    }  
	    //ZipSubdirectory������һ��ָ��Ŀ¼����������Ŀ¼��ѹ����һ��ͬ��ѹ���ļ�(�����Ϊ"ORIGIN")  
	    public static File ZipSubdirectory(File myDir)throws IOException{  
	        //��������������BufferedInputStream   
	        BufferedInputStream origin = null;  
	        //����ZipOutputStream���󣬽���������ϣ��д���ļ��������  
	        //File zipFile=new File("D:/"+myDir.getName()+".zip");  
	        File zipFile=new File("C://exportChainItem//����__20140401.zip");
	        FileOutputStream fos=new FileOutputStream(zipFile);  
	        ZipOutputStream out=new ZipOutputStream(new BufferedOutputStream(fos,BUFFER));  
	        //dirContents[]��ȡ��ǰĿ¼(myDir)�����ļ����󣨰�����Ŀ¼��)  
	        File dirContents[]=myDir.listFiles();  
	        //������ʱ�ļ�tempFile,ʹ�ú�ɾ��  
	        File tempFile=null;   
	        try{  
	            //����ǰĿ¼�����ļ����󣬰�����Ŀ¼  
	            for(int i=0;i < dirContents.length; i++){  
	            //ʹ�õݹ鷽������ǰĿ¼����Ŀ¼ת��һ��ZIP�ļ�������Ϊһ��ENTRY�ӽ�"ORIGIN"   
	            if(dirContents[i].isDirectory()){  
	                tempFile = ZipSubdirectory(dirContents[i]);  
	                flag=true;  
	        }  
	        //�����ǰ�ļ�������Ŀ¼  
	        else {  
	            tempFile=dirContents[i];  
	            //flag���tempFile�Ƿ�����Ŀ¼ѹ���ɵ�ZIP�ļ�  
	            flag = false;  
	        }  
	        System.out.println("Compress file: "+tempFile.getName());  
	        FileInputStream fis = new FileInputStream(tempFile);  
	        origin = new BufferedInputStream(fis,BUFFER);  
	        //Ϊ����ȡ���ļ�����ѹ����Ŀ  
	        ZipEntry entry = new ZipEntry(tempFile.getName());  
	        byte data[]= new byte[BUFFER];  
	        int count;  
	        //����ZIP�����д������֮ǰ����������ʹ��out.putNextEntry(entry); ��������ѹ����Ŀ����   
	        out.putNextEntry(entry);  
	        //��ZIP �ļ�д������   
	        while((count=origin.read(data,0,BUFFER))!=-1){  
	            out.write(data,0,count);  
	        }  
	        //tempFile����ʱ���ɵ�ZIP�ļ�,ɾ����  
	        if(flag==true){  
	            flag = tempFile.delete();  
	            System.out.println("Delete file:"+tempFile.getName()+flag);   
	        }   
	        //�ر�������   
	        origin.close();   
	        }  
	        out.close();  
	        } catch(Exception e){  
	            System.out.println(e);  
	        }  
	        //�ݹ鷵��   
	        return zipFile;   
	    }  
}
