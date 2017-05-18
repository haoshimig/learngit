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
		   //创建文件输出对象out,提示:注意中文支持
		   FileOutputStream out = new FileOutputStream(
		     new String(zipFileName.getBytes("UTF-8"))); 
		   //將文件輸出ZIP输出流接起来
		   ZipOutputStream zOut = new ZipOutputStream(out);   
		   zip(zOut, inputFile, "");
		   zOut.close();
		  } catch (Exception e) {  
		   e.printStackTrace();
		  }
		 
		 }  
		 
		 public static void zip(ZipOutputStream zOut, File file, String base) {  
		  try { 
		   // 如果文件句柄是目录
		   if (file.isDirectory()) {
		    //获取目录下的文件
		    File[] listFiles = file.listFiles();
		    // 建立ZIP条目
		    zOut.putNextEntry(new ZipEntry(base + "/"));     
		    base =( base.length() == 0 ? "" : base + "/" );
		    // 遍历目录下文件
		    for (int i = 0; i < listFiles.length; i++) {
		     // 递归进入本方法
		     zip(zOut, listFiles[i], base + listFiles[i].getName());
		    }
		   }
		   // 如果文件句柄是文件
		   else {
		    if (base == "") {  
		     base = file.getName();
		    }
		    // 填入文件句柄
		    zOut.putNextEntry(new ZipEntry(base));
		     
		    // 开始压缩
		    // 从文件入流读,写入ZIP 出流
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
		 
		  zip(new File("C:/exportChainItem/showLast/测试20140402"), "C:/exportChainItem/ziptest.zip");
		 
		 }

}
