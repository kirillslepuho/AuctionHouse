package by.epam.auctionhouse.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.auctionhouse.command.impl.Localization;
import by.epam.auctionhouse.command.impl.NoSuchCommand;
import by.epam.auctionhouse.command.impl.admin.AddAuctionCommand;
import by.epam.auctionhouse.command.impl.admin.AddLotCommand;
import by.epam.auctionhouse.command.impl.admin.BlockLotCommand;
import by.epam.auctionhouse.command.impl.admin.CancelBetCommand;
import by.epam.auctionhouse.command.impl.admin.ChangeUserStatusCommand;
import by.epam.auctionhouse.command.impl.admin.DeleteAuctionCommand;
import by.epam.auctionhouse.command.impl.admin.EditAuctionCommand;
import by.epam.auctionhouse.command.impl.admin.EditLotCommand;
import by.epam.auctionhouse.command.impl.admin.GoToAddAuctionPage;
import by.epam.auctionhouse.command.impl.admin.GoToAdminPage;
import by.epam.auctionhouse.command.impl.admin.GoToAuctionsBetsPageCommand;
import by.epam.auctionhouse.command.impl.admin.GoToAuctionsPage;
import by.epam.auctionhouse.command.impl.admin.GoToClientsPage;
import by.epam.auctionhouse.command.impl.admin.GoToEditPage;
import by.epam.auctionhouse.command.impl.admin.GoToLotEditPage;
import by.epam.auctionhouse.command.impl.admin.GoToLotsPage;
import by.epam.auctionhouse.command.impl.admin.SetAuctionWinner;
import by.epam.auctionhouse.command.impl.user.GoToAuctionPage;
import by.epam.auctionhouse.command.impl.user.GoToClientPage;
import by.epam.auctionhouse.command.impl.user.GoToMainPage;
import by.epam.auctionhouse.command.impl.user.PlaceBlitzBet;
import by.epam.auctionhouse.command.impl.user.PlaceEnglishBet;
import by.epam.auctionhouse.command.impl.user.RegistrationCommand;
import by.epam.auctionhouse.command.impl.user.SignInCommand;
import by.epam.auctionhouse.command.impl.user.SignOutCommand;

public class CommandHelper {
	private static final CommandHelper instance = new CommandHelper();

	private Map<CommandName,ICommand> commands = new HashMap<>();

	public CommandHelper(){
		commands.put(CommandName.SIGN_IN, new SignInCommand());
		commands.put(CommandName.SIGN_OUT, new SignOutCommand());
		commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
		commands.put(CommandName.LOCALIZATION, new Localization());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.ADD_AUCTION, new AddAuctionCommand());
		commands.put(CommandName.DELETE_AUCTION, new DeleteAuctionCommand());
		commands.put(CommandName.EDIT_AUCTION, new EditAuctionCommand());
		commands.put(CommandName.ADD_LOT, new AddLotCommand());
		commands.put(CommandName.GO_TO_ADMIN_PAGE, new GoToAdminPage());
		commands.put(CommandName.GO_TO_ADD_AUCTION_PAGE, new GoToAddAuctionPage());
		commands.put(CommandName.GO_TO_EDIT_PAGE, new GoToEditPage());
		commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
		commands.put(CommandName.GO_TO_AUCTION_PAGE, new GoToAuctionPage());
		commands.put(CommandName.PLACE_ENGLISH_BET, new PlaceEnglishBet());
		commands.put(CommandName.PLACE_BLITZ_BET,new PlaceBlitzBet() );
		commands.put(CommandName.GO_TO_CLIENT_PAGE,new GoToClientPage() );
		commands.put(CommandName.GO_TO_AUCTIONS_PAGE,new GoToAuctionsPage());
		commands.put(CommandName.GO_TO_CLIENTS_PAGE,new GoToClientsPage() );
		commands.put(CommandName.GO_TO_LOTS_PAGE,new GoToLotsPage() );
		commands.put(CommandName.CHANGE_USER_STATUS, new ChangeUserStatusCommand());
		commands.put(CommandName.GO_TO_AUCTION_BETS_PAGE, new GoToAuctionsBetsPageCommand());
		commands.put(CommandName.SET_AUCTION_WINNER, new SetAuctionWinner());
		commands.put(CommandName.EDIT_LOT,new EditLotCommand());
		commands.put(CommandName.GO_TO_LOT_EDIT_PAGE, new GoToLotEditPage());
		commands.put(CommandName.BLOCK_LOT, new BlockLotCommand());
		commands.put(CommandName.CANCEL_BET, new CancelBetCommand());
		
	}

	public static CommandHelper getInstance() {
		return instance;
	}

	public ICommand getCommand(String commandName){
		ICommand command;
		if(commandName != null){
			command = commands.get(CommandName.NO_SUCH_COMMAND);
		}
		
		CommandName name = CommandName.valueOf(commandName.toUpperCase());
		if(name != null && commands.get(name) != null){
			
			command = commands.get(name);
		}else{
			command = commands.get(CommandName.NO_SUCH_COMMAND);
		}

		return command;
	}

}
