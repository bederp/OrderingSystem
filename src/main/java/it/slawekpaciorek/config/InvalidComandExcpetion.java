package it.slawekpaciorek.config;

import it.slawekpaciorek.controllers.AppManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidComandExcpetion extends Exception implements AppManager {

    private Logger logger = LoggerFactory.getLogger(InvalidComandExcpetion.class);
    private static final String msg = "Nierawidłowa komenda, użyj dostępnych poleceń";

    public InvalidComandExcpetion() {
        super(msg);
    };

    @Override
    public void executeCommand() {
        logger.warn("User used invalid comand");
        logger.warn(getMessage());
    }
}
