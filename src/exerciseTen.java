import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;


public class exerciseTen {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File file =new File("C:\\exportChainItem\\模版根目录__20140414174623.zip");
		unzip(file);
	}
	
	private static void unzip(File oldDirectory) throws Exception {
		try {
			org.apache.tools.zip.ZipFile zipFile = new org.apache.tools.zip.ZipFile(oldDirectory);
			java.util.Enumeration e = zipFile.getEntries();
			while (e.hasMoreElements()) {
				org.apache.tools.zip.ZipEntry zipEnt = (org.apache.tools.zip.ZipEntry) e.nextElement();
				// 读文件
				BufferedInputStream bis = null;
				try {
					bis = new BufferedInputStream(zipFile.getInputStream(zipEnt));
				} catch (Exception e1) {
					throw e1;
				} finally {
				try {
					if (null != bis) {
						bis.close();
					}
					} catch (Exception e2) {
						throw e2;
					}
				}
			
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
