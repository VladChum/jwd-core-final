package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.impl.FindMissionService;
import com.epam.jwd.core_final.service.impl.FindSpacemapService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NassaMenu implements ApplicationMenu {
    private static final Logger logger = Logger.getLogger(NassaMenu.class);

    private static NassaMenu instance;

    public static NassaMenu getInstance() {
        if (instance == null) {
            instance = new NassaMenu();
        }
        return instance;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        logger.log(Level.INFO, "Start Nassa program  ...");
        Scanner scanner = new Scanner(System.in);
        int quantityOptionsMenu = 6;
        int option = 0;

        while (option != quantityOptionsMenu) {
            printAvailableOptions();
            try {
                option = scanner.nextInt();
                handleUserInput(option);
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
       System.out.println("Select options:\n" +
               "1) view mission\n" +
               "2) create mission\n" +
               "3) view spaceship\n" +
               "4) view crew member\n" +
               "5) view planets\n" +
               "6) Exit.");
    }

    @Override
    public int handleUserInput(int option) {
        switch (option) {
            case 1:     //view mission
//                мисии которые завершились
                FindMissionService.getInstance().findAllMissions().forEach(System.out::println);
                break;
            case 2:     //create mission
                MenuCreatMission.getInstance().getApplicationContext();
                break;
            case 3:     //view spaceship
                MenuSpaceship.getInstance().getApplicationContext();
                break;
            case 4:     //view crew member
                MenuCrewMember.getInstance().getApplicationContext();
                break;
            case 5:     //view planets
                FindSpacemapService.getInstance().findAllPlanet().forEach(System.out::println);
                break;
            case 6:     //EXIT
                logger.log(Level.INFO, "Exit program !!!!");
                System.exit(0);
                break;
            default:
                System.out.println("incorrect input!!!! Please repeat");
        }
        return 0;
    }
}
