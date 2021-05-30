package ua.training.controller.commands.commandfactory;

import ua.training.controller.commands.Command;
import ua.training.controller.commands.authorizationcommands.LoginCommand;
import ua.training.controller.commands.authorizationcommands.LogoutCommand;
import ua.training.controller.commands.authorizationcommands.RegistrationCommand;
import ua.training.controller.commands.cancelcommands.*;
import ua.training.controller.commands.checkcommands.AddToCheckCommand;
import ua.training.controller.commands.checkcommands.ClearCheckCommand;
import ua.training.controller.commands.checkcommands.CreateCheckCommand;
import ua.training.controller.commands.storecommands.AddToStoreCommand;
import ua.training.controller.commands.storecommands.DeleteFromStoreCommand;
import ua.training.controller.commands.storecommands.ProductsCommand;
import ua.training.controller.commands.storecommands.UpdateProductCommand;

public class CommandFactory {
    private static CommandFactory instance;

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    public Command getCommand(Commands command) {
        switch (command) {
            case LOGIN:
                return new LoginCommand();
            case LOGOUT:
                return new LogoutCommand();
            case REGISTRATION:
                return new RegistrationCommand();
            case ADD_TO_CHECK:
                return new AddToCheckCommand();
            case CLEAR_CHECK_COMMAND:
                return new ClearCheckCommand();
            case PRODUCTS:
                return new ProductsCommand();
            case DELETE_FROM_STORE:
                return new DeleteFromStoreCommand();
            case ADD_TO_STORE:
                return new AddToStoreCommand();
            case UPDATE_IN_STORE:
                return new UpdateProductCommand();
            case CREATE_CHECK:
                return new CreateCheckCommand();
            case SHOW_CHECK_BY_ID:
                return new ShowCheckByIdCommand();
            case DELETE_BY_ID:
                return new DeleteCheckByIdCommand();
            case SHOW_CHECK_LAST:
                return new ShowLastCheckCommand();
            case DELETE_CHECK_LAST:
                return new DeleteLastCheckCommand();
            case CREATE_REPORT:
                return new CreateReportCommand();
            default:
                return null;
        }
    }

    public enum Commands {
        LOGIN, REGISTRATION,
        LOGOUT, ADD_TO_CHECK,
        DELETE_CHECK_LAST, CLEAR_CHECK_COMMAND,
        PRODUCTS, SHOW_CHECK_LAST,
        DELETE_FROM_STORE, ADD_TO_STORE,
        DELETE_BY_ID, UPDATE_IN_STORE,
        CREATE_CHECK, SHOW_CHECK_BY_ID,
        CREATE_REPORT
    }
}
