package org.cytoscape.slimscape.internal;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.slimscape.internal.ui.SlimsearchOptions;
import org.cytoscape.slimscape.internal.ui.SlimsearchOptionsPanel;

import javax.swing.*;
import java.util.List;

public class RunSlimsearch {
    CyNetwork network;
    private SlimsearchOptionsPanel optionsPanel;

    public RunSlimsearch(CyNetwork network, List<CyNode> selected, String motif, SlimsearchOptionsPanel optionsPanel) {
        this.network = network;
        this.optionsPanel = optionsPanel;

        // Get the uniprot IDs of each selected element, and add them to a list
        List<String> uniprotIDs = null;

        for (CyNode node : selected) {
            String name = network.getRow(node).get(CyNetwork.NAME, String.class); // Gets uniprot ID
            JOptionPane.showMessageDialog(null, name);
            uniprotIDs.add(name);
        }

        // Get state of SlimsearchOptionsPanel
        SlimsearchOptions options = optionsPanel.getSlimsearchOptions();
        boolean dismask = options.getDismask();
        boolean conservation = options.getConservation();
        double cutoff = options.getCutoff();
        String custom = options.getCustomParameters();

        // Send a request to the REST server
        // Remember to send the list of uniprot IDs

        // "dismask=" T/F
        // "probcut=" number
        // "consmask=" T/F
        // custom Parens (replace \n and ' ')
    }

}
