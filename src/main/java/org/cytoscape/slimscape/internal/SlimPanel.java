package org.cytoscape.slimscape.internal;

import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.util.swing.OpenBrowser;
import org.osgi.framework.BundleContext;

import java.awt.*;

import javax.swing.*;


public class SlimPanel extends JPanel implements CytoPanelComponent {
    private CyApplicationManager manager;
    private final CySwingAppAdapter adapter;
    private OpenBrowser openBrowser;
    // Panel height
    private static final int DIM_HEIGHT = 800;

    // Panel width
    private static final int DIM_WIDTH = 550;

    private JButton startButton;
    private JLabel start_button;

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
    }

    public SlimPanel(CyApplicationManager manager, CySwingAppAdapter adapter, OpenBrowser openBrowser) {
        super();
        this.adapter = adapter;
        this.manager = manager;
        this.openBrowser = openBrowser;

        makeJComponents();

        setPreferredSize(new Dimension(DIM_WIDTH, DIM_HEIGHT));
        setOpaque(false);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "SLiMScape Settings",
                0, 0, new Font("slimscape settings", Font.BOLD, 16), Color.black));

        this.add(start_button);
        this.add(startButton);

    }

    /**
     * Method that makes the JComponents for the panel
     */
    public void makeJComponents() {
        startButton = new JButton("GO");
        start_button = new JLabel("Press to return UNIPROT IDs:");
        startButton.addActionListener(new StartButtonActionListener(this, manager, openBrowser));

    }

    public Component getComponent() {
        return this;
    }

    public CytoPanelName getCytoPanelName() {
        return CytoPanelName.WEST;
    }

    public String getTitle() {
        return "SLiMSearch Settings Panel";
    }

    public Icon getIcon() {
        return null;
    }
}
