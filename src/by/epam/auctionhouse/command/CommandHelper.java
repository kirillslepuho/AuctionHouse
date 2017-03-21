package by.epam.auctionhouse.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.auctionhouse.command.impl.Localization;
import by.epam.auctionhouse.command.impl.NoSuchCommand;
import by.epam.auctionhouse.command.impl.admin.AddAuctionCommand;
import by.epam.auctionhouse.command.impl.admin.AddLotCommand;
import by.epam.auctionhouse.command.impl.admin.DeleteAuctionCommand;
import by.epam.auctionhouse.command.impl.admin.EditAuctionCommand;
import by.epam.auctionhouse.command.impl.admin.GoToAddAuctionPage;
import by.epam.auctionhouse.command.impl.admin.GoToAdminPage;
import by.epam.auctionhouse.command.impl.admin.GoToEditPage;
import by.epam.auctionhouse.command.impl.user.GoToAuctionPage;
import by.epam.auctionhouse.command.impl.user.GoToMainPage;
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
	}

	public static CommandHelper getInstance() {
		return instance;
	}

	public ICommand getCommand(String commandName){
		CommandName name = CommandName.valueOf(commandName.toUpperCase());
		ICommand command;
		if(name != null){
			command = commands.get(name);
		}else{
			command = commands.get(CommandName.NO_SUCH_COMMAND);
		}

		return command;
	}

}
