//通过判断文件的结尾来读取文件   
	import java.io.File;  
	import java.io.InputStream;  
	import java.io.FileInputStream; 
public class showInfo {
		public static void main(String args[]) throws Exception{
			File f = new File("C://exportChainItem//测试__20140325.zip");
			InputStream in = new FileInputStream(f);
			byte b[] = new byte[1024];
			int len = 0;
			int temp=0;          //所有读取的内容都使用temp接收
			while((temp=in.read())!=-1){    //当没有读取完时，继续读取
				b[len]=(byte)temp;
				len++;
			}
			in.close();
			System.out.println(new String(b,0,len));
		}
}
