package org.cytoscape.slimscape.internal;

import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.slimscape.internal.ui.SlimfinderOptionsPanel;
import org.cytoscape.slimscape.internal.ui.SlimfinderRunPanel;
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
        this.setBounds(100, 100, 725, 471);

<<<<<<< HEAD
        setPreferredSize(new Dimension(700,400));
        setOpaque(false);

        JTabbedPane pane = new JTabbedPane();
        pane.setPreferredSize(new Dimension(400, 700));
=======
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
        gbc_tabbedPane.fill = GridBagConstraints.BOTH;
        gbc_tabbedPane.gridx = 0;
        gbc_tabbedPane.gridy = 0;
        add(tabbedPane, gbc_tabbedPane);
>>>>>>> modularise

        JTabbedPane slimsearch = new JTabbedPane();
        slimsearch.addTab("Run SLiMSearch", new SlimsearchRunPanel());
        slimsearch.addTab("Options", new SlimsearchOptionsPanel());

<<<<<<< HEAD
        // Add the slimsearch tab to the overarching tabbed pane
        pane.addTab("SLiMSearch", slimsearch);
        pane.addTab("SLiMFinder", new JPanel());
        pane.addTab("Domain", new JPanel());
=======
        JTabbedPane slimfinder = new JTabbedPane();
        slimfinder.addTab("Run SLiMFinder", new SlimfinderRunPanel());
        slimfinder.addTab("Options", new SlimfinderOptionsPanel());

        // Add the slimsearch tab to tabbedPane
        tabbedPane.addTab("SLiMSearch", slimsearch);
        tabbedPane.addTab("SLiMFinder", slimfinder);
        tabbedPane.addTab("Domain", new JPanel());
>>>>>>> modularise

        this.add(tabbedPane);
    }

<<<<<<< HEAD
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
        slimSearchOptionsPanel.setPreferredSize(new Dimension(350, 100));
        slimSearchOptionsPanel.setMaximumSize(new Dimension(350, 100));
		runSLiMFinderPanel.add(slimSearchOptionsPanel);

        JPanel panel = new JPanel();
		slimSearchOptionsPanel.add(panel);

        JLabel motifLabel = new JLabel("Motifs:");
		slimSearchOptionsPanel.add(motifLabel);

        motifTextArea = new JTextArea(1, 20);
		slimSearchOptionsPanel.add(motifTextArea);

        return runSLiMFinderPanel;
    }

    /*
     * Subpanel of RootSLiMSearchPanel
     * Creates the SLiMSearch Options Panel
     */
    private JPanel SLiMSearchOptionsPanel () {
        JPanel options = new JPanel();
        options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));

        JPanel maskingPanel = new JPanel();
        maskingPanel.setBorder(new TitledBorder(null, "Masking", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        maskingPanel.setPreferredSize(new Dimension(400, 100));
        maskingPanel.setMaximumSize(new Dimension(400, 100));
        maskingPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        disorderMakingCheckBox = new JCheckBox("Disorder Masking");
        disorderMakingCheckBox.setSelected(true);
        disorderMakingCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        maskingPanel.add(disorderMakingCheckBox);

        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(350, 2));
        spacer.setMaximumSize(new Dimension(350, 2));
        maskingPanel.add(spacer);

        conservationCheckBox = new JCheckBox("Conservation Masking");
        conservationCheckBox.setSelected(true);
        conservationCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        maskingPanel.add(conservationCheckBox);

        options.add(maskingPanel);

        JPanel SLiMChance = new JPanel();
        SLiMChance.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "SLiM Chance", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        SLiMChance.setAlignmentX(Component.LEFT_ALIGNMENT);;
        SLiMChance.setPreferredSize(new Dimension(400, 100));
        SLiMChance.setMaximumSize(new Dimension(400, 100));


        JLabel labelProbabilityCutOff = new JLabel("Probability cut-off:");
        labelProbabilityCutOff.setHorizontalAlignment(SwingConstants.LEFT);
        SLiMChance.add(labelProbabilityCutOff);

        probabililtyCutoffTextField = new JTextField();
        probabililtyCutoffTextField.setHorizontalAlignment(SwingConstants.LEFT);
        probabililtyCutoffTextField.setText("1");
        SLiMChance.add(probabililtyCutoffTextField);
        probabililtyCutoffTextField.setColumns(10);

        options.add(SLiMChance);

        JPanel customPanel = new JPanel();
        customPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Custom Parameters", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        customPanel.setPreferredSize(new Dimension(400, 100));
        customPanel.setMaximumSize(new Dimension(400, 100));
        customPanel.setAlignmentX(Component.LEFT_ALIGNMENT);


        customParametersTextArea = new JTextArea(4, 34);
        customPanel.add(customParametersTextArea);

        options.add(customPanel);

        return options;
    }


=======
>>>>>>> modularise
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
