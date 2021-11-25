package com.store.electronic.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ControllerFactory {

    private Map<String, Controller> controllerMap = new HashMap<>();

    private void init() {
        //controllerMap.put("GET/profile", new ShowProfileController());
        //controllerMap.put("GET/profile", new ShowPageController("profile"));
        //controllerMap.put("GET/login", new ShowPageController("login"));
        //controllerMap.put("POST/login", new LoginController());
        controllerMap.put("GET/registration", new ShowPageController("registration"));
        controllerMap.put("POST/registration", new RegistrationController());
        controllerMap.put("GET/registrationProfile", new ShowPageController("registrationProfile"));

    }

    public Controller getController(HttpServletRequest request) {
        if (controllerMap.isEmpty()) {
            init();
        }

        return controllerMap.get(request.getMethod() + request.getPathInfo());
    }
}
