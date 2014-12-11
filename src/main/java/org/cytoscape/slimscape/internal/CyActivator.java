package org.cytoscape.slimscape.internal;

import java.util.Properties;

import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.service.util.AbstractCyActivator;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {
	@Override
	public void start(BundleContext context) throws Exception {
		CyApplicationManager manager = getService(context, CyApplicationManager.class);
		CySwingAppAdapter adapter = getService(context, CySwingAppAdapter.class);
		CySwingApplication desktopApp = adapter.getCySwingApplication();
		Properties properties = new Properties();

		SlimPanel slimPanel = new SlimPanel(manager, adapter, context);
		registerService(context, slimPanel, CytoPanelComponent.class, properties);

		SlimscapePluginAction slimscapePluginAction = new SlimscapePluginAction(manager, adapter, desktopApp, slimPanel);
		registerService(context, slimscapePluginAction, CyAction.class, properties);
	}
}
