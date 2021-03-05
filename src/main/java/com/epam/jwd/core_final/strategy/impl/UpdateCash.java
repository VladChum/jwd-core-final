package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.exception.InvalidStateException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.TimerTask;

public class UpdateCash extends TimerTask {
    private static Logger logger = Logger.getLogger(UpdateCash.class);

    @Override
    public void run() {
        logger.log(Level.INFO, "Update cash!");
        CrewMemberWriteStrategy.getInstance().closeFile();
        SpaceshipWriteStrategy.getInstance().closeFile();
        try {
            CrewMemberFetchStrategy.getInstance().fetchFromFile("workDirectory/CrewMemberFile.txt");
            SpaceshipFetchStrategy.getInstance().fetchFromFile("workDirectory/SpaceShipFile.txt");
        } catch (InvalidStateException e) {
            e.printStackTrace();
        }
        CrewMemberWriteStrategy.getInstance().openFile();
        SpaceshipWriteStrategy.getInstance().openFile();
        CrewMemberWriteStrategy.getInstance().writeCash();
        SpaceshipWriteStrategy.getInstance().writeCash();
    }
}
