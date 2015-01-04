package org.cytoscape.slimscape.internal;

import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.slimscape.internal.ui.SlimsearchOptionsPanel;
import org.cytoscape.slimscape.internal.ui.SlimsearchRunPanel;
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

        setPreferredSize(new Dimension(400,700));
        setOpaque(false);

        // Creates all SLiMSearch panel components
        //JPanel resultCards = ResultCards();

        //JPanel slimsearchRunPanel = SlimsearchRunPanel(runSLiMSearch, resultCards);
        //JPanel optionsPanel = SLiMSearchOptionsPanel();

        JTabbedPane slimscapePane = new JTabbedPane();
        slimscapePane.setPreferredSize(new Dimension(400, 700));

        JTabbedPane slimsearch = new JTabbedPane();
        slimsearch.addTab("Run SLiMSearch", new SlimsearchRunPanel());
        slimsearch.addTab("Options", new SlimsearchOptionsPanel());


        // Add the slimsearch tab to the overarching tabbed pane
        slimscapePane.addTab("SLiMSearch", slimsearch);
        slimscapePane.addTab("SLiMFinder", new JPanel());
        slimscapePane.addTab("Domain", new JPanel());

        this.add(slimscapePane);
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
