package com.otiliouine.marovea.ui;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.ui.internal.workbench.E4Workbench;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.otiliouine.marovea.ui.service.IMaroveaCommandService;
import com.otiliouine.marovea.ui.service.MaroveaCommandService;

public class Activator implements BundleActivator {

    private static BundleContext context;

    static BundleContext getContext() {
	return context;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
     * )
     */
    public void start(BundleContext bundleContext) throws Exception {
	Activator.context = bundleContext;
//	context.registerService(IMaroveaCommandService.class.getName(),
//		ContextInjectionFactory.make(MaroveaCommandService.class,
//			E4Workbench.getServiceContext()), null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext bundleContext) throws Exception {
	Activator.context = null;
    }

}
