import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;


public class exerciseEight {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File file =new File("C:\\exportChainItem\\模版根目录__20140414174623.zip");
		unzip(file,"C:\\exportChainItem\\showNow\\");
	}
	
	private static void unzip(File oldDirectory,String savePath) throws Exception {
		try {
			String destPath = savePath;
			(new File(destPath)).mkdirs();
			org.apache.tools.zip.ZipFile zipFile = new org.apache.tools.zip.ZipFile(oldDirectory);
			String gbkPath, strtemp;
			java.util.Enumeration e = zipFile.getEntries();
			while (e.hasMoreElements()) {
				org.apache.tools.zip.ZipEntry zipEnt = (org.apache.tools.zip.ZipEntry) e.nextElement();
				gbkPath = zipEnt.getName();
				if (zipEnt.isDirectory()) {
					strtemp = destPath + gbkPath;
					File dir = new File(strtemp);
					dir.mkdirs();
					continue;
				} else {
				// 读写文件
				gbkPath = zipEnt.getName();
				strtemp = destPath + gbkPath;

				// 建目录
//				String strsubdir = gbkPath;
//				for (int i = 0; i < strsubdir.length(); i++) {
//					if (strsubdir.substring(i, i + 1).equalsIgnoreCase("/")) {
//						String temp = strPath+ strsubdir.substring(0, i);
//						File subdir = new File(temp);
//						if (!subdir.exists())
//						subdir.mkdir();
//					}
//				}
				BufferedInputStream bis = null;
				BufferedOutputStream bos = null;
				try {
				bis = new BufferedInputStream(zipFile.getInputStream(zipEnt));
				bos = new BufferedOutputStream(new FileOutputStream(strtemp));
				int c;
				while ((c = bis.read()) != -1) {
					bos.write((byte) c);
				}
				} catch (Exception e1) {
					throw e1;
				} finally {
				try {					if (null != bos) {
						bos.close();
					}
					if (null != bis) {
						bis.close();
					}
					} catch (Exception e2) {
						throw e2;
					}
				}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
