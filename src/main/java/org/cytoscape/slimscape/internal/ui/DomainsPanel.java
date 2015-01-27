package org.cytoscape.slimscape.internal.ui;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTableUtil;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;
import java.util.List;


public class DomainsPanel extends JPanel {
    private CyApplicationManager manager;

    public DomainsPanel (CyApplicationManager manager) {
        this.manager = manager;

        // Need to add a button to make the thing happen

        // Get all nodes in the graph
        CyNetwork network = manager.getCurrentNetwork();
        List<CyNode> nodes = CyTableUtil.getNodesInState(network, "selected", true);

        Hashtable<String, String> domains = getDomains(nodes, network);

        // Display these as a table in the panel (with an unchecked checkbox for display)

        // Onclick, cause a change in relevant nodes in the graph.
    }

    // WOW SO UNTESTED YOU SHOULD TEST THIS AT SOME STAGE
    private Hashtable<String, String> getDomains(List<CyNode> nodes, CyNetwork network) {
        Hashtable<String, String> domains = null;
        for(CyNode node: nodes) {

            // Get the uniprot ID
            String name = network.getRow(node).get(CyNetwork.NAME, String.class);

            try {
                URL website = new URL("http://www.uniprot.org/uniprot/"+ name + ".txt");

                URLConnection connection = website.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.substring(0,2) == "DR") {
                        // Grab elements 1 and 3
                        String[] components = inputLine.split(";");
                        String database = components[0].split("\\s*")[1];
                        String className = components[2];
                        String domainsKey = database + "," + className;

                        if (domains.containsKey(domainsKey)) {
                            String toAppend = domains.get(domainsKey);
                            toAppend = toAppend + "," + name;
                            domains.put(domainsKey, toAppend);
                        } else {
                            domains.put(domainsKey, name);
                        }
                    }
                }
            } catch (java.io.IOException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return domains;
    }

}
