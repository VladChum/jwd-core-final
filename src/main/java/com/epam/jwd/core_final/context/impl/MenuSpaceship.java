package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.impl.FindSpaceshipService;
import com.epam.jwd.core_final.strategy.impl.UserInputStrategy;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuSpaceship implements ApplicationMenu {
    private static final Logger logger = Logger.getLogger(MenuSpaceship.class);
    private static Scanner scanner = new Scanner(System.in);
    private static MenuSpaceship instance;

    public static MenuSpaceship getInstance() {
        if (instance == null) {
            instance = new MenuSpaceship();
        }
        return instance;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        int quantityOptionsMenu = 4;
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
        System.out.println("Select options Spaceships:\n" +
                "1) find all spaceships\n" +
                "2) find all spaceships with options\n" +
                "3) find spaceship with user option\n" +
                "4) click to go back");
    }

    @Override
    public int handleUserInput(int option) {
        int returnValue = 0;
        switch (option) {
            case 1:                             //find all spaceships
                FindSpaceshipService.getInstance().findAllSpaceships().forEach(System.out::println);
                break;
            case 2:                             //find all spaceships with options
                outputAllSpaceshipsWithCriteria();
                break;
            case 3:                             // find spaceship with user options
                printSpaceshipWithUserOptions();
                break;
            case 4:                             //back
                logger.log(Level.INFO, "Return from NASSA menu !");
                returnValue = 1;
                break;
            default:
                System.out.println("incorrect input!!!! Please repeat");
        }
        return returnValue;
    }

    private void printSpaceshipWithUserOptions() {
        System.out.println("Enter options for find spaceship : ");
        Long id = UserInputStrategy.getInstance().inputID();
        System.out.println("add option ready for mission ? [y/n]");
        Boolean readyForNextMission = null;
        if (scanner.next().equals("y")) {
            readyForNextMission = UserInputStrategy.getInstance().inputReadyForNextMissions();
        }

        Criteria<Spaceship> spaceshipCriteria = SpaceshipCriteria
                .builder()
                .name(UserInputStrategy.getInstance().inputName())
                .id(id)
                .flightDistance(UserInputStrategy.getInstance().inputDistance())
                .isReadyForNextMissions(readyForNextMission)
                .builder();
        logger.log(Level.DEBUG, FindSpaceshipService.getInstance()
                .findSpaceshipByCriteria(spaceshipCriteria));
        if (FindSpaceshipService.getInstance()
                .findSpaceshipByCriteria(spaceshipCriteria).isPresent()) {
            System.out.println(FindSpaceshipService.getInstance()
                    .findSpaceshipByCriteria(spaceshipCriteria).toString());
        } else {
            System.out.println("Spaceship were not found with this criteria");
        }
        logger.log(Level.INFO, "Find spaceship with user options");
    }

    private void outputAllSpaceshipsWithCriteria() {
        System.out.println("Enter options for find spaceShips : ");
        Criteria<Spaceship> spaceshipCriteria = SpaceshipCriteria
                .builder()
                .isReadyForNextMissions(UserInputStrategy.getInstance().inputReadyForNextMissions())
                .flightDistance(UserInputStrategy.getInstance().inputDistance())
                .builder();

        FindSpaceshipService.getInstance().findAllSpaceshipsByCriteria(spaceshipCriteria)
                .forEach(System.out::println);
        logger.log(Level.INFO, "Find all spaceships with options !");
    }
}
