import Model.Table;
import Model.TableSettings;
import Model.Enumerations.RaisingPolicy;
import UI.Menu;
import Utility.ConsoleHelper;

public class TexasHoldemGame {
	public void run()
	{
		System.out.println("Hello, this is texas holdem - the game.");
		
		while(true)
		{
			ConsoleHelper.getInstance().clearConsole();
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
				t.start(); //game entry point
				break;
			default:
				break;
			}	
		}
	}

	//close application
	private void endGame()
	{
		ConsoleHelper.getInstance().clearConsole();
		System.out.println("Do you really want to quit? (y)");
		if(ConsoleHelper.getInstance().readYes())
		{
			System.exit(0);
		}
	}
}
