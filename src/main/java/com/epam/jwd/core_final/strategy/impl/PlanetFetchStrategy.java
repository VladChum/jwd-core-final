package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.PlanetFactory;
import com.epam.jwd.core_final.service.impl.FindSpacemapService;
import com.epam.jwd.core_final.strategy.FetchStrategy;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PlanetFetchStrategy implements FetchStrategy {
    private static final Logger logger = Logger.getLogger(PlanetFetchStrategy.class);

    private static PlanetFetchStrategy instance;

    public static PlanetFetchStrategy getInstance() {
        if (instance == null) {
            instance = new PlanetFetchStrategy();
        }
        return instance;
    }
    @Override
    public void fetchFromFile(String pathName) throws InvalidStateException {
        logger.log(Level.INFO, "Load information about Planets ...");
        int yPlanetCoordinate = 0;

        try (Scanner scanner = new Scanner(new File("src/main/resources/input/" + pathName))) {
            while (scanner.hasNext()) {
                yPlanetCoordinate++;
                String spaceMap = scanner.nextLine();
                String[] pointsSpaceMap = spaceMap.split(",");

                for (int i = 0; i < pointsSpaceMap.length; i++) {
                    if (!pointsSpaceMap[i].equals("null")) {
                        FindSpacemapService.getInstance().createPlanet(PlanetFactory.getInstance()
                                .create(pointsSpaceMap[i], i + 1, yPlanetCoordinate));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.ERROR, "File   \"" + pathName + "\" cant be found!!!!");
            throw new InvalidStateException("Impossible to fetch Planets from file!");
        }
    }
}
