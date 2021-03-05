package com.epam.jwd.core_final.strategy;

import com.epam.jwd.core_final.exception.InvalidStateException;

public interface WriteStrategy<T> {
    void writeToFile(T crewMember) throws InvalidStateException;
    void openFile();
    void closeFile();
}
