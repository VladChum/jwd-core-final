package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.impl.FindCrewMemberService;
import com.epam.jwd.core_final.strategy.FetchStrategy;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CrewMemberFetchStrategy implements FetchStrategy {
    private static final Logger logger = Logger.getLogger(CrewMemberFetchStrategy.class);

    private static CrewMemberFetchStrategy instance;

    public static CrewMemberFetchStrategy getInstance() {
        if (instance == null) {
            instance = new CrewMemberFetchStrategy();
        }
        return instance;
    }

    @Override
    public void fetchFromFile(String pathName) throws InvalidStateException {
        logger.log(Level.INFO, "Load information about crew member ...");

        try (Scanner scanner = new Scanner(new File(pathName))) {
            scanner.nextLine();
            scanner.useDelimiter(";");
            int nameCrewMember = 1;
            int roleCrewMember = 0;
            int rankCrewMember = 2;

            while (scanner.hasNext()) {
                String crewMember = scanner.next();
                String[] crewMemberAttributes = crewMember.split(",");
                if (FindCrewMemberService.getInstance()
                        .checkDuplicateCrewMember(crewMemberAttributes[nameCrewMember])) {
                    FindCrewMemberService.getInstance().createCrewMember(CrewMemberFactory.getInstance()
                            .create(crewMemberAttributes[nameCrewMember],
                                    Role.resolveRoleById(Integer.parseInt(crewMemberAttributes[roleCrewMember])),
                                    Rank.resolveRankById(Integer.parseInt(crewMemberAttributes[rankCrewMember]))));
                }
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.ERROR, "File   \"" + pathName + "\" cant be found!!!!");
            throw new InvalidStateException("Impossible to fetch crew member from file!");
        }
        logger.log(Level.INFO, "Information about crew member was loaded");
    }
}
