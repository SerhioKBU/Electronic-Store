package com.store.electronic.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ControllerFactory {

    private Map<String, Controller> controllerMap = new HashMap<>();

    private void init() {

        controllerMap.put("GET/login", new ShowPageController("login"));
        controllerMap.put("POST/login", new LoginControllerDto());

        controllerMap.put("GET/registration", new ShowPageController("registration"));
        controllerMap.put("POST/registration", new RegistrationController());

        controllerMap.put("GET/category", new CategoryController());

        controllerMap.put("POST/addToBasket", new AddBasketController());
        controllerMap.put("GET/addToBasket", new AddBasketController());
        controllerMap.put("GET/basket", new ShowBasketController());

    }

    public Controller getController(HttpServletRequest request) {
        if (controllerMap.isEmpty()) {
            init();
        }
        System.out.println("Request method type: " + request.getMethod() + request.getPathInfo());
        return controllerMap.get(request.getMethod() + request.getPathInfo());
    }
}
