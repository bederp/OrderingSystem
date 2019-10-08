package it.slawekpaciorek.config;

import it.slawekpaciorek.controllers.AppManager;
import it.slawekpaciorek.controllers.ImportManagement;
import it.slawekpaciorek.controllers.OrdersManagment;
import it.slawekpaciorek.controllers.StatsManagment;

import java.util.HashMap;
import java.util.Map;

public class CommandSettings {

    private Map<String, AppManager> commandMap;

    public CommandSettings(){
        commandMap = new HashMap<>();
    }

    public void settingsInit(){

        commandMap.put("menu", ConsoleView::printMenu);
        commandMap.put("orders", new OrdersManagment());
        commandMap.put("stats", new StatsManagment());
        commandMap.put("error", new InvalidComandExcpetion());
        commandMap.put("exit", ConsoleView::exitView);
        commandMap.put("import", new ImportManagement());

    }

    public void runWithParameter(String string){

        if(!commandMap.containsKey(string))
            string = "error";

        commandMap.get(string).executeCommand();
    }


}
