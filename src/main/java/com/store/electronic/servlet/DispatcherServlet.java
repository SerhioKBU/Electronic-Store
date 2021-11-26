package com.store.electronic.servlet;

import com.store.electronic.controller.Controller;
import com.store.electronic.controller.ControllerFactory;
import com.store.electronic.controller.ControllerResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/electronic-store/*")
public class DispatcherServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    private ControllerFactory controllerFactory;

    @Override
    public void init() throws ServletException {
        controllerFactory = new ControllerFactory();
        super.init();
        logger.info("Servlet is created");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Controller controller = controllerFactory.getController(req);
        try {
            ControllerResultDto result = controller.execute(req, resp);
            doForwardOrRedirect(result, req, resp);
        } catch (Exception e) {
            throw new ServletException("Cannot execute action", e);
        }
    }

    private void doForwardOrRedirect(ControllerResultDto result, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (result.isRedirect()) {
            resp.sendRedirect(result.getView());
        } else {
            String path = "/WEB-INF/jsp/" + result.getView() + ".jsp";
            req.getRequestDispatcher(path).forward(req, resp);
        }
    }
}
