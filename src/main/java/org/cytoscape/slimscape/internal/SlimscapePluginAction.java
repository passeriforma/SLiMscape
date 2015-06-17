package org.cytoscape.slimscape.internal;


import org.cytoscape.app.CyAppAdapter;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.swing.CytoPanel;
import org.cytoscape.application.swing.CytoPanelName;

import java.awt.event.ActionEvent;

public class SlimscapePluginAction extends AbstractCyAction {

    private final CyAppAdapter adapter;
    private CySwingApplication desktopApp;
    private final CyApplicationManager manager;
    private static final String MENU_NAME = "SLiMScape";
    private final SlimPanel slimPanel;
    private final CytoPanel cytoPanelWest;

    public SlimscapePluginAction(final CyApplicationManager manager, final CyAppAdapter adapter,
                                 final CySwingApplication desktopApp, SlimPanel slimPanel) {
        super(MENU_NAME);
        this.adapter = adapter;
        this.manager = manager;
        this.desktopApp = desktopApp;

        setPreferredMenu("Apps");

        this.cytoPanelWest = this.desktopApp.getCytoPanel(CytoPanelName.WEST);
        this.slimPanel = slimPanel;

    }

    /**
     * @desc - This method opens the Slimscape settings panel on menu selection.
     * @param event - event triggered when menu item is clicked.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        // Set slimpanel to visible
        slimPanel.setVisible(true);
    }
}
