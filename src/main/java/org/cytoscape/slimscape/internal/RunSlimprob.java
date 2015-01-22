package org.cytoscape.slimscape.internal;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.slimscape.internal.ui.SlimprobOptions;
import org.cytoscape.slimscape.internal.ui.SlimprobOptionsPanel;

import java.util.ArrayList;
import java.util.List;

public class RunSlimprob {
    CyNetwork network;
    private SlimprobOptionsPanel optionsPanel;

    public RunSlimprob(CyNetwork network, List<CyNode> selected, String motif, SlimprobOptionsPanel optionsPanel) {
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
    private String constructUrl (SlimprobOptionsPanel optionsPanel, List<String> uniprotIDs) {
        // Get state of SlimprobOptionsPanel
        SlimprobOptions options = optionsPanel.getSlimprobOptions();
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

        return ("http://rest.slimsuite.unsw.edu.au/slimprob" + ids + prob + dismaskS + consmaskS + custom + "&rest=full");
    }

}
