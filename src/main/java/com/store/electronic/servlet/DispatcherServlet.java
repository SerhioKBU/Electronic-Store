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

@WebServlet(urlPatterns = "/electronic-store/*")
public class DispatcherServlet extends HttpServlet {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class.getName());
    private ControllerFactory controllerFactory;

    @Override
    public void init() throws ServletException {
        if(logger.isInfoEnabled()) {
            logger.info(ANSI_GREEN + "Servlet is created" + ANSI_RESET);
        }
        controllerFactory = new ControllerFactory();
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Controller controller = controllerFactory.getController(req);
        System.out.println(controller);
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
            System.out.println("Path: " + path);
            req.getRequestDispatcher(path).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
