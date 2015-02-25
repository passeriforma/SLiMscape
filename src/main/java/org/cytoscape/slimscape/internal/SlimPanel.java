package org.cytoscape.slimscape.internal;

import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.slimscape.internal.ui.SlimfinderOptionsPanel;
import org.cytoscape.slimscape.internal.ui.SlimfinderRunPanel;
import org.cytoscape.slimscape.internal.ui.SlimprobOptionsPanel;
import org.cytoscape.slimscape.internal.ui.SlimprobRunPanel;
import org.cytoscape.util.swing.OpenBrowser;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.vizmap.VisualMappingManager;

import javax.swing.*;
import java.awt.*;


public class SlimPanel extends JPanel implements CytoPanelComponent {
    CyNetworkFactory networkFactory;
    CyNetworkViewFactory networkViewFactory;
    CyNetworkViewManager networkViewManager;

    public SlimPanel(CyApplicationManager manager, CySwingAppAdapter adapter, OpenBrowser openBrowser,
                     CyEventHelper eventHelper, CyNetworkFactory networkFactory, CyNetworkManager networkManager,
                     CyNetworkViewFactory networkViewFactory, CyNetworkViewManager networkViewManager, VisualMappingManager visualMappingManager) {
        super();
        this.networkFactory = networkFactory;
        this.networkViewFactory = networkViewFactory;
        this.networkViewManager = networkViewManager;
        this.setBounds(100, 100, 725, 471);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
        gbc_tabbedPane.fill = GridBagConstraints.BOTH;
        gbc_tabbedPane.gridx = 0;
        gbc_tabbedPane.gridy = 0;
        add(tabbedPane, gbc_tabbedPane);

        JTabbedPane slimfinder = new JTabbedPane();
        SlimfinderOptionsPanel slimfinderOptionsPanel = new SlimfinderOptionsPanel();
        slimfinder.addTab("Run SLiMFinder", new SlimfinderRunPanel(manager, openBrowser, slimfinderOptionsPanel,
                eventHelper, networkFactory, networkManager, networkViewFactory, networkViewManager, visualMappingManager));
        slimfinder.addTab("Options", slimfinderOptionsPanel);

        JTabbedPane slimprob = new JTabbedPane();
        SlimprobOptionsPanel slimprobOptionsPanel = new SlimprobOptionsPanel();
        slimprob.addTab("Run SLiMProb", new SlimprobRunPanel(manager, openBrowser, slimprobOptionsPanel,
                eventHelper, networkFactory, networkManager, networkViewFactory, networkViewManager, visualMappingManager));
        slimprob.addTab("Options", slimprobOptionsPanel);

        // Add the slimprob and slimfinder tabs to tabbedPane
        tabbedPane.addTab("SLiMProb", slimprob);
        tabbedPane.addTab("SLiMFinder", slimfinder);
        //tabbedPane.addTab("Domain", new JPanel());

        this.add(tabbedPane);
    }

    // Obligatory components for CytoPanelComponent
    public Component getComponent() {
        return this;
    }

    public CytoPanelName getCytoPanelName() {
        return CytoPanelName.WEST;
    }

    public String getTitle() {
        return "SLiMScape";
    }

    public Icon getIcon() {
        return null;
    }

}
