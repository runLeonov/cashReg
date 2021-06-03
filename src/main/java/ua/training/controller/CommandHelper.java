package ua.training.controller;

import ua.training.controller.commands.Command;
import ua.training.controller.commands.commandfactory.CommandFactory;

import javax.servlet.http.HttpServletRequest;

import static ua.training.controller.commands.commandfactory.CommandFactory.Commands.*;

public class CommandHelper {

    public static Command getCommand(HttpServletRequest req) {
        CommandFactory commands = CommandFactory.getInstance();
        switch (req.getServletPath()) {
            case "/":
            case "/login":
                if (req.getParameter("btnLogin") != null && req.getParameter("email") != null) {
                    return commands.getCommand(LOGIN);
                }
                break;
            case "/logout":
                return commands.getCommand(LOGOUT);
            case "/registration":
                if (req.getParameter("btnReg") != null) {
                    return commands.getCommand(REGISTRATION);
                }
                break;
            case "/products":
                if (req.getParameter("btnDeleteProduct") != null) {
                    return commands.getCommand(DELETE_FROM_STORE);
                } else if (req.getParameter("btnAddNewProduct") != null) {
                    return commands.getCommand(ADD_TO_STORE);
                } else if (req.getParameter("btnUpdateInStore") != null) {
                    return commands.getCommand(UPDATE_IN_STORE);
                }
                return commands.getCommand(PRODUCTS);
            case "/check":
                if (req.getParameter("btnAddToCheck") != null) {
                    return commands.getCommand(ADD_TO_CHECK);
                } else if (req.getParameter("clearCheck") != null) {
                    return commands.getCommand(CLEAR_CHECK_COMMAND);
                } else if (req.getParameter("btnCreateCheck") != null) {
                    return commands.getCommand(CREATE_CHECK);
                }
                break;
            case "/cancel":
                if (req.getParameter("btnShowById") != null) {
                    return commands.getCommand(SHOW_CHECK_BY_ID);
                } else if (req.getParameter("btnDeleteById") != null) {
                    return commands.getCommand(DELETE_BY_ID);
                } else if (req.getParameter("btnShowLast") != null) {
                    return commands.getCommand(SHOW_CHECK_LAST);
                } else if (req.getParameter("btnDeleteLastCheck") != null) {
                    return commands.getCommand(DELETE_CHECK_LAST);
                } else if (req.getParameter("btnCreateXReport") != null
                        || req.getParameter("btnCreateZReport") != null) {
                    return commands.getCommand(CREATE_REPORT);
                }
            default:
                return null;
        }
        return null;
    }

    public static String getPage(HttpServletRequest req) {
        String page = req.getServletPath();
        if (page.equals("/") || page.equals("/login") || page.equals("/logout")) {
            page = "/index";
        }
        return page;
    }
}
