package org.cytoscape.slimscape.internal;

import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.slimscape.internal.ui.SlimfinderOptionsPanel;
import org.cytoscape.slimscape.internal.ui.SlimfinderRunPanel;
import org.cytoscape.slimscape.internal.ui.SlimprobOptionsPanel;
import org.cytoscape.slimscape.internal.ui.SlimprobRunPanel;
import org.cytoscape.util.swing.OpenBrowser;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;

import javax.swing.*;
import java.awt.*;


public class SlimPanel extends JPanel implements CytoPanelComponent { // Dont forget, you can add ,ActionListener
    private CyApplicationManager manager;
    private final CySwingAppAdapter adapter;
    private OpenBrowser openBrowser;
    private CyEventHelper eventHelper;
    private JTextArea motifTextArea;
    CyNetworkFactory networkFactory;
    CyNetworkViewFactory networkViewFactory;
    CyNetworkViewManager networkViewManager;

    public SlimPanel(CyApplicationManager manager, CySwingAppAdapter adapter, OpenBrowser openBrowser,
                     CyEventHelper eventHelper, CyNetworkFactory networkFactory, CyNetworkManager networkManager,
                     CyNetworkViewFactory networkViewFactory, CyNetworkViewManager networkViewManager) {
        super();
        this.adapter = adapter;
        this.manager = manager;
        this.openBrowser = openBrowser;
        this.eventHelper = eventHelper;
        this.networkFactory = networkFactory;
        this.networkViewFactory = networkViewFactory;
        this.networkViewManager = networkViewManager;

        CyNetwork network = manager.getCurrentNetwork();
        this.setBounds(100, 100, 725, 471);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
        gbc_tabbedPane.fill = GridBagConstraints.BOTH;
        gbc_tabbedPane.gridx = 0;
        gbc_tabbedPane.gridy = 0;
        add(tabbedPane, gbc_tabbedPane);

        JTabbedPane slimprob = new JTabbedPane();
        SlimprobOptionsPanel optionsPanel = new SlimprobOptionsPanel();
        slimprob.addTab("Run SLiMProb", new SlimprobRunPanel(manager, openBrowser, optionsPanel));
        slimprob.addTab("Options", optionsPanel);

        JTabbedPane slimfinder = new JTabbedPane();
        SlimfinderOptionsPanel optionsPanel1 = new SlimfinderOptionsPanel();
        slimfinder.addTab("Run SLiMFinder", new SlimfinderRunPanel(manager, openBrowser, optionsPanel1,
                eventHelper, networkFactory, networkManager, networkViewFactory, networkViewManager));
        slimfinder.addTab("Options", optionsPanel1);

        // Add the slimprob tab to tabbedPane
        tabbedPane.addTab("SLiMProb", slimprob);
        tabbedPane.addTab("SLiMFinder", slimfinder);
        tabbedPane.addTab("Domain", new JPanel());


        this.add(tabbedPane);
    }

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
