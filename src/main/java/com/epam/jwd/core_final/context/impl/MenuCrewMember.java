package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.impl.FindCrewMemberService;
import com.epam.jwd.core_final.strategy.impl.CrewMemberWriteStrategy;
import com.epam.jwd.core_final.strategy.impl.UserInputStrategy;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuCrewMember implements ApplicationMenu {
    private static final Logger logger = Logger.getLogger(MenuCrewMember.class);

    private static MenuCrewMember instance;
    private static Scanner scanner = new Scanner(System.in);

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
                "3) find crew member with user options\n" +
                "4) create crew member\n" +
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
                printAllCrewMemberWithOptions();
                break;
            case 3:                             //find crew member with user options
                printCrewMemberWithUserOptions();
                break;
            case 4:                             // creat crew member
                createCrewMemberWithUserOptions();
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

    private void printCrewMemberWithUserOptions() {
        System.out.println("Enter options for find crew member : ");
        System.out.println("add option ready for mission ? [y/n]");
        Boolean readyForNextMission = null;
        if (scanner.next().equals("y")) {
            readyForNextMission = UserInputStrategy.getInstance().inputReadyForNextMissions();
        }

        Criteria<CrewMember> crewMembersCriteria = CrewMemberCriteria
                .builder()
                .name(UserInputStrategy.getInstance().inputName())
                .id(UserInputStrategy.getInstance().inputID())
                .isReadyForNextMissions(readyForNextMission)
                .builder();
        logger.log(Level.DEBUG, FindCrewMemberService.getInstance()
                .findCrewMemberByCriteria(crewMembersCriteria));
        if (FindCrewMemberService.getInstance()
                .findCrewMemberByCriteria(crewMembersCriteria).isPresent()) {
            System.out.println(FindCrewMemberService.getInstance()
                    .findCrewMemberByCriteria(crewMembersCriteria).toString());
            System.out.println("Update this crew member? [y/n] ...");
            if (scanner.next().equals("y")) {
                FindCrewMemberService.getInstance().updateCrewMemberDetails(FindCrewMemberService.getInstance()
                        .findCrewMemberByCriteria(crewMembersCriteria).get());
            }
        } else {
            System.out.println("Crew member were not found with this criteria");
        }
        logger.log(Level.INFO, "Find crew member with user options");
    }

    private void printAllCrewMemberWithOptions() {
        System.out.println("Enter options for find crew member : ");

        Criteria<CrewMember> crewMemberCriteria = CrewMemberCriteria
                .builder()
                .isReadyForNextMissions(UserInputStrategy.getInstance().inputReadyForNextMissions())
                .builder();
        FindCrewMemberService.getInstance()
                .findAllCrewMembersByCriteria(crewMemberCriteria)
                .forEach(System.out::println);
        logger.log(Level.INFO, "Output all missions with user options !");
    }

    private void createCrewMemberWithUserOptions() {
        logger.log(Level.INFO, "Start create crew member ...");
        String name = UserInputStrategy.getInstance().inputNameForCreate();
        Role role = UserInputStrategy.getInstance().inputRole();
        Rank rank = UserInputStrategy.getInstance().inputRank();

        try {
            CrewMemberWriteStrategy.getInstance().writeToFile(CrewMemberFactory.getInstance().create(name, role, rank));
        } catch (InvalidStateException e) {
            e.printStackTrace();
        }
        System.out.println("Crew member was creat");
        logger.log(Level.INFO, "crew member creat !!!");
    }
}


