package org.cytoscape.slimscape.internal.ui;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.*;
import org.cytoscape.slimscape.internal.AlterGraph;
import org.cytoscape.slimscape.internal.RunSlimfinder;
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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @author: Kevin O'Brien
 */
public class SlimfinderRunPanel extends JPanel {

    JComboBox comboBox = null;
    JComboBox attributeNameCombobox = null;
    JTextArea idTextArea = null;
    SlimfinderOptionsPanel optionsPanel;
    CyApplicationManager manager;
    List<String> input;
    CyNetworkFactory networkFactory;
    CyNetworkManager networkManager;
    CyNetworkViewFactory networkViewFactory;
    CyNetworkViewManager networkViewManager;
    VisualMappingManager visualMappingManager;
    CyEventHelper eventHelper;
    OpenBrowser openBrowser;

    public SlimfinderRunPanel(final CyApplicationManager manager, final OpenBrowser openBrowser,
                              final SlimfinderOptionsPanel optionsPanel, final CyEventHelper eventHelper,
                              final CyNetworkFactory networkFactory, final CyNetworkManager networkManager,
                              final CyNetworkViewFactory networkViewFactory, final CyNetworkViewManager networkViewManager,
                              final VisualMappingManager visualMappingManager) {
        this.networkFactory = networkFactory;
        this.manager = manager;
        this.networkManager = networkManager;
        this.networkViewFactory = networkViewFactory;
        this.networkViewManager = networkViewManager;
        this.visualMappingManager = visualMappingManager;
        this.eventHelper = eventHelper;
        this.openBrowser = openBrowser;

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
        JButton runSLiMFinderButton = new JButton("RunSLiMFinder");
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

        JLabel idLabel = new JLabel("Run ID:");
        GridBagConstraints gbc1_motifLabel = new GridBagConstraints();
        gbc1_motifLabel.anchor = GridBagConstraints.WEST;
        gbc1_motifLabel.insets = new Insets(0, 0, 5, 5);
        gbc1_motifLabel.gridx = 0;
        gbc1_motifLabel.gridy = 1;
        slimSearchOptionsPanel.add(idLabel, gbc1_motifLabel);

        idTextArea = new JTextArea();
        GridBagConstraints gbc1_textArea = new GridBagConstraints();
        gbc1_textArea.insets = new Insets(0, 0, 0, 5);
        gbc1_textArea.fill = GridBagConstraints.BOTH;
        gbc1_textArea.gridx = 0;
        gbc1_textArea.gridy = 2;
        slimSearchOptionsPanel.add(idTextArea, gbc1_textArea);

        runSLiMFinderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CyNetwork network = manager.getCurrentNetwork();
                // There is a past runs ID in the box
                if (idTextArea.getText().length() > 6) {
                    // Send request to the server for that page
                    String id = idTextArea.getText();
                    try {
                        List<String> csvResults = PrepareResults(
                                ("http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id + "&rest=main"));
                        if (csvResults == null) {
                            throwError(id);
                        } else {
                            displayResults(csvResults, id);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                } else {
                    List<CyNode> selected = new ArrayList<CyNode>();
                    selected.addAll(CyTableUtil.getNodesInState(network, "selected", true));
                    if (selected.size() > 0) {
                        RunSlimfinder slimfinder = new RunSlimfinder(network, selected, optionsPanel);
                        String url = slimfinder.getUrl();
                        String id = getJobID(url).replaceAll("\\s+","");
                        // Make sure the job is ready before analysis starts
                        boolean ready = jobReady("http://rest.slimsuite.unsw.edu.au/check&jobid=" + id);
                        JOptionPane.showMessageDialog(null, id);

                        while (ready == false) {
                            ready = jobReady("http://rest.slimsuite.unsw.edu.au/check&jobid=" + id);
                        }
                        try {
                            List<String> csvResults = PrepareResults(
                                    ("http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id + "&rest=main"));
                            if (csvResults == null) {
                                throwError(id);
                            } else {
                                displayResults(csvResults, id);
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No nodes selected!");
                    }
                }
            }
        });
    }

    /**
     * @desc attains and analyses main results from the Slimsuite server.
     * @param url - The url where the results should be located.
     * @return Map - A map of List<String> containing the contents of the csv and occ blocks.
     *               If there is an error, null is returned.
     */
    private List<String> PrepareResults(String url) {
        // Gets URL data
        try {
            URL website = new URL(url);
            URLConnection connection = website.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));

            String inputLine;
            String lineOne = in.readLine();

            // There is an error in the results obtained
            if (lineOne.startsWith("ERROR")) {
                in.close();
                return null;
            } else {
                List<String> csv = new ArrayList<String>();  // TODO: Add error catching if there is nothing in there-
                csv.add(lineOne);
                int lines = 0;
                while ((inputLine = in.readLine()) != null) {
                    StringBuilder builder = new StringBuilder();
                    boolean inBraces = false;
                    for (char x : inputLine.toCharArray()) {
                        if (x == '{') {
                            inBraces = true;
                            builder.append(x);
                        } else if (x == '}') {
                            inBraces = false;
                            builder.append(x);
                        } else if (x == ',' && inBraces) {
                            builder.append('|');
                        } else {
                            builder.append(x);
                        }
                    }

                    String toReturn = builder.toString();
                    csv.add(toReturn);
                    lines ++;
                }
                in.close();

                return csv;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + " in function");
            return null;
        }
    }

    /**
     * @desc - Gets the IDs of each node in the slimdb results page.
     * @param url - the URL to search for results in
     * @return - list of all the IDs found, as strings
     */
    private List<String> getNodeIds(String url) {

        try {
            URL website = new URL(url);
            URLConnection connection = website.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));

            String inputLine;

            List<String> nodeIds = new ArrayList<String>();
            // Add each line with more than two connections
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.startsWith(">")) {
                    String[] line = inputLine.split(" ");
                    nodeIds.add(line[0].substring(1));
                }
            }
            in.close();
            return nodeIds;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + " in function");
            return null;
        }

    }

    /**
     * @desc attains and analyses the upc results from the Slimsuite server, so that graph edges can be added later.
     * @param url - The url where the results should be located.
     * @return Map - A map of List<String> containing the contents of the upc lines with >1 elements present.
     *               If there is an error, null is returned.
     */
    private List<String> getUpcResults(String url) {

        try {
            URL website = new URL(url);
            URLConnection connection = website.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));

            String inputLine;
            String lineOne = in.readLine();

            // There is an error in the results obtained
            if (lineOne.startsWith("ERROR")) {
                in.close();
                return null;
            } else {
                in.readLine(); // Removes the second line which contains unnecessary data for our purposes
                List<String> upc = new ArrayList<String>();
                // Add each line with more than two connections
                while ((inputLine = in.readLine()) != null) {
                    String[] line = inputLine.split("\\t");
                    if (Integer.parseInt(line[1]) > 1) {
                        upc.add(line[3]);
                    }
                }
                in.close();

                return upc;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + " in function");
            return null;
        }
    }

    /**
     * @desc Creates a csv-specific JTable from an input of comma separated strings.
     * @param input - a List<String> consisting of a series of comma-separated lines.
     * @return JTable - a table populated with the input elements.
     */
    private JTable createCsvTable (List<String> input) {
        // Get column names from input
        JTable table;
        List<String> names = Arrays.asList(input.get(0).split(","));
        List<String> abbreviated = names.subList(10, names.size()-5);
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
            List<String> abbreviate = line.subList(10, line.size()-5);
            Object lineObject[] = new String[abbreviate.size()];
            abbreviate.toArray(lineObject);
            model.addRow(lineObject);
        }

        // Table formatting
        table.setEnabled(false);
        TableColumn column;
        for (int i = 0; i < abbreviated.size(); i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 2) {
                column.setMinWidth(100);
            } else {
                column.setMinWidth(50);
            }
        }
        return table;
    }

    /**
     * @desc Creates an occ-specific JTable from an input of comma separated strings.
     * @param input - a List<String> consisting of a series of comma-separated lines.
     * @return JTable - a table populated with the input elements.
     */
    private JTable createOccTable (List<String> input) {
        JTable table;
        List<String> names = Arrays.asList(input.get(0).split(","));
        List<String> abbreviated = names.subList(3, names.size()-7);
        Object columnNames[] = new String[abbreviated.size()]; // line 2
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
            List<String> abbreviate = line.subList(3, line.size()-7);
            Object lineObject[] = new String[abbreviate.size()];
            abbreviate.toArray(lineObject);
            model.addRow(lineObject);
        }

        // Table formatting
        table.setEnabled(false);
        TableColumn column;
        for (int i = 0; i < abbreviated.size(); i++) {
            column = table.getColumnModel().getColumn(i);
            column.setMinWidth(100);
        }

        return table;
    }

    /**
     * @desc - creates the results panels and alters/creates the cytoscape network as required.
     * @param csvResults  - processed results from the main page.
     * @param id -  run ID from the server.
     */
    private void displayResults(List<String> csvResults, String id) {
        // Get OCC Results
        List<String> occResults = PrepareResults(
                ("http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id + "&rest=occ"));

        // Get list of all node IDs from slimdb
        List<String> nodeIds = getNodeIds("http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id
                + "&rest=slimdb");

        // Get graph edge data from upc outputs
        List<String> upc = getUpcResults("http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id
                + "&rest=upc");

        // Display main (CSV) results
        JTable csv = createCsvTable(csvResults);
        JPanel csvPanel = new JPanel();
        csvPanel.setLayout(new BorderLayout());
        csvPanel.add(new JScrollPane(csv), BorderLayout.CENTER);
        JFrame csvFrame = new JFrame("Main Results");
        csvFrame.getContentPane().add(csvPanel);
        csvFrame.pack();
        csvFrame.setVisible(true);

        // Display OCC results

        JTable occ = createOccTable(occResults);
        JPanel occPanel = new JPanel();
        occPanel.setLayout(new BorderLayout());
        occPanel.add(new JScrollPane(occ), BorderLayout.CENTER);
        JFrame occFrame = new JFrame("OCC Results");
        occFrame.getContentPane().add(occPanel);
        occFrame.pack();
        occFrame.setVisible(true);

        List<String> occIds = new ArrayList<String>();
        for (int y=0; y<occ.getRowCount(); y++) {
            Object current = occ.getModel().getValueAt(y, 2);
            String string = String.valueOf(current);
            occIds.add(string);
        }

        // Alter the graph
        new AlterGraph(nodeIds, occIds, upc, manager, eventHelper, networkFactory, networkManager,
                networkViewFactory, networkViewManager, visualMappingManager);    }

    /**
     * @desc - Provides an error popup and opens the server run page in case of an error
     * @param id - the run ID of the server process
     */
    private void throwError (String id) {
        openBrowser.openURL("http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id);
        //JOptionPane.showMessageDialog(null, "Something went wrong. " +
          //      "Opening the output page in a web browser.");
    }

    /**
     * @desc - gets the job id from a running SLiMSuite service, for later use.
     * @param url - the URL to search for the ID in.
     * @return - the ID, as a string.
     */
    private String getJobID (String url) {
        try {
            URL website = new URL(url);
            URLConnection connection = website.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));

            String inputLine;
            String lineOne = in.readLine();

            // There is an error in the results obtained
            if (lineOne.startsWith("ERROR")) {
                in.close();
                return null;
            } else {
                String id = null;
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.contains("### slimfinder job")) {
                        id = inputLine.split("job")[1];
                    }
                }
                in.close();
                return id;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + " in function");
            return null;
        }
    }

    /**
     * @desc - Determines whether the job is ready for analysis or still running
     * @param url - the URL of the run
     */
    private boolean jobReady (String url) {
        String lineOne = null;
        try {
            URL website = new URL(url);
            URLConnection connection = website.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            lineOne = in.readLine();
            if (lineOne.contains("Finished")) {
                in.close();
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Run is currently: " + lineOne + ". Click OK to check again.");
                in.close();
                return false;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
    }
}