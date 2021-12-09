package com.store.electronic.controller;

import com.store.electronic.authorisation.AuthUser;
import com.store.electronic.dto.LoginPageDto;
import com.store.electronic.entity.Account;
import com.store.electronic.entity.Role;
import com.store.electronic.entity.RoleEnum;
import com.store.electronic.entity.User;
import com.store.electronic.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Registration of new account for user
 * @author serhii_chebanov
 */
public class RegistrationController implements Controller{
    private final String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
    /**
     * @param req - Http Servlet Request
     * @param resp - Http Servlet Response
     * @return User Account Profile
     */
    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        RegistrationUserService registrationUserService = new RegistrationUserService();

        boolean isCreated = false;
        boolean isFind = false;
        String login = req.getParameter("userName");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String name = req.getParameter("name");

        boolean validPassword = isValidPassword(password, regex);

        //System.out.println("Weak password -" + validPassword);
        if (!validPassword) {
            try {
                throw new RegistrationException("Weak password", RegistrationException.ErrorType.PASSWORD_WEAK);
            } catch (RegistrationException e) {
                e.printStackTrace();
            }
        }

        if (login.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty()) {
            return new ControllerResultDto("registration");
        }

        AccountService accountService = new AccountService();
        Account account = new Account();

        try {
            isFind = accountService.findByValue(login);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ControllerResultDto("error");
        }
        System.out.println("new login " + login);
        System.out.println("db login " + account.getLogin());

        if (isFind) {
            //throw new RegistrationException("Login already EXIST", RegistrationException.ErrorType.LOGIN_EXIST);
            return new ControllerResultDto("registrUserExist");
        }

        User user = new User();
        user.setAccount(new Account());
        user.getAccount().setLogin(login).setPassword(password).setRole(new Role(RoleEnum.USER));
        user.setUserName(name).setEmail(email);
        System.out.println("user:  " + user);

        try {
            isCreated = registrationUserService.create(user);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ControllerResultDto("error");
        }

        if (isCreated) {
            HttpSession session = req.getSession();
            //session.setAttribute("account", user.getAccount());
            session.setAttribute("authUser", new AuthUser().setLogin(login).setRole(RoleEnum.USER));
            return new ControllerResultDto("registrationProfile");
        } else {
            return new ControllerResultDto("error-403");
        }
    }

    public static boolean isValidPassword(String password,String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}