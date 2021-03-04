package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.context.impl.NassaMenu;
import com.epam.jwd.core_final.exception.InvalidStateException;
import org.apache.log4j.Logger;

import java.util.function.Supplier;

public interface Application {
    static ApplicationMenu start() throws InvalidStateException {
        final NassaMenu applicationMenu = NassaMenu.getInstance();
        final Supplier<ApplicationContext> applicationContextSupplier                   //!!!!!!!!!!!
                = applicationMenu::getApplicationContext;                               // todo
        final NassaContext nassaContext = new NassaContext();

        nassaContext.init();
        return applicationContextSupplier::get;
    }
}
