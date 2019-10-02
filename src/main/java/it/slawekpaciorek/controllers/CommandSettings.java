package it.slawekpaciorek.controllers;

import it.slawekpaciorek.config.ConsoleView;

import java.util.HashMap;
import java.util.Map;

public class CommandSettings {

    private Map<String, AppManager> commandMap;

    public CommandSettings(){
        commandMap = new HashMap<>();
    }

    public void settingsInit(){

        commandMap.put("menu", ConsoleView::printMenu);
        commandMap.put("order", new OrdersManagment());
        commandMap.put("stats", new StatsManagment());
        commandMap.put("exit", ConsoleView::exitView);

    }

    public void runWithParameter(String string){

        if(!commandMap.containsKey(string))
            string = "error";

        commandMap.get(string).executeCommand();

    }


}
