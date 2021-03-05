package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.impl.NassaMenu;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.strategy.impl.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Timer;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);


    public static void main(String[] args) {
        logger.log(Level.INFO, "Start application work ...");
        Timer timer = new Timer();

        try {
            CrewMemberWriteStrategy.getInstance().openFile();
            SpaceshipWriteStrategy.getInstance().openFile();
            timer.scheduleAtFixedRate(new UpdateCash(), 0, 10 * 1000);
            Application.start();
            NassaMenu.getInstance().getApplicationContext();
            CrewMemberWriteStrategy.getInstance().closeFile();
            SpaceshipWriteStrategy.getInstance().closeFile();
        } catch (InvalidStateException e) {
            logger.log(Level.ERROR, "Application failed!!!");
        }
    }
}



