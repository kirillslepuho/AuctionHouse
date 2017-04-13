package by.test;

import static org.junit.Assert.*;

import org.junit.Test;

import by.epam.auctionhouse.command.CommandHelper;
import by.epam.auctionhouse.command.impl.NoSuchCommand;

public class CommandHelperTest {
    private static final String INVALID_COMMAND = "";

    @Test
    public void getCommand(){
        CommandHelper commandHelper = new CommandHelper();
        assertTrue(commandHelper.getCommand(INVALID_COMMAND) instanceof NoSuchCommand);
    }

}
