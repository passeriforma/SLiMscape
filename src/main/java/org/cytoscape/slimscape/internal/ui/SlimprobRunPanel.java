package org.cytoscape.slimscape.internal.ui;

import org.cytoscape.app.CyAppAdapter;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.*;
import org.cytoscape.slimscape.internal.AlterGraph;
import org.cytoscape.slimscape.internal.CommonMethods;
import org.cytoscape.slimscape.internal.RunSlimprob;
import org.cytoscape.util.swing.OpenBrowser;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.vizmap.VisualMappingManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/*
 * @author: Kevin O'Brien
 */
public class SlimprobRunPanel extends JPanel {
    CyApplicationManager manager;
    JTextArea motifTextArea = null;
    JTextArea idTextArea = null;
    JTextArea uniprotTextArea = null;
    JButton runSlimprobButton;
    OpenBrowser openBrowser;
    SlimprobOptionsPanel optionsPanel;
    CyEventHelper eventHelper;
    CyNetworkFactory networkFactory;
    CyNetworkManager networkManager;
    CyNetworkViewFactory networkViewFactory;
    CyNetworkViewManager networkViewManager;
    VisualMappingManager visualMappingManager;
    JTabbedPane slimprob;
    CyAppAdapter adapter;

	/**
	 * Create the panel.
	 */
    public SlimprobRunPanel(final CyApplicationManager manager, final OpenBrowser openBrowser,
                            final SlimprobOptionsPanel optionsPanel, final CyEventHelper eventHelper,
                            final CyNetworkFactory networkFactory, final CyNetworkManager networkManager,
                            final CyNetworkViewFactory networkViewFactory, final CyNetworkViewManager networkViewManager,
                            final VisualMappingManager visualMappingManager, final CyAppAdapter adapter, JTabbedPane slimprob) {

        this.openBrowser = openBrowser;
        this.manager = manager;
        this.optionsPanel = optionsPanel;
        this.networkFactory = networkFactory;
        this.networkManager = networkManager;
        this.networkViewFactory = networkViewFactory;
        this.networkViewManager = networkViewManager;
        this.visualMappingManager = visualMappingManager;
        this.eventHelper = eventHelper;
        this.openBrowser = openBrowser;
        this.slimprob = slimprob;
        this.adapter = adapter;


        setBackground(new Color(238, 238, 238));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 558, 0 };
        gridBagLayout.rowHeights = new int[] { 208, 0 };
        gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
        setLayout(gridBagLayout);

        JPanel runSLiMFinderPanel = new JPanel();
        runSLiMFinderPanel.setBorder(BorderFactory.createCompoundBorder(
                new TitledBorder("Run SLiMProb"),
                new EmptyBorder(0, 0, 0, 20)));
        GridBagLayout gbl_runSLiMFinderPanel = new GridBagLayout();
        gbl_runSLiMFinderPanel.columnWidths = new int[] { 466, 0 };
        gbl_runSLiMFinderPanel.rowHeights = new int[] { 25, 110, 0, 0, 0 };
        gbl_runSLiMFinderPanel.columnWeights = new double[] { 1.0,
                Double.MIN_VALUE };
        gbl_runSLiMFinderPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE };
        runSLiMFinderPanel.setLayout(gbl_runSLiMFinderPanel);
        GridBagConstraints gbc_runSLiMFinderPanel = new GridBagConstraints();
        gbc_runSLiMFinderPanel.fill = GridBagConstraints.BOTH;
        gbc_runSLiMFinderPanel.gridx = 0;
        gbc_runSLiMFinderPanel.gridy = 0;
        add(runSLiMFinderPanel, gbc_runSLiMFinderPanel);

        JPanel slimprobOptionsPanel = new JPanel();
        slimprobOptionsPanel.setBorder(new TitledBorder(new LineBorder(
                new Color(184, 207, 229)), "New Input", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(51, 51, 51)));
        GridBagConstraints gbc_slimprobOptionsPanel = new GridBagConstraints();
        gbc_slimprobOptionsPanel.insets = new Insets(0, 0, 5, 0);
        gbc_slimprobOptionsPanel.fill = GridBagConstraints.BOTH;
        gbc_slimprobOptionsPanel.gridx = 0;
        gbc_slimprobOptionsPanel.gridy = 0;
        runSLiMFinderPanel.add(slimprobOptionsPanel,
                gbc_slimprobOptionsPanel);

        GridBagLayout gbl_slimprobOptionsPanel = new GridBagLayout();
        gbl_slimprobOptionsPanel.columnWidths = new int[] { 0, 0 };
        gbl_slimprobOptionsPanel.rowHeights = new int[] { 0, 0, 0, 0 };
        gbl_slimprobOptionsPanel.columnWeights = new double[] { 1.0,
                Double.MIN_VALUE };
        gbl_slimprobOptionsPanel.rowWeights = new double[] { 1.0, 0.0, 1.0,
                Double.MIN_VALUE };
        slimprobOptionsPanel.setLayout(gbl_slimprobOptionsPanel);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 5, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        slimprobOptionsPanel.add(panel, gbc_panel);

        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 0, 0, 0 };
        gbl_panel.rowHeights = new int[] { 0, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

        JLabel motifLabel = new JLabel("Motifs:");
        GridBagConstraints gbc_motifLabel = new GridBagConstraints();
        gbc_motifLabel.anchor = GridBagConstraints.WEST;
        gbc_motifLabel.insets = new Insets(0, 0, 5, 5);
        gbc_motifLabel.gridx = 0;
        gbc_motifLabel.gridy = 1;
        slimprobOptionsPanel.add(motifLabel, gbc_motifLabel);

        runSlimprobButton = new JButton("Run SLiMProb");
        GridBagConstraints gbc_runSlimprobButton = new GridBagConstraints();
        gbc_runSlimprobButton.anchor = GridBagConstraints.EAST;
        gbc_runSlimprobButton.insets = new Insets(0, 0, 5, 0);
        gbc_runSlimprobButton.gridx = 0;
        gbc_runSlimprobButton.gridy = 1;
        slimprobOptionsPanel.add(runSlimprobButton, gbc_runSlimprobButton);

        motifTextArea = new JTextArea();
        GridBagConstraints gbc_textArea = new GridBagConstraints();
        gbc_textArea.insets = new Insets(0, 0, 0, 5);
        gbc_textArea.fill = GridBagConstraints.BOTH;
        gbc_textArea.gridx = 0;
        gbc_textArea.gridy = 2;
        slimprobOptionsPanel.add(motifTextArea, gbc_textArea);

        JLabel uniprotLabel = new JLabel("Uniprot IDs:");
        GridBagConstraints gbc1_uniprot = new GridBagConstraints();
        gbc1_uniprot.anchor = GridBagConstraints.WEST;
        gbc1_uniprot.insets = new Insets(0, 0, 5, 5);
        gbc1_uniprot.gridx = 0;
        gbc1_uniprot.gridy = 3;
        slimprobOptionsPanel.add(uniprotLabel, gbc1_uniprot);

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
        slimprobOptionsPanel.add(scroll, gbc_uniprotTextArea);

        // Old Run ID Panel
        JPanel oldRunPanel = new JPanel();
        oldRunPanel.setBorder(new TitledBorder(new LineBorder(
                new Color(184, 207, 229)), "Past Run", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(51, 51, 51)));
        GridBagConstraints gbc_oldRunPanel = new GridBagConstraints();
        gbc_oldRunPanel.insets = new Insets(0, 0, 5, 0);
        gbc_oldRunPanel.fill = GridBagConstraints.BOTH;
        gbc_oldRunPanel.gridx = 0;
        gbc_oldRunPanel.gridy = 1;
        runSLiMFinderPanel.add(oldRunPanel, gbc_oldRunPanel);

        GridBagLayout gbl_oldRunPanel = new GridBagLayout();
        gbl_oldRunPanel.columnWidths = new int[] { 0, 0 };
        gbl_oldRunPanel.rowHeights = new int[] { 0, 0, 0, 0 };
        gbl_oldRunPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_oldRunPanel.rowWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
        oldRunPanel.setLayout(gbl_slimprobOptionsPanel);

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

        // Action listeners for the buttons
        idCheckButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                if (idTextArea.getText().length() > 6) {
                    String id = idTextArea.getText();
                    int ready = CommonMethods.checkReady(id, openBrowser);
                    if (ready == 1) { // ready
                        String url = "http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id;
                        if (CommonMethods.checkProgramsMatch(url, "SLiMProb", openBrowser)) {
                            resultProcessing(id);
                        } else {
                            JOptionPane.showMessageDialog(null, "This Run ID was not from SLiMProb.\n" +
                                    "Please use the original program to import this data.");
                        }                    } else if (ready != -2) {
                        JOptionPane.showMessageDialog(null, "This ID is still being processed. Please check back later.");
                    }
                }
            }
        });

        // Get selected nodes in the graph and send them for processing
        runSlimprobButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CyNetwork network = manager.getCurrentNetwork();
                // There are a set of IDs in the IDs box
                if (uniprotTextArea.getText().length() > 5) {
                    String input = uniprotTextArea.getText();
                    String motif = motifTextArea.getText();
                    // Strings have to be comma+space delineated ONLY
                    List<String> ids = Arrays.asList(input.split(",\\s+|\\s+"));
                    List<String> motifs = Arrays.asList(motif.split(",\\s+|\\s+"));
                    RunSlimprob slimprob = new RunSlimprob(network, null, ids, motifs, optionsPanel);
                    String url = slimprob.getUrl();
                    String id = CommonMethods.getJobID(url).replaceAll("\\s+", "");
                    idTextArea.setText(id);
                    // Make sure the job is ready before analysis starts
                    int ready = CommonMethods.checkReady(id, openBrowser);
                    if (ready == 1) {
                        resultProcessing(id);
                    }
                    // Get node IDs from the graph
                } else {
                    try {
                        List<CyNode> selected = new ArrayList<CyNode>();
                        selected.addAll(CyTableUtil.getNodesInState(network, "selected", true));
                        String motif = motifTextArea.getText();
                        List<String> motifs = Arrays.asList(motif.split(",\\s+|\\s+"));
                        RunSlimprob slimprob = new RunSlimprob(network, selected, null, motifs, optionsPanel);
                        String url = slimprob.getUrl();
                        String id = CommonMethods.getJobID(url).replaceAll("\\s+", "");
                        idTextArea.setText(id);
                        // Make sure the job is ready before analysis starts
                        int ready = CommonMethods.checkReady(id, openBrowser);
                        if (ready == 1) {
                            resultProcessing(id);
                        }
                    } catch (Exception ex){
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
                "full server run at the url: rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id + ". ");
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
        fullResults.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openBrowser.openURL("http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id);
            }
        });

        // Create button to take users to the help page on github
        JButton help = new JButton();
        help.setText("Help");
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openBrowser.openURL("https://github.com/RayneCatseye/SLiMscape/wiki/SLiMProb");
            }
        });


        JTable csv = slimprobCreateCsvTable(csvResults);
        JTable occ = CommonMethods.createOccTable(occResults);
        csv.setAutoCreateRowSorter(true);
        occ.setAutoCreateRowSorter(true);

        Map<String, ArrayList<String>> occIds = new HashMap<String, ArrayList<String>>();
        for (int y=0; y<occ.getRowCount(); y++) {
            Object name = occ.getModel().getValueAt(y, 1);
            String nameString = String.valueOf(name);
            Object pattern = occ.getModel().getValueAt(y, 0);
            String patternString = String.valueOf(pattern);
            if (!occIds.containsKey(nameString)) {
                ArrayList<String> patt = new ArrayList<String>();
                patt.add(patternString);
                occIds.put(nameString, patt);
            } else {
                ArrayList<String> patt = occIds.get(nameString);
                patt.add(patternString);
                occIds.put(nameString, patt);
            }
        }

        // Obtain the patterns found
        ArrayList<String> patterns = new ArrayList<String>();
        Object pattern = occ.getModel().getValueAt(0, 0);
        String patternString = String.valueOf(pattern);
        patterns.add(patternString);
        //JOptionPane.showMessageDialog(null, patterns);

        String programName = runSlimprobButton.getText().split(" ")[1];
        programName = programName + ' ' + id;

        // Alter the graph
        new AlterGraph(programName, nodeIds, occIds, upc, patterns, manager, eventHelper, networkFactory, networkManager,
                networkViewFactory, networkViewManager, visualMappingManager, adapter);

        // Display the results in a panel
        JPanel resultsPane = new ResultsPanel(new JScrollPane(csv), new JScrollPane(occ), fullResults, help, slimprob, id);
        slimprob.add(id, resultsPane);
    }

    /**
     * @desc Creates a csv-specific JTable from an input of comma separated strings.
     * @param input - a List<String> consisting of a series of comma-separated lines.
     * @return JTable - a table populated with the input elements.
     */
    public static JTable slimprobCreateCsvTable (List<String> input) {
        // Get column names from input
        JTable table;
        List<String> names = Arrays.asList(input.get(0).split(","));
            int s = names.size();
        List<String> abbreviated = new ArrayList<String>(names.subList(7, 11));
        List<String> abbreviated1 = new ArrayList<String>(names.subList(14, 15));
        List<String> abbreviated2 = new ArrayList<String>(names.subList(18, 22));
        abbreviated.addAll(abbreviated1);
        abbreviated.addAll(abbreviated2);
        Object columnNames[] = new String[abbreviated.size()];
        abbreviated.toArray(columnNames);

        // Create table
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(400, 700));
        table.setFillsViewportHeight(true);

        // Add a row in table for each element in the input
        int lines = input.size();
        for(int c=1; c<lines; c++) {
            List<String> line = Arrays.asList(input.get(c).split(","));
            while (line.size() < names.size()) {
                line.add(" ");
            }
            List<String> abbreviate = new ArrayList<String>(line.subList(7, 11));
            List<String> abbreviate1 = new ArrayList<String>(line.subList(14, 15));
            List<String> abbreviate2 = new ArrayList<String>(line.subList(18, 22));
            abbreviate.addAll(abbreviate1);
            abbreviate.addAll(abbreviate2);
            Object lineObject[] = new String[abbreviate.size()];
            abbreviate.toArray(lineObject);
            model.addRow(lineObject);
        }

        // Table formatting
        table.setEnabled(false);
        TableColumn column;
        for (int i = 0; i < abbreviated.size(); i++) {
            column = table.getColumnModel().getColumn(i);
            column.setMinWidth(30);
        }
        return table;
    }

}