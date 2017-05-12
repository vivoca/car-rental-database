package com.codecool.controller;

import com.codecool.database.UserLoginHandlerDAODB;
import javassist.NotFoundException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LoginController {

    private static UserLoginHandlerDAODB loginDB = new UserLoginHandlerDAODB();

    public static ModelAndView renderLogin(Request req, Response res) {
        Map params = new HashMap();
        params.put("nombre", "cecilio");
        return new ModelAndView(params, "/login");
    }

    public static ModelAndView renderSignIn(Request req, Response res) {
        if (req.session().attribute("authenticated").equals(false)) {
            Map params = new HashMap();
            params.put("valid", true);
            return new ModelAndView(params, "signin");
        } else {
            res.redirect("/");
        }
        return null;
    }

    public static ModelAndView login(Request req, Response res) throws NotFoundException, SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if(req.session().attribute("authenticated").equals(true)) {
            res.redirect("/ok");
        } else {

            String inputPassword = req.queryParams("password");
            String username = req.queryParams("username");
            if (loginDB.authenticate(username, inputPassword)) {
                req.session().attribute("authenticated", true);
                req.session().attribute("username", username);
                res.redirect("/ok");
                return null;
            } else {
                Map params = new HashMap<>();
                params.put("valid", false);
                params.put("message", "Incorrect username or password.");
                return new ModelAndView(params, "signin");
            }
        }
        return null;
    }

    public static ModelAndView renderSignUp(Request req, Response res) throws NotFoundException, SQLException {
        Map params = new HashMap<>();
        if (req.session().attribute("authenticated").equals(false)) {

            return new ModelAndView(params, "signup");
        } else {
            params.put("valid", false);
            params.put("message", "You are currently logged in. Please log out to register.");
            return new ModelAndView(params, "signup");
        }
    }

    public static ModelAndView createUser(Request req, Response res) throws NotFoundException, SQLException {
        Map params = new HashMap();
        if (req.session().attribute("authenticated").equals(true)) {
            params.put("valid", false);
            params.put("message", "You are logged in. Please log out to continue.");
            return new ModelAndView(params, "signup");
        } else {
            if (!(loginDB.checkIfEmailExists(req.queryParams("email")) || loginDB.checkIfUsernameExists(req.queryParams("username")))) {
                loginDB.add(req.queryParams("username"), req.queryParams("password"), req.queryParams("email"));
                res.redirect("/signin");
            }else {
                params.put("valid", false);
                params.put("message", "Email or username already in use.");
                return new ModelAndView(params, "signup");
            }
        }
        return null;
    }

    public static ModelAndView logOut(Request req, Response res){
        req.session().attribute("authenticated", false);
        req.session().attribute("username", null);
        res.redirect("/logout");
        return  null;
    }


}
