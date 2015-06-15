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
    String motif;

    public RunSlimprob(CyNetwork network, List<CyNode> selected, List<String> uniprotIDs, String motif, SlimprobOptionsPanel optionsPanel) {
        this.network = network;
        this.optionsPanel = optionsPanel;
        this.motif = motif;

        List<String> ids;

        if (uniprotIDs == null) {
            ids = getNodeIds(selected);
        } else {
            ids = uniprotIDs;
        }
        url = constructUrl(optionsPanel, ids);
    }

    /**
     * @desc gets the uniprot IDs of each selected node, and returns them as a list
     * @param selected - list of CyNodes that have been selected in the graph.
     * @return list containing the Uniprot ids of all selected nodes
     */
    private List<String> getNodeIds (List<CyNode> selected) {
        List<String> uniprotIDs = new ArrayList<String>();

        for (CyNode node : selected) {
            String name = network.getRow(node).get(CyNetwork.NAME, String.class); // Gets uniprot ID
            uniprotIDs.add(name);
        }

        return uniprotIDs;
    }

    /**
     * @desc Gets the current state of the options panel, and constructs the URL to send to the REST server
     * @param optionsPanel - slimpr options panel, containing all the options elements to be passed to the server
     * @param uniprotIDs - list containing the Uniprot ids of all selected nodes
     * @return the constructed URL to be passed to the server
     */
    private String constructUrl (SlimprobOptionsPanel optionsPanel, List<String> uniprotIDs) {
        // Get state of SlimprobOptionsPanel
        SlimprobOptions options = optionsPanel.getSlimprobOptions();
        boolean dismask = options.getDismask();
        boolean consmask = options.getConsmask();
        String custom = options.getCustomParameters();

        StringBuilder stringBuilder = new StringBuilder("http://rest.slimsuite.unsw.edu.au/slimprob");

        stringBuilder.append("&motif=" + motif);

        // Construct properly formatted string components
        String dismaskS = "&dismask=";
        if (dismask) {
            stringBuilder.append(dismaskS + "T");
        } else {
            stringBuilder.append(dismaskS + "F");
        }

        String consmaskS = "&dismask=";
        if (consmask) {
            stringBuilder.append(consmaskS + "T");
        } else {
            stringBuilder.append(consmaskS + "F");
        }

        if (custom.length() > 0) {
            custom = custom.replace("\n", "&");
            custom = custom.replace(" ", "");
            stringBuilder.append("&" + custom);
        }

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
