package com.epam.jwd.core_final.domain;

/**
 * This class should be IMMUTABLE!
 * <p>
 * Expected fields:
 * <p>
 * inputRootDir {@link String} - base dir for all input files
 * outputRootDir {@link String} - base dir for all output files
 * crewFileName {@link String}
 * missionsFileName {@link String}
 * spaceshipsFileName {@link String}
 * <p>
 * fileRefreshRate {@link Integer}
 * dateTimeFormat {@link String} - date/time format for {@link java.time.format.DateTimeFormatter} pattern
 */
public final class ApplicationProperties {
    //todo
    //make final class     for IMMUTABLE
    private final String inputRootDir;
    private final String outputRootDir;
    private final String crewFileName;
    private final String missionsFileName;
    private final String planetFileName;
    private final String spaceshipsFileName;
    private final Integer fileRefreshRate;
    private final String dateTimeFormat;

    public ApplicationProperties(String inputRootDir,
                                 String outputRootDir,
                                 String crewFileName,
                                 String missionsFileName,
                                 String planetFileName,
                                 String spaceshipsFileName,
                                 Integer fileRefreshRate,
                                 String dateTimeFormat) {
        this.inputRootDir = inputRootDir;
        this.outputRootDir = outputRootDir;
        this.crewFileName = crewFileName;
        this.missionsFileName = missionsFileName;
        this.planetFileName = planetFileName;
        this.spaceshipsFileName = spaceshipsFileName;
        this.fileRefreshRate = fileRefreshRate;
        this.dateTimeFormat = dateTimeFormat;
    }

    public String getCrewFileName() {
        return crewFileName;
    }

    public String getSpaceshipsFileName() {
        return spaceshipsFileName;
    }

    public Integer getFileRefreshRate() {
        return fileRefreshRate;
    }

    public String getPlanetFileName() { return planetFileName; }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }
}
