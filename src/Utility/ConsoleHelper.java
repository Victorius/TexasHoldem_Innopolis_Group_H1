package Utility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
	//fields
	private static ConsoleHelper instance;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//Constructor
	private ConsoleHelper()
	{
	}
	
	@Override 
	public void finalize()
	{
		try {
			br.close();
		} catch (IOException e) {
			// TODO add logger
			e.printStackTrace();
		}
	}
	
	//Singleton realization
	public static ConsoleHelper GetInstance()
	{
		if(instance == null)
		{
			instance = new ConsoleHelper();
		}
		return instance;
	}
	
	//Public Methods
	public int ReadInt()
	{
		while(true)
		{
			try 
			{
				return Integer.parseInt(br.readLine());
			} 
			catch (NumberFormatException e)
			{
				System.out.println("Please enter a correct number...");
			}
			catch (IOException e)
			{
				System.out.println("Please enter a correct number...");
			}
		}
	}
	
	public String ReadString()
	{
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void ClearConsole()
	{
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean ReadYes()
	{
		return ReadString().toLowerCase().equalsIgnoreCase("y");
	}
}
