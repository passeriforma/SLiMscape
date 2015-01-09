package org.cytoscape.slimscape.internal;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.slimscape.internal.ui.SlimsearchOptions;
import org.cytoscape.slimscape.internal.ui.SlimsearchOptionsPanel;

import java.util.ArrayList;
import java.util.List;

public class RunSlimsearch {
    CyNetwork network;
    private SlimsearchOptionsPanel optionsPanel;

    public RunSlimsearch(CyNetwork network, List<CyNode> selected, String motif, SlimsearchOptionsPanel optionsPanel) {
        this.network = network;
        this.optionsPanel = optionsPanel;

        List<String> uniprotIDs = getNodeIds(selected);

        String url = constructUrl(optionsPanel, uniprotIDs);

        // Send a request to the REST server

    }

    // Get the uniprot IDs of each selected element, and add them to a list
    private List<String> getNodeIds (List<CyNode> selected) {
        List<String> uniprotIDs = new ArrayList<String>();

        for (CyNode node : selected) {
            String name = network.getRow(node).get(CyNetwork.NAME, String.class); // Gets uniprot ID
            uniprotIDs.add(name);
        }

        return uniprotIDs;
    }

    // Gets the current state of the options panel, and constructs the URL to send to the REST server
    private String constructUrl (SlimsearchOptionsPanel optionsPanel, List<String> uniprotIDs) {
        // Get state of SlimsearchOptionsPanel
        SlimsearchOptions options = optionsPanel.getSlimsearchOptions();
        boolean dismask = options.getDismask();
        boolean conservation = options.getConservation();
        double cutoff = options.getCutoff();
        String custom = options.getCustomParameters();

        // Construct properly formatted string components
        String dismaskS = "&dismask=";
        if (dismask) {
            dismaskS = dismaskS + "T";
        } else {
            dismaskS = dismaskS + "F";

        }

        String consmaskS = "&consmask=";
        if (conservation) {
            consmaskS = consmaskS + "T";
        } else {
            consmaskS = consmaskS + "F";
        }

        String prob = "&probcut=" + cutoff;

        custom = custom.replace("\n", "&");
        custom = custom.replace(" ", "");
        custom = "&" + custom;

        String ids = "&uniprotid=";
        for (String id : uniprotIDs) {
            ids = ids + id + ",";
        }
        ids = ids.substring(0, ids.length()-1);

        return ("http://rest.slimsuite.unsw.edu.au/slimprob" + ids + prob + dismaskS + consmaskS + custom);
    }

}
