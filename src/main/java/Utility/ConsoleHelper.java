package main.java.Utility;
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
	public static ConsoleHelper getInstance()
	{
		if(instance == null)
		{
			instance = new ConsoleHelper();
		}
		return instance;
	}
	
	//Public Methods
	public int readInt()
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
	
	public String readString()
	{
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean readYes()
	{
		return readString().toLowerCase().equalsIgnoreCase("y");
	}
}
