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
    String url;

    public RunSlimprob(CyNetwork network, List<CyNode> selected, String motif, SlimprobOptionsPanel optionsPanel) {
        this.network = network;
        this.optionsPanel = optionsPanel;

        List<String> uniprotIDs = getNodeIds(selected);

        url = constructUrl(optionsPanel, uniprotIDs);
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

        StringBuilder stringBuilder = new StringBuilder("http://rest.slimsuite.unsw.edu.au/slimprob");

        // Construct properly formatted string components
        String dismaskS = "&dismask=";
        if (dismask) {
            stringBuilder.append(dismaskS + "T");
        } else {
            stringBuilder.append(dismaskS + "F");
        }

        String consmaskS = "&consmask=";
        if (conservation) {
            stringBuilder.append(consmaskS + "T");
        } else {
            stringBuilder.append(consmaskS + "F");
        }

        stringBuilder.append("&probcut=" + cutoff);

        custom = custom.replace("\n", "&");
        custom = custom.replace(" ", "");
        stringBuilder.append("&" + custom);

        String ids = "&uniprotid=";
        for (String id : uniprotIDs) {
            ids = ids + id + ",";
        }
        ids = ids.substring(0, ids.length() - 1);
        stringBuilder.append(ids);

        // Make the final string

        return (stringBuilder.toString());
    }

    public String getUrl() {
        return url;
    }

}
