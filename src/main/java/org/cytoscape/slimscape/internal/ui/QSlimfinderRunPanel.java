package org.cytoscape.slimscape.internal.ui;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.*;
import org.cytoscape.slimscape.internal.AlterGraph;
import org.cytoscape.slimscape.internal.CommonMethods;
import org.cytoscape.slimscape.internal.RunQSlimfinder;
import org.cytoscape.util.swing.OpenBrowser;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.vizmap.VisualMappingManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QSlimfinderRunPanel extends JPanel{
    JTextArea idTextArea = null;
    JTextArea queryTextArea = null;
    JTextArea uniprotTextArea = null;
    CyApplicationManager manager;
    List<String> input;
    CyNetworkFactory networkFactory;
    CyNetworkManager networkManager;
    CyNetworkViewFactory networkViewFactory;
    CyNetworkViewManager networkViewManager;
    VisualMappingManager visualMappingManager;
    CyEventHelper eventHelper;
    OpenBrowser openBrowser;
    JTabbedPane slimfinder;

    public QSlimfinderRunPanel(final CyApplicationManager manager, final OpenBrowser openBrowser,
                              final QSlimfinderOptionsPanel optionsPanel, final CyEventHelper eventHelper,
                              final CyNetworkFactory networkFactory, final CyNetworkManager networkManager,
                              final CyNetworkViewFactory networkViewFactory, final CyNetworkViewManager networkViewManager,
                              final VisualMappingManager visualMappingManager, JTabbedPane slimfinder) {
        this.networkFactory = networkFactory;
        this.manager = manager;
        this.networkManager = networkManager;
        this.networkViewFactory = networkViewFactory;
        this.networkViewManager = networkViewManager;
        this.visualMappingManager = visualMappingManager;
        this.eventHelper = eventHelper;
        this.openBrowser = openBrowser;
        this.slimfinder = slimfinder;

        setBackground(new Color(238, 238, 238));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{629, 0};
        gridBagLayout.rowHeights = new int[]{170, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);
        setPreferredSize(new Dimension(476, 167));

        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 1;

        JPanel sLiMFinderPanel = new JPanel();
        sLiMFinderPanel.setBorder(BorderFactory.createCompoundBorder(
                new TitledBorder("Run QSLiMFinder"),
                new EmptyBorder(0, 0, 0, 20)));
        GridBagConstraints gbc_sLiMFinderPanel = new GridBagConstraints();
        gbc_sLiMFinderPanel.fill = GridBagConstraints.BOTH;
        gbc_sLiMFinderPanel.gridx = 0;
        gbc_sLiMFinderPanel.gridy = 0;
        add(sLiMFinderPanel, gbc_sLiMFinderPanel);
        GridBagLayout gbl_sLiMFinderPanel = new GridBagLayout();
        gbl_sLiMFinderPanel.columnWidths = new int[]{497, 0};
        gbl_sLiMFinderPanel.rowHeights = new int[]{25, 87, 0};
        gbl_sLiMFinderPanel.columnWeights = new double[]{1.0,
                Double.MIN_VALUE};
        gbl_sLiMFinderPanel.rowWeights = new double[]{0.0, 0.0,
                Double.MIN_VALUE};
        sLiMFinderPanel.setLayout(gbl_sLiMFinderPanel);

        JPanel slimSearchOptionsPanel = new JPanel();
        slimSearchOptionsPanel.setBorder(new TitledBorder(new LineBorder(
                new Color(184, 207, 229)), "New Inputs", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(51, 51, 51)));
        GridBagConstraints gbc_slimSearchOptionsPanel = new GridBagConstraints();
        gbc_slimSearchOptionsPanel.insets = new Insets(0, 0, 5, 0);
        gbc_slimSearchOptionsPanel.fill = GridBagConstraints.BOTH;
        gbc_slimSearchOptionsPanel.gridx = 0;
        gbc_slimSearchOptionsPanel.gridy = 0;
        sLiMFinderPanel.add(slimSearchOptionsPanel, gbc_slimSearchOptionsPanel);

        GridBagLayout gbl_slimSearchOptionsPanel = new GridBagLayout();
        gbl_slimSearchOptionsPanel.columnWidths = new int[]{0, 0};
        gbl_slimSearchOptionsPanel.rowHeights = new int[]{0, 0, 0, 0};
        gbl_slimSearchOptionsPanel.columnWeights = new double[]{1.0,
                Double.MIN_VALUE};
        gbl_slimSearchOptionsPanel.rowWeights = new double[]{1.0, 0.0, 1.0,
                Double.MIN_VALUE};
        slimSearchOptionsPanel.setLayout(gbl_slimSearchOptionsPanel);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 5, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        slimSearchOptionsPanel.add(panel, gbc_panel);

        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        JLabel motifLabel = new JLabel("Query Sequence:");
        GridBagConstraints gbc_motifLabel = new GridBagConstraints();
        gbc_motifLabel.anchor = GridBagConstraints.WEST;
        gbc_motifLabel.insets = new Insets(0, 0, 5, 5);
        gbc_motifLabel.gridx = 0;
        gbc_motifLabel.gridy = 1;
        slimSearchOptionsPanel.add(motifLabel, gbc_motifLabel);

        JButton runSLiMFinderButton = new JButton("Run QSLiMFinder");
        GridBagConstraints gbc_runSLiMFinderButton = new GridBagConstraints();
        gbc_runSLiMFinderButton.anchor = GridBagConstraints.EAST;
        gbc_runSLiMFinderButton.insets = new Insets(0, 0, 5, 0);
        gbc_runSLiMFinderButton.gridx = 0;
        gbc_runSLiMFinderButton.gridy = 1;
        slimSearchOptionsPanel.add(runSLiMFinderButton, gbc_runSLiMFinderButton);


        queryTextArea = new JTextArea();
        GridBagConstraints gbc_textArea = new GridBagConstraints();
        gbc_textArea.insets = new Insets(0, 0, 0, 5);
        gbc_textArea.fill = GridBagConstraints.BOTH;
        gbc_textArea.gridx = 0;
        gbc_textArea.gridy = 2;
        slimSearchOptionsPanel.add(queryTextArea, gbc_textArea);


        JLabel uniprotLabel = new JLabel("Uniprot IDs:");
        GridBagConstraints gbc_uniprotLabel = new GridBagConstraints();
        gbc_uniprotLabel.anchor = GridBagConstraints.WEST;
        gbc_uniprotLabel.insets = new Insets(0, 0, 5, 5);
        gbc_uniprotLabel.gridx = 0;
        gbc_uniprotLabel.gridy = 3;
        slimSearchOptionsPanel.add(uniprotLabel, gbc_uniprotLabel);

        uniprotTextArea = new JTextArea(4, 15);
        uniprotTextArea.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(uniprotTextArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setMinimumSize(new Dimension(15, 60));
        GridBagConstraints gbc_uniprotTextArea = new GridBagConstraints();
        gbc_uniprotTextArea.insets = new Insets(0, 0, 0, 5);
        gbc_uniprotTextArea.fill = GridBagConstraints.BOTH;
        gbc_uniprotTextArea.gridx = 0;
        gbc_uniprotTextArea.gridy = 6;
        slimSearchOptionsPanel.add(scroll, gbc_uniprotTextArea);

        // Add the old run panel
        JPanel oldRunPanel = new JPanel();
        oldRunPanel.setBorder(new TitledBorder(new LineBorder(
                new Color(184, 207, 229)), "Past Run", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(51, 51, 51)));
        GridBagConstraints gbc_oldRunPanel = new GridBagConstraints();
        gbc_oldRunPanel.insets = new Insets(0, 0, 5, 0);
        gbc_oldRunPanel.fill = GridBagConstraints.BOTH;
        gbc_oldRunPanel.gridx = 0;
        gbc_oldRunPanel.gridy = 1;
        sLiMFinderPanel.add(oldRunPanel, gbc_oldRunPanel);

        GridBagLayout gbl_oldRunPanel = new GridBagLayout();
        gbl_oldRunPanel.columnWidths = new int[]{0, 0};
        gbl_oldRunPanel.rowHeights = new int[]{0, 0, 0, 0};
        gbl_oldRunPanel.columnWeights = new double[]{1.0,
                Double.MIN_VALUE};
        gbl_oldRunPanel.rowWeights = new double[]{1.0, 0.0, 1.0,
                Double.MIN_VALUE};
        oldRunPanel.setLayout(gbl_oldRunPanel);

        JLabel idLabel = new JLabel("Run ID:");
        GridBagConstraints gbc1_motifLabel = new GridBagConstraints();
        gbc1_motifLabel.anchor = GridBagConstraints.WEST;
        gbc1_motifLabel.insets = new Insets(0, 0, 5, 5);
        gbc1_motifLabel.gridx = 0;
        gbc1_motifLabel.gridy = 0;
        oldRunPanel.add(idLabel, gbc1_motifLabel);

        JButton idCheckButton = new JButton("Retrieve");
        GridBagConstraints gbc_checkLabel = new GridBagConstraints();
        gbc_checkLabel.anchor = GridBagConstraints.EAST;
        gbc_checkLabel.insets = new Insets(0, 0, 0, 5);
        gbc_checkLabel.gridx = 0;
        gbc_checkLabel.gridy = 0;
        oldRunPanel.add(idCheckButton, gbc_checkLabel);

        idTextArea = new JTextArea();
        GridBagConstraints gbc1_textArea = new GridBagConstraints();
        gbc1_textArea.insets = new Insets(0, 0, 0, 5);
        gbc1_textArea.fill = GridBagConstraints.BOTH;
        gbc1_textArea.gridx = 0;
        gbc1_textArea.gridy = 1;
        oldRunPanel.add(idTextArea, gbc1_textArea);


        idCheckButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                if (idTextArea.getText().length() > 6) {
                    String id = idTextArea.getText();
                    int ready = CommonMethods.checkReady(id, openBrowser);
                    if (ready == 1) { // ready
                        resultProcessing(id);
                    } else if (ready != -2) {
                        JOptionPane.showMessageDialog(null, "This ID is still being processed. Please check back later.");
                    }
                }
            }
        });

        runSLiMFinderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CyNetwork network = manager.getCurrentNetwork();
                // There are a set of IDs in the IDs box
                if (uniprotTextArea.getText().length() > 5) {
                    String input = uniprotTextArea.getText();
                    // Strings have to be comma+space delineated ONLY
                    List<String> ids = Arrays.asList(input.split(",\\s+|\\s+"));
                    String query = queryTextArea.getText();
                    RunQSlimfinder qslimfinder = new RunQSlimfinder(network, null, ids, query, optionsPanel);
                    String url = qslimfinder.getUrl();
                    String id = CommonMethods.getJobID(url).replaceAll("\\s+", "");
                    idTextArea.setText(id);
                    // Make sure the job is ready before analysis starts
                    int ready = CommonMethods.checkReady(id, openBrowser);
                    if (ready == 1) {
                        resultProcessing(id);
                    }
                    // Get node IDs from the graph
                } else {
                    List<CyNode> selected = new ArrayList<CyNode>();
                    selected.addAll(CyTableUtil.getNodesInState(network, "selected", true));
                    if (selected.size() > 1) {
                        String query = queryTextArea.getText();
                        RunQSlimfinder qslimfinder = new RunQSlimfinder(network, selected, null, query, optionsPanel);
                        String url = qslimfinder.getUrl();
                        String id = CommonMethods.getJobID(url).replaceAll("\\s+", "");
                        idTextArea.setText(id);
                        // Make sure the job is ready before analysis starts
                        int ready = CommonMethods.checkReady(id, openBrowser);
                        if (ready == 1) {
                            resultProcessing(id);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No inputs to analyse!");
                    }
                }
            }
        });
    }

    private void resultProcessing(String id) {
        try {
            List<String> csvResults = CommonMethods.PrepareResults(
                    "http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id, "&rest=main", openBrowser, id);
            if (csvResults != null) {
                displayResults(csvResults, id);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Something went wrong! No results were generated. Please check the " +
                    "full server run at the url: rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id + ". ");        }
    }

    /**
     * @desc - creates the results panels and alters/creates the cytoscape network as required.
     * @param csvResults  - processed results from the main page.
     * @param id -  run ID from the server.
     */
    private void displayResults(List<String> csvResults, final String id) {
        // Get OCC Results
        List<String> occResults = CommonMethods.PrepareResults(
                "http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id, "&rest=occ", openBrowser, id);

        // Get list of all node IDs from slimdb
        List<String> nodeIds = CommonMethods.getNodeIds("http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id
                + "&rest=slimdb");

        // Get graph edge data from upc outputs
        List<String> upc = CommonMethods.getUpcResults("http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id
                + "&rest=upc");

        // Create button to take users to the full results
        JButton fullResults = new JButton();
        fullResults.setText("Full results");
        fullResults.setBackground(Color.WHITE);
        fullResults.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openBrowser.openURL("http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id);
            }
        });

        // Create button to take users to the help page on github
        JButton help = new JButton();
        help.setText("Help");
        help.setBackground(Color.WHITE);
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openBrowser.openURL("https://github.com/RayneCatseye/SLiMscape/wiki/QSLiMFinder");
            }
        });

        JTable csv = CommonMethods.createCsvTable(csvResults);
        JTable occ = CommonMethods.createOccTable(occResults);

        List<String> occIds = new ArrayList<String>();
        for (int y=0; y<occ.getRowCount(); y++) {
            Object current = occ.getModel().getValueAt(y, 2);
            String string = String.valueOf(current);
            occIds.add(string);
        }

        // Alter the graph
        new AlterGraph(nodeIds, occIds, upc, manager, eventHelper, networkFactory, networkManager,
                networkViewFactory, networkViewManager, visualMappingManager);

        // Display the results in a panel
        JPanel resultsPane = new ResultsPanel(new JScrollPane(csv), new JScrollPane(occ), fullResults, help, slimfinder, id);
        slimfinder.add(id, resultsPane);
    }

}
