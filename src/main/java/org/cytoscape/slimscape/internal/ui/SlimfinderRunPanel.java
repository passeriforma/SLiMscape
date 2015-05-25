package org.cytoscape.slimscape.internal.ui;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.*;
import org.cytoscape.slimscape.internal.AlterGraph;
import org.cytoscape.slimscape.internal.CommonMethods;
import org.cytoscape.slimscape.internal.RunSlimfinder;
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

/*
 * @author: Kevin O'Brien
 * @author: Emily SW
 */
public class SlimfinderRunPanel extends JPanel {

    JTextArea idTextArea = null;
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
    JButton runSLiMFinderButton;

    public SlimfinderRunPanel(final CyApplicationManager manager, final OpenBrowser openBrowser,
                              final SlimfinderOptionsPanel optionsPanel, final CyEventHelper eventHelper,
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
                new TitledBorder("Run SLiMFinder"),
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
        runSLiMFinderButton = new JButton("Run SLiMFinder");
        GridBagConstraints gbc_runSLiMFinderButton = new GridBagConstraints();
        gbc_runSLiMFinderButton.anchor = GridBagConstraints.NORTHWEST;
        gbc_runSLiMFinderButton.insets = new Insets(0, 0, 5, 0);
        gbc_runSLiMFinderButton.gridx = 0;
        gbc_runSLiMFinderButton.gridy = 0;
        sLiMFinderPanel.add(runSLiMFinderButton, gbc_runSLiMFinderButton);

        JPanel slimSearchOptionsPanel = new JPanel();
        slimSearchOptionsPanel.setBorder(new TitledBorder(new LineBorder(
                new Color(184, 207, 229)), "Parameters", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(51, 51, 51)));
        GridBagConstraints gbc_slimSearchOptionsPanel = new GridBagConstraints();
        gbc_slimSearchOptionsPanel.insets = new Insets(0, 0, 5, 0);
        gbc_slimSearchOptionsPanel.fill = GridBagConstraints.BOTH;
        gbc_slimSearchOptionsPanel.gridx = 0;
        gbc_slimSearchOptionsPanel.gridy = 1;
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

        JLabel motifLabel = new JLabel("Run ID:");
        GridBagConstraints gbc_motifLabel = new GridBagConstraints();
        gbc_motifLabel.anchor = GridBagConstraints.WEST;
        gbc_motifLabel.insets = new Insets(0, 0, 5, 5);
        gbc_motifLabel.gridx = 0;
        gbc_motifLabel.gridy = 1;
        slimSearchOptionsPanel.add(motifLabel, gbc_motifLabel);

        JButton idCheckButton = new JButton("Check");
        GridBagConstraints gbc_checkLabel = new GridBagConstraints();
        gbc_checkLabel.anchor = GridBagConstraints.EAST;
        gbc_checkLabel.insets = new Insets(0, 0, 0, 5);
        gbc_checkLabel.gridx = 0;
        gbc_checkLabel.gridy = 1;
        slimSearchOptionsPanel.add(idCheckButton, gbc_checkLabel);

        idTextArea = new JTextArea();
        GridBagConstraints gbc_textArea = new GridBagConstraints();
        gbc_textArea.insets = new Insets(0, 0, 0, 5);
        gbc_textArea.fill = GridBagConstraints.BOTH;
        gbc_textArea.gridx = 0;
        gbc_textArea.gridy = 2;
        slimSearchOptionsPanel.add(idTextArea, gbc_textArea);

        JLabel idLabel = new JLabel("Uniprot IDs:");
        GridBagConstraints gbc1_motifLabel = new GridBagConstraints();
        gbc1_motifLabel.anchor = GridBagConstraints.WEST;
        gbc1_motifLabel.insets = new Insets(0, 0, 5, 5);
        gbc1_motifLabel.gridx = 0;
        gbc1_motifLabel.gridy = 3;
        slimSearchOptionsPanel.add(idLabel, gbc1_motifLabel);

        uniprotTextArea = new JTextArea(4, 15);
        uniprotTextArea.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(uniprotTextArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setMinimumSize(new Dimension(15, 60));
        GridBagConstraints gbc1_textArea = new GridBagConstraints();
        gbc1_textArea.insets = new Insets(0, 0, 0, 5);
        gbc1_textArea.fill = GridBagConstraints.BOTH;
        gbc1_textArea.gridx = 0;
        gbc1_textArea.gridy = 4;
        slimSearchOptionsPanel.add(scroll, gbc1_textArea);

        idCheckButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                if (idTextArea.getText().length() > 6) {
                    String id = idTextArea.getText();
                    int ready = CommonMethods.checkReady(id, openBrowser);
                    if (ready == 1) { // ready
                        resultProcessing(id);
                    } else {
                        JOptionPane.showMessageDialog(null, "This ID is still being processed. Please check back later.");
                    }
                }
            }
        });


        runSLiMFinderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CyNetwork network = manager.getCurrentNetwork();
                // There is a past runs ID in the box
                if (idTextArea.getText().length() > 6) {
                    // Send request to the server for that page
                    String id = idTextArea.getText();
                    resultProcessing(id);

                    // There are a set of IDs in the IDs box
                } else if (uniprotTextArea.getText().length() > 5) {
                    String input = uniprotTextArea.getText();
                    // Strings have to be comma+space delineated ONLY
                    List<String> ids = Arrays.asList(input.split(",\\s+|\\s+"));
                    RunSlimfinder slimfinder = new RunSlimfinder(network, null, ids, optionsPanel);
                    String url = slimfinder.getUrl();
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
                        RunSlimfinder slimfinder = new RunSlimfinder(network, selected, null, optionsPanel);
                        String url = slimfinder.getUrl();
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
        List<String> csvResults = CommonMethods.PrepareResults(
                "http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id, "&rest=main", openBrowser, id);
        if (csvResults != null) {
            displayResults(csvResults, id);
        }
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
        fullResults.setBorderPainted(false);
        fullResults.setOpaque(false);
        fullResults.setBackground(Color.WHITE);
        fullResults.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openBrowser.openURL("http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id);
            }
        });

        // Create button to take users to the help page on github
        JButton help = new JButton();
        help.setText("Help");
        help.setBorderPainted(false);
        help.setOpaque(false);
        help.setBackground(Color.WHITE);
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openBrowser.openURL("https://github.com/RayneCatseye/SLiMscape/wiki/SLiMFinder");
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
        slimfinder.add("Run " + id + " Results", resultsPane);
    }

}