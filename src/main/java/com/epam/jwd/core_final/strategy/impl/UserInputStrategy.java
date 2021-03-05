package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.service.impl.FindMissionService;
import com.epam.jwd.core_final.strategy.InputStrategy;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

public class UserInputStrategy implements InputStrategy {
    private static final Logger logger = Logger.getLogger(UserInputStrategy.class);

    private static UserInputStrategy instance;
    private static Scanner scanner = new Scanner(System.in);

    public static UserInputStrategy getInstance() {
        if (instance == null) {
            instance = new UserInputStrategy();
        }
        return instance;
    }

    public Boolean inputReadyForNextMissions() {
        System.out.println("Ready for next mission? [y/n] ...");
        Boolean ready = false;
        if (scanner.next().equals("y")) {
            ready = true;
        }
        return ready;
    }

    @Override
    public MissionResult inputMissionResult() {
        System.out.println("add mission result options [y/n] ...");
        MissionResult missionResult = null;
        if (scanner.next().equals("y")) {
            missionResult = toStringMissionStatus();
        }
        return missionResult;
    }

    @Override
    public String inputName() {
        System.out.println("add  name options [y/n] ...");
        String name = null;
        if (scanner.next().equals("y")) {
            System.out.println("Enter  name ...");
            name = scanner.next();
        }
        return name;
    }

    public String inputNameForCreate() {
        System.out.println("Enter  name ...");
        return scanner.next();
    }

    @Override
    public Long inputID() {
        System.out.println("add  ID options [y/n] ...");
        Long id = null;
        if (scanner.next().equals("y")) {
            System.out.println("Enter  id ...");
            id = Long.valueOf(scanner.next());
        }
        return id;
    }

    @Override
    public Long inputDistance() {
        System.out.println("Enter Distance : ");
        Long distance = null;
        try {
            distance =  Long.valueOf(scanner.next());
        } catch (InputMismatchException e) {
            logger.log(Level.ERROR, "Incorrect input");
            System.out.println("Incorrect input!!!! pleas repeat..");
            scanner.next();
        }
        return distance;
    }

    private MissionResult toStringMissionStatus() {
        MissionResult missionResult;
        System.out.println("Mission status :\n" +
                "1) CANCELLED\n" +
                "2) FAILED,\n" +
                "3) PLANNED,\n" +
                "4) IN_PROGRESS,\n" +
                "5) COMPLETED");
        return choseMissionStatus(scanner.nextInt());
    }

    public MissionResult choseMissionStatus(int number) {
        MissionResult missionResult;
        switch (number) {
            case 1:
                missionResult = MissionResult.CANCELLED;
                break;
            case 2:
                missionResult = MissionResult.FAILED;
                break;
            case 3:
                missionResult = MissionResult.PLANNED;
                break;
            case 4:
                missionResult = MissionResult.IN_PROGRESS;
                break;
            default:
                missionResult = MissionResult.COMPLETED;
        }
        return missionResult;
    }

    public Rank inputRank() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of rank :");
        System.out.println("1) TRAINEE\n"
                + "2) SECOND_OFFICER\n"
                + "3) FIRST_OFFICER\n"
                + "4) CAPTAIN");
        int rank = 0;
        while ((rank < 1) || (rank > 4)) {
            try {
                rank = scanner.nextInt();
            } catch (InputMismatchException e) {
                logger.log(Level.ERROR, "Incorrect input");
                System.out.println("Incorrect input!!!! pleas repeat..");
                scanner.next();
            }
        }
        return Rank.resolveRankById(rank);
    }

    public Role inputRole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of Role :");
        System.out.println("1)MISSION_SPECIALIST\n"
                + "2) FLIGHT_ENGINEER\n"
                + "3) PILOT\n"
                + "4) COMMANDER");
        int role = 0;
        while ((role < 1) || (role > 4)) {
            try {
                role = scanner.nextInt();
            } catch (InputMismatchException e) {
                logger.log(Level.ERROR, "Incorrect input");
                System.out.println("Incorrect input!!!! pleas repeat..");
                scanner.next();
            }
        }
        return Role.resolveRoleById(role);
    }

    public Map<Role, Short> crewForSpaceship() {
        Map<Role, Short> crew = new HashMap<>();
        System.out.println("Enter number MISSION_SPECIALIST : ");
        crew.put(Role.MISSION_SPECIALIST, Short.valueOf(scanner.next()));
        System.out.println("Enter number FLIGHT_ENGINEER : ");
        crew.put(Role.FLIGHT_ENGINEER, Short.valueOf(scanner.next()));
        System.out.println("Enter number Pilot : ");
        crew.put(Role.PILOT, Short.valueOf(scanner.next()));
        System.out.println("Enter number COMMANDER : ");
        crew.put(Role.COMMANDER, Short.valueOf(scanner.next()));
        return crew;
    }

    public void progressFlightMission(FlightMission flightMission) {
        if (flightMission.getMissionResult().equals(MissionResult.IN_PROGRESS)) {
            double distanceBetweenDate = DAYS.between(flightMission.getStartDate(), flightMission.getEndDate());
            System.out.println("Mission duration : " + distanceBetweenDate + "  days");
            double distanceBetweenStartDateAndNowTime = DAYS.between(flightMission.getStartDate(), LocalDate.now());
            System.out.println("On the way :  " + distanceBetweenStartDateAndNowTime + "  days");
            double progress = distanceBetweenStartDateAndNowTime / distanceBetweenDate * 100;
            System.out.println("Progress  : " + progress + "%");
        }
    }
}
