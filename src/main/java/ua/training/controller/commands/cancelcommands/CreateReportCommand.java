package ua.training.controller.commands.cancelcommands;

import org.apache.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.dao.entity.Report;
import ua.training.service.ReportGeneratorService;
import ua.training.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class CreateReportCommand implements Command {
    private static final Logger logger = Logger.getLogger(CreateReportCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        ReportGeneratorService service = ServiceFactory.getInstance().getReportGeneratorService();
        HttpSession session = req.getSession();

        if (Objects.nonNull(req.getParameter("btnCreateXReport"))) {
            Report report = service.createAndGetX();
            session.setAttribute("reportX", report);
            logger.info("Створення звіту Х");
            session.setAttribute("reportZ", null);
            return "report";
        } else if (Objects.nonNull(req.getParameter("btnCreateZReport"))) {
            Report report = service.createAndGetZ();
            session.setAttribute("reportZ", report);
            logger.info("Створення звіту Z");
            session.setAttribute("reportX", null);
            return "report";
        }
        return "cancel";
    }
}
