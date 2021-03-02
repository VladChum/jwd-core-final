package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.service.impl.FindCrewMemberService;
import com.epam.jwd.core_final.service.impl.FindSpaceshipService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuCrewMember implements ApplicationMenu {
    private static final Logger logger = Logger.getLogger(MenuCrewMember.class);

    private static MenuCrewMember instance;

    public static MenuCrewMember getInstance() {
        if (instance == null) {
            instance = new MenuCrewMember();
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
        System.out.println("Select options Crew member:\n" +
                "1) find all crew member\n" +
                "2) find all crew member with options\n" +
                "3) find all busy crew member\n" +
                "4) find all free crew member\n" +
                "5) click to go back");
    }

    @Override
    public int handleUserInput(int option) {
        int returnValue = 0;
        switch (option) {
            case 1:                             //find all crew member
                FindCrewMemberService.getInstance().findAllCrewMembers().forEach(System.out::println);
                break;
            case 2:                             //find all crew member with options
                break;
            case 3:                             //find all busy crew member

                break;
            case 4:                             //find all free crew member

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
