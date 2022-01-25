package com.store.electronic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Controller {
    ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp);
}
