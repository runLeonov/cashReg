package ua.training;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.training.controller.MainController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.training.controller.commands.Command;
import ua.training.controller.commands.authorizationcommands.LoginCommand;
import ua.training.controller.commands.authorizationcommands.LogoutCommand;
import ua.training.controller.commands.authorizationcommands.RegistrationCommand;
import ua.training.controller.commands.cancelcommands.*;
import ua.training.controller.commands.checkcommands.AddToCheckCommand;
import ua.training.controller.commands.checkcommands.ClearCheckCommand;
import ua.training.controller.commands.checkcommands.CreateCheckCommand;
import ua.training.controller.commands.commandfactory.CommandFactory;
import ua.training.controller.commands.storecommands.AddToStoreCommand;
import ua.training.controller.commands.storecommands.DeleteFromStoreCommand;
import ua.training.controller.commands.storecommands.ProductsCommand;
import ua.training.controller.commands.storecommands.UpdateProductCommand;
import ua.training.dao.daoimpl.UserDAO;
import ua.training.dao.entity.User;
import ua.training.dao.entity.UserRole;
import ua.training.service.UserService;

@RunWith(PowerMockRunner.class)
public class ServletTest extends Mockito {
    MainController servlet = new MainController();
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    RequestDispatcher dispatcher = mock(RequestDispatcher.class);


    @Test
    public void commandTest() {
        CommandFactory factory = CommandFactory.getInstance();
        assertEquals(factory.getCommand(CommandFactory.Commands.CLEAR_CHECK_COMMAND)
                .getClass(), (ClearCheckCommand.class));
        assertEquals(factory.getCommand(CommandFactory.Commands.ADD_TO_CHECK)
                .getClass(), (AddToCheckCommand.class));
        assertEquals(factory.getCommand(CommandFactory.Commands.CREATE_CHECK)
                .getClass(), (CreateCheckCommand.class));
        assertEquals(factory.getCommand(CommandFactory.Commands.LOGIN)
                .getClass(), (LoginCommand.class));
        assertEquals(factory.getCommand(CommandFactory.Commands.REGISTRATION)
                .getClass(), (RegistrationCommand.class));
        assertEquals(factory.getCommand(CommandFactory.Commands.LOGOUT)
                .getClass(), (LogoutCommand.class));
        assertEquals(factory.getCommand(CommandFactory.Commands.DELETE_CHECK_LAST)
                .getClass(), (DeleteLastCheckCommand.class));
        assertEquals(factory.getCommand(CommandFactory.Commands.PRODUCTS)
                .getClass(), (ProductsCommand.class));
        assertEquals(factory.getCommand(CommandFactory.Commands.SHOW_CHECK_LAST)
                .getClass(), (ShowLastCheckCommand.class));
        assertEquals(factory.getCommand(CommandFactory.Commands.DELETE_FROM_STORE)
                .getClass(), (DeleteFromStoreCommand.class));
        assertEquals(factory.getCommand(CommandFactory.Commands.ADD_TO_STORE)
                .getClass(), (AddToStoreCommand.class));
        assertEquals(factory.getCommand(CommandFactory.Commands.DELETE_BY_ID)
                .getClass(), (DeleteCheckByIdCommand.class));
        assertEquals(factory.getCommand(CommandFactory.Commands.UPDATE_IN_STORE)
                .getClass(), (UpdateProductCommand.class));
        assertEquals(factory.getCommand(CommandFactory.Commands.SHOW_CHECK_BY_ID)
                .getClass(), (ShowCheckByIdCommand.class));
        assertEquals(factory.getCommand(CommandFactory.Commands.CREATE_REPORT)
                .getClass(), (CreateReportCommand.class));
    }

    @PrepareForTest(UserService.class)
    @Test
    public void loginTest() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn("/");
        when(request.getRequestDispatcher("/view/index.jsp")).thenReturn(dispatcher);
        servlet.doGet(request, response);
        when(request.getParameter("btnLogin")).thenReturn("Enter");
        when(request.getParameter("email")).thenReturn("oleksandr@gmail.com");
        when(request.getParameter("password")).thenReturn("1");
        verify(dispatcher).forward(request, response);
        verify(request, times(1)).getParameter("btnLogin");


        when(mock(UserService.class).login(anyString(), anyString())).thenReturn(null);
        servlet.doPost(request, response);
        verify(request, atLeast(1)).getParameter("btnLogin");
        verify(request, atLeast(1)).getParameter("email");
        UserDAO userDao = mock(UserDAO.class);
        when(userDao.findUserByLogin(anyString())).thenReturn(null);
        servlet.doPost(request, response);
    }
    @PrepareForTest(UserService.class)
    @Test
    public void registrationTest() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn("/registration");
        when(request.getRequestDispatcher("/view/registration.jsp")).thenReturn(dispatcher);
        servlet.doGet(request, response);

        PowerMockito.mockStatic(UserService.class );
        when(mock(UserService.class).registration(anyString(), anyString(), anyString())).thenReturn(new User());
        when(request.getParameter("btnReg")).thenReturn("Registration");
        servlet.doPost(request, response);
    }






    @PrepareForTest(UserService.class)
    @Test
    public void findUserTest() throws ServletException, IOException {
        when(mock(UserService.class).findUser(anyString(), anyString())).thenReturn(null);
    }

    @Test
    public void pagesTest() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn("/check");
        when(request.getRequestDispatcher("/view/check.jsp")).thenReturn(dispatcher);
        servlet.doGet(request, response);

        when(request.getServletPath()).thenReturn("/cancel");
        when(request.getRequestDispatcher("/view/cancel.jsp")).thenReturn(dispatcher);
        servlet.doGet(request, response);

        when(request.getServletPath()).thenReturn("/login");
        when(request.getRequestDispatcher("/view/index.jsp")).thenReturn(dispatcher);
        servlet.doGet(request, response);

        when(request.getServletPath()).thenReturn("/report");
        when(request.getRequestDispatcher("/view/report.jsp")).thenReturn(dispatcher);
        servlet.doGet(request, response);

        when(request.getServletPath()).thenReturn("/registration");
        when(request.getRequestDispatcher("/view/registration.jsp")).thenReturn(dispatcher);
        servlet.doGet(request, response);
    }
}
