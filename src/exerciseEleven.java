import java.io.File;


public class exerciseEleven {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		method(new File("C://exportChainItem//20140402183444"));
	}
	
	public static void method(File f)
	{
		File[] FList = f.listFiles();
		for (int i = 0; i < FList.length; i++)
		{
			if (FList[i].isDirectory()==true)
			{
				method(FList[i]);
			}
			else
			{
				System.out.println(FList[i].getAbsolutePath());
			}
		}
	}

}
