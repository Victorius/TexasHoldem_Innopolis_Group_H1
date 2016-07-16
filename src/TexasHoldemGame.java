import Utility.ConsoleHelper;

public class TexasHoldemGame {
	//private 
	
	public void run()
	{
		System.out.println("Hello, this is texas holdem - the game.");
		
		menuDialog();
	}
	
	private void menuDialog()
	{
		while(true)
		{
			System.out.println("Please enter one of options...");
			System.out.println("1 - Start a new table");
			System.out.println("0 - Exit");
			
			switch (ConsoleHelper.getInstance().readInt())
			{
			case 0:
				endGame();
				break;
			case 1:
				settingsDialog();
				break;
			default:
				System.out.println("Incorrect value entered, please try again once more...");
				break;
			}	
		}
	}

	private void settingsDialog() {
	}

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
