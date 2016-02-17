package com.otiliouine.marovea.ui.addon;

import javax.inject.Inject;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.internal.CommandServiceImpl;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;

import com.otiliouine.marovea.ui.service.IMaroveaCommandService;
import com.otiliouine.marovea.ui.service.MaroveaCommandService;

public class MaroveaCommandServiceAddon {
//    @Inject
//    IEventBroker eventBroker;

    @PostConstruct
    void init(IEclipseContext context) {
	IMaroveaCommandService service = ContextInjectionFactory.make(
		MaroveaCommandService.class, context);
	context.set(IMaroveaCommandService.class, service);
    }

    @PreDestroy
    void unhookListeners() {
	// Unhook event listeners
    }
}