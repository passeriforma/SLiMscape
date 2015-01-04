package org.cytoscape.slimscape.internal;

import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.util.swing.OpenBrowser;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
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

        JTabbedPane pane = new JTabbedPane();
        pane.setPreferredSize(new Dimension(400, 700));

        JTabbedPane slimsearch = new JTabbedPane();
        slimsearch.addTab("Run SLiMSearch", RunSLiMSearch());

        slimsearch.addTab("Options", new SlimsearchOptionsPanel());


        // Add the slimsearch tab to the overarching tabbed pane
        pane.addTab("SLiMSearch", slimsearch);
        pane.addTab("SLiMFinder", new JPanel());
        pane.addTab("Domain", new JPanel());

        this.add(pane);
    }

    /*
     * Subpanel of slimsearchRunPanel.
     * Creates components for motif input, and running of SLiMSearch
     */
    private JPanel RunSLiMSearch () {
        JPanel runSLiMFinderPanel = new JPanel();
        runSLiMFinderPanel.setLayout(new BoxLayout(runSLiMFinderPanel, BoxLayout.Y_AXIS));
        runSLiMFinderPanel.setMaximumSize(new Dimension(400, 400));
        runSLiMFinderPanel.setPreferredSize(new Dimension(400, 400));

        // Creates label and button required to run SLiMSearch
		runSLiMFinderPanel.setBorder(new TitledBorder(null, "Run SLiMSearch",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
        JButton runSLiMSearchButton = new JButton("RunSLiMSearch");
        runSLiMSearchButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        // TODO: Add action listener
		runSLiMFinderPanel.add(runSLiMSearchButton);

        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(350, 4));
        spacer.setMaximumSize(new Dimension(350, 4));
        runSLiMFinderPanel.add(spacer);

        // Creates panel for input of motifs to search for
        JPanel slimSearchOptionsPanel = new JPanel();
		slimSearchOptionsPanel.setBorder(new TitledBorder(new LineBorder(
				new Color(184, 207, 229)), "Parameters", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(51, 51, 51)));
        slimSearchOptionsPanel.setPreferredSize(new Dimension(400, 100));
        slimSearchOptionsPanel.setMaximumSize(new Dimension(400, 100));
		runSLiMFinderPanel.add(slimSearchOptionsPanel);

        JPanel panel = new JPanel();
		slimSearchOptionsPanel.add(panel);

        JLabel motifLabel = new JLabel("Motifs:");
		slimSearchOptionsPanel.add(motifLabel);

        motifTextArea = new JTextArea(1, 15);
		slimSearchOptionsPanel.add(motifTextArea);

        // TODO: Add adjustment listener for when boxes are ticked on and off

        /* Example of how to add objects to the table
         * table.addRow(new Object[] { Boolean.valueOf(true), String."" etcetc});)
         */

        return runSLiMFinderPanel;
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
