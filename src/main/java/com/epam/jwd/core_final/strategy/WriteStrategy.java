package com.epam.jwd.core_final.strategy;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.InvalidStateException;

public interface WriteStrategy {
    void writeToFile(CrewMember crewMember) throws InvalidStateException;
    void openFile();
    void closeFile();
}
