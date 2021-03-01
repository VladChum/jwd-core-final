package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.impl.FindSpaceshipService;
import com.epam.jwd.core_final.strategy.FetchStrategy;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SpaceshipFetchStrategy implements FetchStrategy {
    private static final Logger logger = Logger.getLogger(SpaceshipFetchStrategy.class);

    @Override
    public void fetchFromFile(String pathName) throws InvalidStateException {
        logger.log(Level.INFO, "Load information about spaceship ...");

        try (Scanner scanner = new Scanner(new File("src/main/resources/" + pathName))) {
            int nameSpaceship = 0;
            int distanceSpaceship = 1;
            int crewSpaceship = 2;

            while (scanner.hasNext()) {
                String spaceship = scanner.nextLine();
                if (spaceship.startsWith("#")) {
                    continue;
                }
                String[] spaceshipAttributes = spaceship.split(";");

                FindSpaceshipService.getInstance().createSpaceship(SpaceshipFactory.getInstance().
                        create(spaceshipAttributes[nameSpaceship],
                        Long.valueOf(spaceshipAttributes[distanceSpaceship]),
                        crewMap(spaceshipAttributes[crewSpaceship])));
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.ERROR, "File   \"" + pathName + "\" cant be found!!!!");
            throw new InvalidStateException("Impossible to fetch spaceship from file!");
        }
        logger.log(Level.INFO, "Information about spaceship was loaded!");

    }

    private Map<Role, Short> crewMap(String crewSpaceship) {
        String[] crews = crewSpaceship.substring(1, crewSpaceship.length() - 1).split(",");
        Map<Role, Short> mapCrew = new HashMap<>();
        for (String crew : crews) {
            String[] roleIds = crew.split(":");
            mapCrew.put(Role.resolveRoleById(Integer.parseInt(roleIds[0])), Short.parseShort(roleIds[1]));
        }
        return mapCrew;
    }
}
