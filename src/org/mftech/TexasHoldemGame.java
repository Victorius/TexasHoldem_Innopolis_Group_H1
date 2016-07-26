package main.java;

import main.java.Model.Croupier;
import main.java.Model.Table;
import main.java.UI.Menu;
import main.java.Utility.ConsoleHelper;

public class TexasHoldemGame {
	public void run()
	{
		System.out.println("Hello, this is texas holdem - the game.");
		
		while(true)
		{
			System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
			System.out.println("Please enter one of options...");
			System.out.println("1 - Start a new table");
			System.out.println("0 - Exit");
			
			switch (ConsoleHelper.getInstance().readInt())
			{
			case 0:
				endGame();
				break;
			case 1:
				Table t = new Table(Menu.settingsDialog());
				t.init();
				Croupier c = new Croupier(t);//Croupier comes to table and shit is about to get crazy ladies and jentelmens
				c.StartGame();
				break;
			default:
				break;
			}	
		}
	}

	//close application
	private void endGame()
	{
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
		System.out.println("Do you really want to quit? (y)");
		if(ConsoleHelper.getInstance().readYes())
		{
			System.exit(0);
		}
	}
}
