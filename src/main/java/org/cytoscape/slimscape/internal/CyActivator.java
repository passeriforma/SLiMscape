package org.cytoscape.slimscape.internal;

import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.util.swing.OpenBrowser;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.osgi.framework.BundleContext;

import java.util.Properties;


public class CyActivator extends AbstractCyActivator {
	@Override
	public void start(BundleContext context) throws Exception {
		CyApplicationManager manager = getService(context, CyApplicationManager.class);
		CySwingAppAdapter adapter = getService(context, CySwingAppAdapter.class);
		CySwingApplication desktopApp = adapter.getCySwingApplication();
        OpenBrowser openBrowser = getService(context,OpenBrowser.class);
        CyEventHelper eventHelper = getService(context, CyEventHelper.class);
        CyNetworkFactory networkFactory = getService(context, CyNetworkFactory.class);
        CyNetworkManager networkManager = getService(context, CyNetworkManager.class);
        CyNetworkViewFactory networkViewFactory = getService(context, CyNetworkViewFactory.class);
        CyNetworkViewManager networkViewManager = getService(context, CyNetworkViewManager.class);
        VisualMappingManager visualMappingManager = getService(context,VisualMappingManager.class);

		Properties properties = new Properties();

		SlimPanel slimPanel = new SlimPanel(manager, adapter, openBrowser, eventHelper, networkFactory, networkManager,
                networkViewFactory, networkViewManager, visualMappingManager);
		registerService(context, slimPanel, CytoPanelComponent.class, properties);

		SlimscapePluginAction slimscapePluginAction = new SlimscapePluginAction(manager, adapter, desktopApp, slimPanel);
		registerService(context, slimscapePluginAction, CyAction.class, properties);
	}
}
