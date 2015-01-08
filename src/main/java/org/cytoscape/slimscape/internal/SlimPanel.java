package org.cytoscape.slimscape.internal;

import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.slimscape.internal.ui.*;
import org.cytoscape.util.swing.OpenBrowser;

import javax.swing.*;
import java.awt.*;


public class SlimPanel extends JPanel implements CytoPanelComponent { // Dont forget, you can add ,ActionListener
    private CyApplicationManager manager;
    private final CySwingAppAdapter adapter;
    private OpenBrowser openBrowser;

    private JTextArea motifTextArea;

    public SlimPanel(CyApplicationManager manager, CySwingAppAdapter adapter, OpenBrowser openBrowser) {
        super();
        this.adapter = adapter;
        this.manager = manager;
        this.openBrowser = openBrowser;

        CyNetwork network = manager.getCurrentNetwork();
        this.setBounds(100, 100, 725, 471);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
        gbc_tabbedPane.fill = GridBagConstraints.BOTH;
        gbc_tabbedPane.gridx = 0;
        gbc_tabbedPane.gridy = 0;
        add(tabbedPane, gbc_tabbedPane);

        JTabbedPane slimsearch = new JTabbedPane();
        SlimsearchOptionsPanel optionsPanel = new SlimsearchOptionsPanel();
        slimsearch.addTab("Run SLiMSearch", new SlimsearchRunPanel(manager, openBrowser, optionsPanel));
        slimsearch.addTab("Options", optionsPanel);

        JTabbedPane slimfinder = new JTabbedPane();
        SlimfinderOptionsPanel optionsPanel1 = new SlimfinderOptionsPanel();
        slimfinder.addTab("Run SLiMFinder", new SlimfinderRunPanel(manager, optionsPanel1));
        slimfinder.addTab("Options", optionsPanel1);

        // Add the slimsearch tab to tabbedPane
        tabbedPane.addTab("SLiMSearch", slimsearch);
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
