package it.slawekpaciorek.controllers;

import it.slawekpaciorek.views.ConsoleView;

import java.util.HashMap;
import java.util.Map;

public class AppManagement {

    private Map<String, Object> commandMap;

    public AppManagement(){
        commandMap = new HashMap<>();
    }

    public AppManagement managementInit(){

        AppManagement management = new AppManagement();

        commandMap.put("menu", new ConsoleView());
        commandMap.put("order", new OrdersManagment());
        commandMap.put("stats", new StatsManagment());
        commandMap.put("error", new ErrorManagment());
        commandMap.put("exit", true);

        return management;
    }

    public void runWithParameter(String string){

        if(!commandMap.containsKey(string))
            string = "error";

        commandMap.get(string);

    }


}
