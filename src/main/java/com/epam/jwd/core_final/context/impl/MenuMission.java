package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.service.impl.FindMissionService;
import com.epam.jwd.core_final.strategy.impl.FlightMissionWriteStrategy;
import com.epam.jwd.core_final.strategy.impl.UserInputStrategy;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuMission implements ApplicationMenu {
    private static final Logger logger = Logger.getLogger(MenuMission.class);
    private static Scanner scanner = new Scanner(System.in);

    private static MenuMission instance;

    public static MenuMission getInstance() {
        if (instance == null) {
            instance = new MenuMission();
        }
        return instance;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        logger.log(Level.INFO, "Start view missions  ...");

        int quantityOptionsMenu = 5;
        int option = 0;
        while (option != quantityOptionsMenu) {
            printAvailableOptions();
            try {
                option = scanner.nextInt();
                if (handleUserInput(option) == 1) {
                    break;
                }
            } catch (InputMismatchException e) {
                logger.log(Level.ERROR, "Incorrect input");
                System.out.println("Incorrect input!!!! pleas repeat..");
                scanner.next();
            }
        }
        return null;
    }

    @Override
    public void printAvailableOptions() {
        System.out.println("Select options Mission:\n" +
                "1) view all missions\n" +
                "2) view all missions with options\n" +
                "3) find mission with user options\n" +
                "4) write all mission in file\n" +
                "5) click to go back");
    }

    @Override
    public int handleUserInput(int option) {
        int returnValue = 0;
        switch (option) {
            case 1:                             //find all crew member
                FindMissionService.getInstance().findAllMissions().forEach(System.out::println);
                break;
            case 2:                             //find all missions with options
                printAllMissionsWithOptions();
                break;
            case 3:                             //find mission with user options
                printMissionWithUserOptions();
                break;
            case 4:
                FlightMissionWriteStrategy.getInstance().writeCash();
                System.out.println("Flight missions write in file!");
                break;
            case 5:                             //back
                logger.log(Level.INFO, "Return from NASSA menu !");
                returnValue = 1;
                break;
            default:
                System.out.println("incorrect input!!!! Please repeat");
        }
        return returnValue;
    }

    private void printMissionWithUserOptions() {
        System.out.println("Enter options for find mission : ");
        MissionResult missionResult = UserInputStrategy.getInstance().inputMissionResult();
        String name = UserInputStrategy.getInstance().inputName();
        Long id = UserInputStrategy.getInstance().inputID();

        Criteria<FlightMission> flightMissionCriteria = FlightMissionCriteria
                .builder()
                .missionName(name)
                .missionResult(missionResult)
                .id(id)
                .builder();
        logger.log(Level.DEBUG, FindMissionService.getInstance()
                .findMissionByCriteria(flightMissionCriteria));
        if (FindMissionService.getInstance()
                .findMissionByCriteria(flightMissionCriteria).isPresent()) {
            System.out.println(FindMissionService.getInstance()
                    .findMissionByCriteria(flightMissionCriteria).toString());
            UserInputStrategy.getInstance().progressFlightMission(FindMissionService.getInstance()
                    .findMissionByCriteria(flightMissionCriteria).get());
        } else {
            System.out.println("Mission were not found with this criteria");
        }
        logger.log(Level.INFO, "Find mission with user options");
    }

    private void printAllMissionsWithOptions() {
        System.out.println("Enter mission status for find all missions : ");
        MissionResult missionResult = UserInputStrategy.getInstance().inputMissionResult();
        Criteria<FlightMission> flightMissionCriteria = FlightMissionCriteria
                .builder()
                .missionResult(missionResult)
                .builder();
        FindMissionService.getInstance()
                .findAllMissionsByCriteria(flightMissionCriteria)
                .forEach(System.out::println);
        logger.log(Level.INFO, "Output all missions with user options !");
    }
}
