package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.impl.FindMissionService;
import com.epam.jwd.core_final.service.impl.FindSpacemapService;
import com.epam.jwd.core_final.strategy.impl.MissionCreatStrategy;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.*;

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
            String missionName = scanner.nextLine();

            System.out.print("Start date [YYYY-MM-dd](example 2001-03-03): ");
            LocalDate startDate = LocalDate.parse(scanner.next());
            System.out.print("End date [YYYY-MM-dd](example 2001-03-03): ");
            LocalDate endDate = LocalDate.parse(scanner.next());
            MissionResult missionStatus = MissionCreatStrategy.getInstance().missionStatus(startDate, endDate);
            Spaceship spaceship = MissionCreatStrategy.getInstance().addSpaceship(missionStatus);

            List<CrewMember> crewMembers = new ArrayList<>();
            if (spaceship == null) {
                missionStatus = MissionResult.CANCELLED;
            } else {
                crewMembers = MissionCreatStrategy.getInstance().addCrewMemberForMission(spaceship, missionStatus);
            }

            Planet from = FindSpacemapService.getInstance().getRandomPlanet();
            Planet to = FindSpacemapService.getInstance().getRandomPlanet();

            FindMissionService.getInstance().createMission(FlightMissionFactory.getInstance()
                    .create(missionName,
                            startDate,
                            endDate,
                            from,
                            to,
                            spaceship,
                            crewMembers,
                            missionStatus));
        } catch (InputMismatchException e) {
            logger.log(Level.ERROR, "Incorrect input");
            System.out.println("Incorrect input!!!! pleas repeat..");
            scanner.next();
        }
        logger.log(Level.INFO, "Mission  creat !!!");
        System.out.println("Mission creat!!!!");
        return null;
    }

    @Override
    public void printAvailableOptions() {
        System.out.println("Create Mission:\n");
    }

}
