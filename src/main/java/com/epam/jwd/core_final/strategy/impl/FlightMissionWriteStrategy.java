package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.FindMissionService;
import com.epam.jwd.core_final.strategy.WriteStrategy;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class FlightMissionWriteStrategy implements WriteStrategy<FlightMission> {
    private static final Logger logger = Logger.getLogger(FlightMissionWriteStrategy.class);

    private static FlightMissionWriteStrategy instance;

    public static FlightMissionWriteStrategy getInstance() {
        if (instance == null) {
            instance = new FlightMissionWriteStrategy();
        }
        return instance;
    }

    private File flightMissionFile = new File("workDirectory/FlightMission.txt");
    private PrintWriter writeFlightMission;

    @Override
    public void writeToFile(FlightMission flightMission) throws InvalidStateException {
        writeFlightMission.println(flightMission.getId() + ","
                + flightMission.getName() + ","
                + flightMission.getDistance() + ","
                + flightMission.getMissionResult() + ","
                + flightMission.getFrom() + ","
                + flightMission.getTo() + ","
                + flightMission.getAssignedCrew() + ";");
        writeFlightMission.flush();
    }

    @Override
    public void openFile() {
        try {
            if (!flightMissionFile.exists()) {
                flightMissionFile.createNewFile();
            }
            writeFlightMission = new PrintWriter(flightMissionFile);
            writeFlightMission.println("#missionID,Name,Distance,MissionStatus,From,To, Crew");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCash() {
        openFile();
        for (int i = 0; i < FindMissionService.getInstance().findAllMissions().size(); i++) {
            try {
                writeToFile(FindMissionService.getInstance().findAllMissions().get(i));
            } catch (InvalidStateException e) {
                e.printStackTrace();
            }
        }
        closeFile();
        logger.log(Level.INFO, "Flight missions write in file!");
    }

    @Override
    public void closeFile() {
        writeFlightMission.close();
    }
}
