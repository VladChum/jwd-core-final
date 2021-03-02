package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.impl.FindMissionService;
import com.epam.jwd.core_final.service.impl.FindSpacemapService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuCreatMission implements ApplicationMenu {
    private static final Logger logger = Logger.getLogger(MenuCreatMission.class);

    private static MenuCreatMission instance;

    public static MenuCreatMission getInstance() {
        if (instance == null) {
            instance = new MenuCreatMission();
        }
        return instance;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        logger.log(Level.INFO, "Start creat mission  ...");
        Scanner scanner = new Scanner(System.in);

        printAvailableOptions();

        try {
            System.out.print(("Mission name: "));
            String missionName = scanner.next();
            System.out.print("Start date [Year-month-date]: ");
            LocalDate startDate = LocalDate.parse(scanner.next());
            System.out.print("End date [Year-month-date]: ");
            LocalDate endDate = LocalDate.parse(scanner.next());

            Planet from = FindSpacemapService.getInstance().getRandomPlanet();
            Planet to = FindSpacemapService.getInstance().getRandomPlanet();

            FindMissionService.getInstance().createMission(FlightMissionFactory.getInstance()
                    .create(missionName,
                    startDate,
                    endDate,
                    from,
                    to));
        } catch (InputMismatchException e) {
            logger.log(Level.ERROR, "Incorrect input");
            System.out.println("Incorrect input!!!! pleas repeat..");
            scanner.next();
        }
        logger.log(Level.INFO, "Mission  creat !!!");
        return null;
    }

    @Override
    public void printAvailableOptions() {
        System.out.println("Create Mission:\n");
    }

}
