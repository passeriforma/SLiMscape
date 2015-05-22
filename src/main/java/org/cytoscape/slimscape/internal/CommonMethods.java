package org.cytoscape.slimscape.internal;

import org.cytoscape.util.swing.OpenBrowser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonMethods {

    /**
     * @desc checks if a run is ready on the rest servers
     * @param id - Run ID from the rest server
     * @return int - Int indicative of whether analysis should take place or not.
     *               -1 = no, do not analyse
     *               1 = analyse now
     */
    public static int checkReady(String id, OpenBrowser openBrowser) {
        int ready = CommonMethods.jobReady("http://rest.slimsuite.unsw.edu.au/check&jobid=" + id, id, openBrowser);
        if (ready == -1) { // Pressed the "no" button, do not want to refresh
            return ready;
        } else {
            while (ready != 1) {
                ready = CommonMethods.jobReady("http://rest.slimsuite.unsw.edu.au/check&jobid=" + id, id, openBrowser);
                if (ready == -1) { // Pressed the "no" button, do not want to refresh
                    break;
                }
            }
        }
        return ready;
    }

    /**
     * @desc attains and analyses main results from the Slimsuite server.
     * @param url - The url where the results should be located.
     * @return Map - A map of List<String> containing the contents of the csv and occ blocks.
     *               If there is an error, null is returned.
     */
    public static List<String> PrepareResults(String url, OpenBrowser openBrowser, String id) {
        // Gets URL data
        try {
            URL website = new URL(url);
            URLConnection connection = website.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));

            String inputLine;
            String lineOne  = in.readLine();
            // There is an error in the results obtained
            if (lineOne.startsWith("ERROR")) {
                JOptionPane.showMessageDialog(null, lineOne);
                in.close();
                return null;
            } else {
                List<String> csv = new ArrayList<String>();
                csv.add(lineOne);
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
                }
                in.close();

                return csv;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * @desc - Gets the IDs of each node in the slimdb results page.
     * @param url - the URL to search for results in
     * @return - list of all the IDs found, as strings
     */
    public static List<String> getNodeIds(String url) {

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
    public static List<String> getUpcResults(String url) {

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
    public static JTable createCsvTable (List<String> input) {
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
    public static JTable createOccTable (List<String> input) {
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
     * @desc - gets the job id from a running SLiMSuite service, for later use.
     * @param url - the URL to search for the ID in.
     * @return - the ID, as a string.
     */
    public static String getJobID(String url) {
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
                    if (inputLine.contains("###")) {
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
     * @return - 1: Job is ready
     *           0: Job is not yet ready
     *           -1: Error/job is cancelled
     */
    public static int jobReady (String url, String id, final OpenBrowser openBrowser) {
        String lineOne;
        try {
            URL website = new URL(url);
            URLConnection connection = website.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            lineOne = in.readLine();
            if (lineOne.contains("Finished")) {
                in.close();
                return 1;
            } else {
                in.close();
                String[] options = new String[2];
                options[0] = "Reload";
                options[1] = "Stop";
                int option = JOptionPane.showOptionDialog(null, "Your job (ID: " + id + ") is queued or " +
                        "running on the SLiMSuite server. This may take some time. You can Reload this box to check for" +
                        " completion, or monitor progress at that REST server: " +
                        "rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id + ". " +
                        "To continue using Cytoscape in the meantime," +
                        " press Stop and enter the Job ID in SLiMScape later to check progress or retrieve results.",
                        "Job In Progress", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
                if (option == JOptionPane.NO_OPTION) {
                    return -1;
                } else {
                    return 0;
                }
            }

        } catch (Exception ex) {
            return -1;
        }
    }

}
