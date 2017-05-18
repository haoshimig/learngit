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
	                if (fileName != null && fileName.indexOf(".") != -1) {//�Ƿ�Ϊ�ļ� ���ļ�����·���磺/images/a.jpg��
	                    execute(zipElement,read,saveRootDirectory);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    private static void execute(ZipEntry ze, InputStream read,String saveRootDirectory)
	            throws FileNotFoundException, IOException {
	        //���ֻ��ȡͼƬ�������жϾ�OK.
	        String fileName = ze.getName();
//	        if(fileName.lastIndexOf(".jpg")!= -1 || fileName.lastIndexOf(".bmp")!= -1 
//	            || fileName.lastIndexOf(".jpeg")!= -1){//ָ��Ҫ��ѹ�������ļ���ʽ����Щ��ʽ�ɳ�ȡ�����ڼ��ϻ�String����ͨ���������ݽ�����������ͨ�ã�
	            File file = new File(saveRootDirectory + fileName);
	            if (!file.exists()) {
	                File rootDirectoryFile = new File(file.getParent());
	                //����Ŀ¼
	                if (!rootDirectoryFile.exists()) {
	                    boolean ifSuccess = rootDirectoryFile.mkdirs();
	                    if (ifSuccess) {
	                        System.out.println("�ļ��д����ɹ�!");
	                    } else {
	                        System.out.println("�ļ�����ʧ��!");
	                    }
	                }
	                //�����ļ�
	                try {
	                    file.createNewFile();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            //д���ļ�
	            BufferedOutputStream write = new BufferedOutputStream(new FileOutputStream(file));
	            int cha = 0;
	            while ((cha = read.read()) != -1) {
	                write.write(cha);
	            }
	            //Ҫע��IO���رյ��Ⱥ�˳��
	            write.flush();
	            write.close();
	            read.close();
//	        }
	    }
}
