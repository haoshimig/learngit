import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class exerciseThree {
	 public static void main(String args[]) {
	        String file = "C://exportChainItem/__20140325.zip";
	        String saveRootDirectory="c://test/";
	        zipFileRead(file,saveRootDirectory);
	    }

	    private static void zipFileRead(String file,String saveRootDirectory) {
	        try {
	            ZipFile zipFile = new ZipFile(file);
	            @SuppressWarnings("unchecked")
	            Enumeration<ZipEntry> enu = (Enumeration<ZipEntry>) zipFile.entries();
	            while (enu.hasMoreElements()) {
	                ZipEntry zipElement = (ZipEntry) enu.nextElement();
	                InputStream read = zipFile.getInputStream(zipElement);
	                String fileName = zipElement.getName();
	                if (fileName != null && fileName.indexOf(".") != -1) {//是否为文件 （文件带有路径如：/images/a.jpg）
	                    execute(zipElement,read,saveRootDirectory);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    private static void execute(ZipEntry ze, InputStream read,String saveRootDirectory)
	            throws FileNotFoundException, IOException {
	        //如果只读取图片，自行判断就OK.
	        String fileName = ze.getName();
//	        if(fileName.lastIndexOf(".jpg")!= -1 || fileName.lastIndexOf(".bmp")!= -1 
//	            || fileName.lastIndexOf(".jpeg")!= -1){//指定要解压出来的文件格式（这些格式可抽取放置在集合或String数组通过参数传递进来，方法更通用）
	            File file = new File(saveRootDirectory + fileName);
	            if (!file.exists()) {
	                File rootDirectoryFile = new File(file.getParent());
	                //创建目录
	                if (!rootDirectoryFile.exists()) {
	                    boolean ifSuccess = rootDirectoryFile.mkdirs();
	                    if (ifSuccess) {
	                        System.out.println("文件夹创建成功!");
	                    } else {
	                        System.out.println("文件创建失败!");
	                    }
	                }
	                //创建文件
	                try {
	                    file.createNewFile();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            //写入文件
	            BufferedOutputStream write = new BufferedOutputStream(new FileOutputStream(file));
	            int cha = 0;
	            while ((cha = read.read()) != -1) {
	                write.write(cha);
	            }
	            //要注意IO流关闭的先后顺序
	            write.flush();
	            write.close();
	            read.close();
//	        }
	    }
}
