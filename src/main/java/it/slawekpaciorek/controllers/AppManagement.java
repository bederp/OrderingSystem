package it.slawekpaciorek.controllers;

import it.slawekpaciorek.views.ConsoleView;

import java.util.HashMap;
import java.util.Map;

public class AppManagement {

    private final Map<String, Object> commands;

    public static AppManagement management(){

        AppManagement manager = new AppManagement();

        manager.commands.put("menu", new ConsoleView());

        return manager;

    }

    private AppManagement(){
        commands = new HashMap<>();
    }


}
