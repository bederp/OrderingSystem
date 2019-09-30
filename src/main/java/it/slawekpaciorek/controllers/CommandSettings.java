package it.slawekpaciorek.controllers;

import it.slawekpaciorek.views.ConsoleView;

import java.util.HashMap;
import java.util.Map;

public class CommandSettings {

    private Map<String, AppManager> commandMap;

    public CommandSettings(){
        commandMap = new HashMap<>();
    }

    public CommandSettings settingsInit(){

        CommandSettings management = new CommandSettings();

        commandMap.put("menu", () -> ConsoleView.printMenu());
        commandMap.put("order", new OrdersManagment());
        commandMap.put("stats", new StatsManagment());
        commandMap.put("error", new ErrorManagment());
        commandMap.put("exit", () -> ConsoleView.exitView());

        return management;
    }

    public void runWithParameter(String string){

        if(!commandMap.containsKey(string))
            string = "error";

        commandMap.get(string).executeCommand();

    }


}
