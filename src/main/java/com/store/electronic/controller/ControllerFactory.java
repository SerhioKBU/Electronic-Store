package com.store.electronic.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ControllerFactory {

    private Map<String, Controller> controllerMap = new HashMap<>();

    private void init() {
        //controllerMap.put("GET/category", new ShowProfileController());
        controllerMap.put("GET/login", new ShowPageController("login"));
        controllerMap.put("POST/login", new LoginControllerDto());
        controllerMap.put("GET/profileLogin", new ShowPageController("profileLogin"));
        //controllerMap.put("GET/profile", new ShowPageController("profile"));
        controllerMap.put("GET/registration", new ShowPageController("registration"));
        controllerMap.put("POST/registration", new RegistrationController());
        controllerMap.put("GET/registrationProfile", new ShowPageController("registrationProfile"));
        controllerMap.put("GET/category", new CategoryController());
        controllerMap.put("GET/buyProduct", new BuyProductController());

    }

    public Controller getController(HttpServletRequest request) {
        if (controllerMap.isEmpty()) {
            init();
        }
        return controllerMap.get(request.getMethod() + request.getPathInfo());
    }
}
