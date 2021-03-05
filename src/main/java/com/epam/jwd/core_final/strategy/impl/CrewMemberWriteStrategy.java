package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.FindCrewMemberService;
import com.epam.jwd.core_final.service.impl.FindSpaceshipService;
import com.epam.jwd.core_final.strategy.WriteStrategy;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class CrewMemberWriteStrategy implements WriteStrategy<CrewMember> {
    private static final Logger logger = Logger.getLogger(CrewMemberWriteStrategy.class);

    private static CrewMemberWriteStrategy instance;

    public static CrewMemberWriteStrategy getInstance() {
        if (instance == null) {
            instance = new CrewMemberWriteStrategy();
        }
        return instance;
    }

    private File crewMemberFile = new File("workDirectory/CrewMemberFile.txt");
    private PrintWriter writeCrewMember;

    @Override
    public void openFile() {
        try {
            if (!crewMemberFile.exists()) {
                crewMemberFile.createNewFile();
            }
            writeCrewMember = new PrintWriter(crewMemberFile);
            writeCrewMember.println("#");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeToFile(CrewMember crewMember) throws InvalidStateException {
//        logger.log(Level.INFO, "write information about crew member ...");

        writeCrewMember.write(crewMember.getRole().getId() + ","
                + crewMember.getName() + ","
                + crewMember.getRank().getId() + ";");
        writeCrewMember.flush();
    }

    @Override
    public void closeFile() {
        writeCrewMember.close();
    }

    public void writeCash() {
        for (int i = 0; i < FindCrewMemberService.getInstance().findAllCrewMembers().size(); i++) {
            try {
                writeToFile(FindCrewMemberService.getInstance().findAllCrewMembers().get(i));
            } catch (InvalidStateException e) {
                e.printStackTrace();
            }
        }
    }
}
