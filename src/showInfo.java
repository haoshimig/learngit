//ͨ���ж��ļ��Ľ�β����ȡ�ļ�   
	import java.io.File;  
	import java.io.InputStream;  
	import java.io.FileInputStream; 
public class showInfo {
		public static void main(String args[]) throws Exception{
			File f = new File("C://exportChainItem//����__20140325.zip");
			InputStream in = new FileInputStream(f);
			byte b[] = new byte[1024];
			int len = 0;
			int temp=0;          //���ж�ȡ�����ݶ�ʹ��temp����
			while((temp=in.read())!=-1){    //��û�ж�ȡ��ʱ��������ȡ
				b[len]=(byte)temp;
				len++;
			}
			in.close();
			System.out.println(new String(b,0,len));
		}
}
