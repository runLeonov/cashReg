package ua.training.controller.commands;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Command for working
 * with servlet
 *
 * @author LeonovOleksand
 */
@FunctionalInterface
public interface Command {

    /**
     * Run logic
     * @param req  request
     * @param resp response
     *
     */

    String execute(HttpServletRequest req, HttpServletResponse resp);
}
