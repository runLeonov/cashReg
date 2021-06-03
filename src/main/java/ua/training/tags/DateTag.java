package ua.training.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy:MM:dd");
            LocalDateTime now = LocalDateTime.now();
            formatter.format(now);
            out.print(now);
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}
