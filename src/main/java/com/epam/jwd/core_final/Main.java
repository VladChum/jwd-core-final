package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.context.impl.NassaMenu;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.util.PropertyReaderUtil;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);


    public static void main(String[] args)  {
        logger.log(Level.INFO, "Start application work ...");

        try {
            Application.start();
            NassaMenu.getInstance().getApplicationContext();

        } catch (InvalidStateException e) {
            logger.log(Level.ERROR, "Application failed!!!");
        }
    }
}



