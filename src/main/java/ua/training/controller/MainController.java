package ua.training.controller;

import ua.training.controller.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/error", "/login", "/check", "/products", "/cancel", "/logout", "/registration", "/report"}
)
public class MainController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);


    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String lang = req.getParameter("lang");
        if (lang != null) {
            req.getSession().setAttribute("language", lang);
        }  else if (req.getSession().getAttribute("language") == null) {
            req.getSession().setAttribute("language", "en");
        }
        Command command = CommandHelper.getCommand(req);
        String path = null;
        if (command != null) {
            path = command.execute(req, resp);
            if (path != null) {
                resp.sendRedirect(path);
            }
        }
        if (command == null || path == null) {
            String page = CommandHelper.getPage(req);
            req.getRequestDispatcher("/view" + page + ".jsp").forward(req, resp);
        }
    }
}
