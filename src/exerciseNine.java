import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;


public class exerciseNine {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void zip(File inputFile, String zipFileName) {
		  try {
			if(!inputFile.exists()){
				inputFile.mkdirs();
			}
		   //�����ļ��������out,��ʾ:ע������֧��
		   FileOutputStream out = new FileOutputStream(
		     new String(zipFileName.getBytes("UTF-8"))); 
		   //���ļ�ݔ��ZIP�����������
		   ZipOutputStream zOut = new ZipOutputStream(out);   
		   zip(zOut, inputFile, "");
		   zOut.close();
		  } catch (Exception e) {  
		   e.printStackTrace();
		  }
		 
		 }  
		 
		 public static void zip(ZipOutputStream zOut, File file, String base) {  
		  try { 
		   // ����ļ������Ŀ¼
		   if (file.isDirectory()) {
		    //��ȡĿ¼�µ��ļ�
		    File[] listFiles = file.listFiles();
		    // ����ZIP��Ŀ
		    zOut.putNextEntry(new ZipEntry(base + "/"));     
		    base =( base.length() == 0 ? "" : base + "/" );
		    // ����Ŀ¼���ļ�
		    for (int i = 0; i < listFiles.length; i++) {
		     // �ݹ���뱾����
		     zip(zOut, listFiles[i], base + listFiles[i].getName());
		    }
		   }
		   // ����ļ�������ļ�
		   else {
		    if (base == "") {  
		     base = file.getName();
		    }
		    // �����ļ����
		    zOut.putNextEntry(new ZipEntry(base));
		     
		    // ��ʼѹ��
		    // ���ļ�������,д��ZIP ����
		    writeFile(zOut,file);
		   }
		   
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		 
		 } 
		 
		 public static void writeFile(ZipOutputStream zOut,File file) throws IOException{
		  FileInputStream in = new FileInputStream(file);  
		  int len;
		  while ((len = in.read()) != -1)
		   zOut.write(len);
		  in.close();
		 }
		  
		public static void main(String[] args) {
		 
		  zip(new File("C:/exportChainItem/showLast/����20140402"), "C:/exportChainItem/ziptest.zip");
		 
		 }

}
