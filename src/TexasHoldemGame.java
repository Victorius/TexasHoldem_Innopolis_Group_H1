import Model.TableSettings;
import Model.Enumerations.RaisingPolicy;
import Utility.ConsoleHelper;

public class TexasHoldemGame {
	//private 
	private TableSettings settings;
	
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
				settings = settingsDialog();
				break;
			default:
				System.out.println("Incorrect value entered, please try again once more...");
				break;
			}	
		}
	}

	private TableSettings settingsDialog() {
		System.out.println("Table settings...");

		
		while(true)
		{
			int smallBlind;
			int playersCount;
			int startingMoney;	
			System.out.println("Enter number of players...");
			while(true)
			{
				int value = ConsoleHelper.getInstance().readInt();
				if(value >= 2 && value <= 8)
				{
					playersCount = value;
					break;
				}
				else
				{
					System.out.println("Enter amount between 2 and 8");
				}
			}
			System.out.println("Enter small blind amount...");
			while(true)
			{
				int value = ConsoleHelper.getInstance().readInt();
				if(value > 0)
				{
					smallBlind = value;
					break;
				}
				else
				{
					System.out.println("Enter positive small blind amount...");
				}
			}
			System.out.println("Enter starting money amount...");
			while(true)
			{
				int value = ConsoleHelper.getInstance().readInt();
				if(value > 0)
				{
					startingMoney = value;
					break;
				}
				else
				{
					System.out.println("Enter positive starting money amount...");
				}
			}
			ConsoleHelper.getInstance().clearConsole();
			System.out.printf("You choosed\nPlayers:%d\nSmallBlind:%d\nStartingMoney:%d\nIs that correct?(y/n)", playersCount,smallBlind,startingMoney);
			if(ConsoleHelper.getInstance().readYes())
			{
				return new TableSettings(playersCount,startingMoney,smallBlind,RaisingPolicy.NoLimits);
			}
			ConsoleHelper.getInstance().clearConsole();
		}
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
