package com.codecool;


import com.codecool.controller.LoginController;
import com.codecool.database.AbstractDBHandler;
import com.codecool.database.BuildTable;
import spark.template.thymeleaf.ThymeleafTemplateEngine;


import static spark.Spark.*;

public class Server {

    public static void main(String[] args) {

        staticFileLocation("/public");
        BuildTable.build();

        before( "*", (request, response) ->
        {
            AbstractDBHandler.getConnection();
            if (request.session().attribute("authenticated") == null) {
                request.session().attribute("authenticated", false);
                request.session().attribute("username", false);
            }
        });
        get("/hello", (req, res) -> "Hello world!");
        get("/test", (req, res) -> {res.redirect("test.html"); return null;});
        get("/", (req, res) -> "index");
        get("/ok", (req, res) -> "ok");
        get("/signin", LoginController::renderSignIn, new ThymeleafTemplateEngine());
        get("/signup", LoginController::renderSignUp, new ThymeleafTemplateEngine());
        get("/logout", LoginController::logOut, new ThymeleafTemplateEngine());
        post("/signin", LoginController::login, new ThymeleafTemplateEngine());
        post("/signup", LoginController::createUser, new ThymeleafTemplateEngine());
    }



}
