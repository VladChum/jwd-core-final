package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.service.impl.FindCrewMemberService;
import com.epam.jwd.core_final.service.impl.FindSpacemapService;
import com.epam.jwd.core_final.service.impl.FindSpaceshipService;
import com.epam.jwd.core_final.strategy.CreatStrategy;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MissionCreatStrategy implements CreatStrategy {
    private static final Logger logger = Logger.getLogger(MissionCreatStrategy.class);

    private static MissionCreatStrategy instance;

    public static MissionCreatStrategy getInstance() {
        if (instance == null) {
            instance = new MissionCreatStrategy();
        }
        return instance;
    }

    @Override
    public MissionResult missionStatus(LocalDate startDate, LocalDate endDate) {
        MissionResult status = MissionResult.PLANNED;
        if (startDate.isBefore(LocalDate.now()) && endDate.isBefore(LocalDate.now())) {  // if mission start and ending
            status = returnStatusForEndingMission();
        } else if (startDate.isBefore(LocalDate.now()) && endDate.isAfter(LocalDate.now())) {// if start but nod ending
            status = returnStatusForStartMissionAndNotEnding();
        }
        logger.log(Level.INFO, status);
        return status;
    }

    private MissionResult returnStatusForStartMissionAndNotEnding() {
        MissionResult missionResult = MissionResult.IN_PROGRESS;
        Random random = new Random();
        if (random.nextInt(4) == 2) {
            missionResult = MissionResult.FAILED;
        }
        return missionResult;
    }

    private MissionResult returnStatusForEndingMission() {
        MissionResult missionResult = MissionResult.COMPLETED;
        Random random = new Random();
        if (random.nextInt(4) == 2) {
            missionResult = MissionResult.FAILED;
        }
        return missionResult;
    }

    @Override
    public Spaceship addSpaceship(MissionResult missionResult) {
        List<Long> cashIdSpaceShipForThisMission = idSpaceShipForThisMission();
        Scanner scanner = new Scanner(System.in);

        Spaceship returnSpaceship = null;
        String numberID;
        if (cashIdSpaceShipForThisMission.size() != 0) {
            System.out.println("Please enter number of spaceship id: ");
            numberID = scanner.next();
            Long number = Long.valueOf(numberID);
            for (int i = 0; i < cashIdSpaceShipForThisMission.size(); i++) {
                if (cashIdSpaceShipForThisMission.get(i).equals(number)) {
                    logger.log(Level.DEBUG, cashIdSpaceShipForThisMission.get(i));
                    Criteria<Spaceship> spaceshipCriteria = SpaceshipCriteria
                            .builder()
                            .isReadyForNextMissions(true)
                            .id(cashIdSpaceShipForThisMission.get(i))
                            .builder();
                    returnSpaceship = FindSpaceshipService.getInstance()
                            .findAllSpaceshipsByCriteria(spaceshipCriteria).get(0);
                    if (missionResult != MissionResult.COMPLETED) {
                        FindSpaceshipService.getInstance().assignSpaceshipOnMission(FindSpaceshipService.getInstance()
                                .findAllSpaceshipsByCriteria(spaceshipCriteria).get(0));
                    }
                    ;
                }
            }
        }
        logger.log(Level.INFO, returnSpaceship);
        return returnSpaceship;
    }

    private List<Long> idSpaceShipForThisMission() {
        List<Long> cashIdSpaceShipForThisMission = new ArrayList<>();
        Short quantityMissionSpecialistCrewMember = 0;
        Short quantityFlightEngineerCrewMember = 0;
        Short quantityPilotCrewMember = 0;
        Short quantityCommanderCrewMember = 0;

        List<CrewMember> allCrewMember = FindCrewMemberService.getInstance().findAllCrewMembers();
        for (int i = 0; i < allCrewMember.size(); i++) {
            if (allCrewMember.get(i).getReadyForNextMissions()
                    && allCrewMember.get(i).getRole() == Role.MISSION_SPECIALIST) {
                quantityMissionSpecialistCrewMember++;
            } else if (allCrewMember.get(i).getReadyForNextMissions()
                    && allCrewMember.get(i).getRole() == Role.FLIGHT_ENGINEER) {
                quantityFlightEngineerCrewMember++;
            } else if (allCrewMember.get(i).getReadyForNextMissions()
                    && allCrewMember.get(i).getRole() == Role.PILOT) {
                quantityPilotCrewMember++;
            } else if (allCrewMember.get(i).getReadyForNextMissions()
                    && allCrewMember.get(i).getRole() == Role.COMMANDER) {
                quantityCommanderCrewMember++;
            }
        }
        logger.log(Level.DEBUG, "scan all crewMember role " + quantityMissionSpecialistCrewMember + " "
                + quantityFlightEngineerCrewMember + " " + quantityPilotCrewMember + " " + quantityCommanderCrewMember);

        List<Spaceship> allSpaceships = FindSpaceshipService.getInstance().findAllSpaceships();
        for (int i = 0; i < allSpaceships.size(); i++) {
            if (allSpaceships.get(i).getCrew().get(Role.MISSION_SPECIALIST) <= quantityMissionSpecialistCrewMember
                    && allSpaceships.get(i).getCrew().get(Role.FLIGHT_ENGINEER) <= quantityFlightEngineerCrewMember
                    && allSpaceships.get(i).getCrew().get(Role.PILOT) <= quantityPilotCrewMember
                    && allSpaceships.get(i).getCrew().get(Role.COMMANDER) <= quantityCommanderCrewMember
                    && allSpaceships.get(i).getReadyForNextMissions()) {
                System.out.println(allSpaceships.get(i));
                cashIdSpaceShipForThisMission.add(allSpaceships.get(i).getId());
            }
        }
        return cashIdSpaceShipForThisMission;
    }

    @Override
    public List<CrewMember> addCrewMemberForMission(Spaceship spaceship, MissionResult missionResult) {
        List<CrewMember> crewMembers = new ArrayList<>();
        Short quantityMissionSpecialistCrewMember = spaceship.getCrew().get(Role.MISSION_SPECIALIST);
        Short quantityFlightEngineerCrewMember = spaceship.getCrew().get(Role.FLIGHT_ENGINEER);
        Short quantityPilotCrewMember = spaceship.getCrew().get(Role.PILOT);
        Short quantityCommanderCrewMember = spaceship.getCrew().get(Role.COMMANDER);

        List<CrewMember> allCrewMember = FindCrewMemberService.getInstance().findAllCrewMembers();
        for (int i = 0; i < allCrewMember.size(); i++) {
            if (allCrewMember.get(i).getReadyForNextMissions()
                    && allCrewMember.get(i).getRole() == Role.MISSION_SPECIALIST
                    && quantityMissionSpecialistCrewMember > 0) {
                quantityMissionSpecialistCrewMember--;
                crewMembers.add(allCrewMember.get(i));
                if (MissionResult.COMPLETED != missionResult) {
                    allCrewMember.get(i).crewMemberIsNotReadeForNextMissions();
                }
            } else if (allCrewMember.get(i).getReadyForNextMissions()
                    && allCrewMember.get(i).getRole() == Role.FLIGHT_ENGINEER
                    && quantityFlightEngineerCrewMember > 0) {
                quantityFlightEngineerCrewMember--;
                crewMembers.add(allCrewMember.get(i));
                if (MissionResult.COMPLETED != missionResult) {
                    allCrewMember.get(i).crewMemberIsNotReadeForNextMissions();
                }
            } else if (allCrewMember.get(i).getReadyForNextMissions()
                    && allCrewMember.get(i).getRole() == Role.PILOT
                    && quantityPilotCrewMember > 0) {
                quantityPilotCrewMember--;
                crewMembers.add(allCrewMember.get(i));
                if (MissionResult.COMPLETED != missionResult) {
                    allCrewMember.get(i).crewMemberIsNotReadeForNextMissions();
                }
            } else if (allCrewMember.get(i).getReadyForNextMissions()
                    && allCrewMember.get(i).getRole() == Role.COMMANDER
                    && quantityCommanderCrewMember > 0) {
                quantityCommanderCrewMember--;
                crewMembers.add(allCrewMember.get(i));
                if (MissionResult.COMPLETED != missionResult) {
                    allCrewMember.get(i).crewMemberIsNotReadeForNextMissions();
                }
            }
        }
        logger.log(Level.INFO, crewMembers);
        return crewMembers;
    }

    public Planet addPlanetForMission(Planet firstPlanet) {
        List<Long> cashIdPlanetForThisMission = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        FindSpacemapService.getInstance().findAllPlanet().forEach(System.out::println);
        for (int i = 0; i < FindSpacemapService.getInstance().findAllPlanet().size(); i++) {
            cashIdPlanetForThisMission.add(FindSpacemapService.getInstance().findAllPlanet().get(i).getId());
        }

        Planet returnPlanet = null;
        Long numberID;
        if (cashIdPlanetForThisMission.size() != 0) {
            while (true) {
                System.out.println("Please enter number of Planet id: ");
                numberID = Long.valueOf(scanner.next());
                if (!numberID.equals(firstPlanet.getId()) && cashIdPlanetForThisMission.contains(numberID)) {
                    for (int i = 0; i < FindSpacemapService.getInstance().findAllPlanet().size(); i++) {
                        if (numberID.equals(FindSpacemapService.getInstance().findAllPlanet().get(i).getId())) {
                            returnPlanet = FindSpacemapService.getInstance().findAllPlanet().get(i);
                            break;
                        }
                    }
                    break;
                }
            }
        }
        logger.log(Level.INFO, returnPlanet);
        return returnPlanet;
    }

}
