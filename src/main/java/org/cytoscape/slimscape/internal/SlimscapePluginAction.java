package org.cytoscape.slimscape.internal;


import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SlimscapePluginAction extends AbstractCyAction {

    private final CySwingAppAdapter adapter;
    private CySwingApplication desktopApp;
    private final CyApplicationManager manager;
    private static final String MENU_NAME = "SLiMScape";
    private static final String WINDOW_TITLE = "SLiMScape Settings";
    private final SlimPanel slimPanel;
    private final CytoPanel cytoPanelWest;

    // This constructor sets text to appear on the menu item
    public SlimscapePluginAction(final CyApplicationManager manager, final CySwingAppAdapter adapter, final CySwingApplication desktopApp, SlimPanel slimPanel) {
        super(MENU_NAME);
        this.adapter = adapter;
        this.manager = manager;
        this.desktopApp = desktopApp;

        setPreferredMenu("Apps");

        this.cytoPanelWest = this.desktopApp.getCytoPanel(CytoPanelName.WEST);
        this.slimPanel = slimPanel;

    }

    /**
     * This method opens the Slimscape settings panel on menu selection
     * @param event
     *          event triggered when bingo menu item is clicked
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        // If panel is hidden, show it
        if (cytoPanelWest.getState() == CytoPanelState.HIDE) {
            cytoPanelWest.setState(CytoPanelState.DOCK);
        }

        int index = cytoPanelWest.indexOfComponent(slimPanel);
        if (index == -1) {
            return;
        }
        cytoPanelWest.setSelectedIndex(index);
    }
}
