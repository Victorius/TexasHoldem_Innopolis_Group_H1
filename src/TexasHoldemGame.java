import Utility.ConsoleHelper;

public class TexasHoldemGame {
	//private 
	
	public void Run()
	{
		System.out.println("Hello, this is texas holdem - the game.");
		
		MenuDialog();
	}
	
	private void MenuDialog()
	{
		while(true)
		{
			System.out.println("Please enter one of options...");
			System.out.println("1 - Start a new table");
			System.out.println("0 - Exit");
			
			switch (ConsoleHelper.GetInstance().ReadInt())
			{
			case 0:
				EndGame();
				break;
			case 1:
				SettingsDialog();
				break;
			default:
				System.out.println("Incorrect value entered, please try again once more...");
				break;
			}	
		}
	}

	private void SettingsDialog() {
		// TODO Auto-generated method stub
		
	}

	private void EndGame()
	{
		ConsoleHelper.GetInstance().ClearConsole();
		System.out.println("Do you really want to quit? (y)");
		if(ConsoleHelper.GetInstance().ReadYes())
		{
			System.exit(0);
		}
	}
}
