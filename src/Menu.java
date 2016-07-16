import Model.TableSettings;
import Model.Enumerations.RaisingPolicy;
import Utility.ConsoleHelper;

public class Menu {
	public static TableSettings settingsDialog() {
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
}
