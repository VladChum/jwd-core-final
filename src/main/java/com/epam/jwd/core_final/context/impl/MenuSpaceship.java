package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.service.impl.FindSpaceshipService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuSpaceship implements ApplicationMenu {
    private static final Logger logger = Logger.getLogger(MenuSpaceship.class);

    private static MenuSpaceship instance;

    public static MenuSpaceship getInstance() {
        if (instance == null) {
            instance = new MenuSpaceship();
        }
        return instance;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        Scanner scanner = new Scanner(System.in);
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
        System.out.println("Select options Spaships:\n" +
                "1) find all spaceships\n" +
                "2) find all spaceships with options\n" +
                "3) find all busy spaceships\n" +
                "4) find all free spaceships\n" +
                "5) click to go back");
    }

    @Override
    public int handleUserInput(int option) {
        int returnValue = 0;
        switch (option) {
            case 1:                             //find all spaceships
                FindSpaceshipService.getInstance().findAllSpaceships().forEach(System.out::println);
                break;
            case 2:                             //find all spaceships with options
                break;
            case 3:                             //find all busy spaceships

                break;
            case 4:                             //find all free spaceships

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
}
