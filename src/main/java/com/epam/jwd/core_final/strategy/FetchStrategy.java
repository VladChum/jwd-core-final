package com.epam.jwd.core_final.strategy;

import com.epam.jwd.core_final.exception.InvalidStateException;

public interface FetchStrategy {
    void fetchFromFile(String pathName) throws InvalidStateException;
}
