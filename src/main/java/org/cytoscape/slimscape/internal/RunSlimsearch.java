package org.cytoscape.slimscape.internal;

import org.biojava3.core.sequence.ProteinSequence;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.slimscape.internal.ui.SlimsearchOptions;
import org.cytoscape.slimscape.internal.ui.SlimsearchOptionsPanel;

import javax.swing.*;
import java.util.Hashtable;
import java.util.List;

public class RunSlimsearch {
    CyNetwork network;
    private SlimsearchOptionsPanel optionsPanel;

    public RunSlimsearch(CyNetwork network, List<CyNode> selected, String motif, SlimsearchOptionsPanel optionsPanel) {
        this.network = network;
        this.optionsPanel = optionsPanel;

        // Get the FASTA sequence for each selected node, and put them in a hash table with their IDs
        Hashtable<String, ProteinSequence> selectedFasta = null;

        for (CyNode node : selected) {
            String name = network.getRow(node).get(CyNetwork.NAME, String.class); // Gets uniprot ID
            JOptionPane.showMessageDialog(null, "ID: " + name);
            // TODO: Get the fasta/uniprot sequence for each node
        }

        // Get state of SlimsearchOptionsPanel
        SlimsearchOptions options = optionsPanel.getSlimsearchOptions();
        JOptionPane.showMessageDialog(null, options.getDismask());

        // Send a request to the REST server

        // "dismask=" T/F
        // "probcut=" number
        // "consmask=" T/F
        // custom Parens (replace \n and ' ')
    }

}
