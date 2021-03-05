package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.impl.NassaMenu;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.strategy.impl.CrewMemberWriteStrategy;
import com.epam.jwd.core_final.strategy.impl.SpaceshipWriteStrategy;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);


    public static void main(String[] args) {
        logger.log(Level.INFO, "Start application work ...");

        try {
            CrewMemberWriteStrategy.getInstance().openFile();
            SpaceshipWriteStrategy.getInstance().openFile();
            Application.start();
            NassaMenu.getInstance().getApplicationContext();
            CrewMemberWriteStrategy.getInstance().closeFile();
            SpaceshipWriteStrategy.getInstance().closeFile();
        } catch (InvalidStateException e) {
            logger.log(Level.ERROR, "Application failed!!!");
        }
    }
}



