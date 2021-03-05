package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.strategy.WriteStrategy;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class SpaceshipWriteStrategy {
    private static final Logger logger = Logger.getLogger(SpaceshipWriteStrategy.class);

    private static SpaceshipWriteStrategy instance;

    public static SpaceshipWriteStrategy getInstance() {
        if (instance == null) {
            instance = new SpaceshipWriteStrategy();
        }
        return instance;
    }

    private File spaceshipFile = new File("workDirectory/SpaceShipFile.txt");
    private PrintWriter writeSpaceship;


    public void writeToFile(Spaceship spaceship) throws InvalidStateException {
        logger.log(Level.INFO, "write information about spaceships ...");
//        name;distance;crew {roleid:count,roleid:count,roleid:count,roleid:count}
        writeSpaceship.println(spaceship.getName() + ";"
                + spaceship.getFlightDistance() + ";{1:"
                + spaceship.getCrew().get(Role.MISSION_SPECIALIST)
                + ",2:" + spaceship.getCrew().get(Role.FLIGHT_ENGINEER)
                + ",3:" + spaceship.getCrew().get(Role.PILOT)
                + ",4:" + spaceship.getCrew().get(Role.COMMANDER) + "}");
        writeSpaceship.flush();
    }


    public void openFile() {
        try {
            if (!spaceshipFile.exists()) {
                spaceshipFile.createNewFile();
            }

            writeSpaceship = new PrintWriter(spaceshipFile);
            writeSpaceship.println("#");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFile() {
        writeSpaceship.close();
    }
}
