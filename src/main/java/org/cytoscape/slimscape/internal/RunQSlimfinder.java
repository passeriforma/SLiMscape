package org.cytoscape.slimscape.internal;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.slimscape.internal.ui.SlimfinderOptions;
import org.cytoscape.slimscape.internal.ui.SlimfinderOptionsPanel;

import java.util.ArrayList;
import java.util.List;

public class RunQSlimfinder {
    CyNetwork network;
    String url;
    String query;

    public RunQSlimfinder(CyNetwork network, List<CyNode> selected, String query, SlimfinderOptionsPanel optionsPanel) {
        this.network = network;
        this.query = query;

        List<String> uniprotIDs = getNodeIds(selected);

        url = constructUrl(optionsPanel, uniprotIDs);
    }

    /**
     * @desc gets the uniprot IDs of each selected node, and returns them as a list
     * @param selected - list of CyNodes that have been selected in the graph.
     * @return list containing the Uniprot ids of all selected nodes
     */
    private List<String> getNodeIds(List<CyNode> selected) {
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
    public String constructUrl(SlimfinderOptionsPanel optionsPanel, List<String> uniprotIDs) {
        // Get state of SlimsearchOptionsPanel
        SlimfinderOptions options = optionsPanel.getSLiMFinderOptions();
        boolean dismask = options.getDismask();
        boolean conservation = options.getConsmask();
        boolean featuremask = options.getFeaturemask();
        String walltime = options.getWalltime();
        double cutoff = options.getCutoff();
        String custom = options.getCustomParameters();

        StringBuilder stringBuilder = new StringBuilder("http://rest.slimsuite.unsw.edu.au/qslimfinder");

        stringBuilder.append("&query=" + query);

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

        String featuremaskS = "&consmask=";
        if (featuremask) {
            stringBuilder.append(featuremaskS + "T");
        } else {
            stringBuilder.append(featuremaskS + "F");
        }

        stringBuilder.append("&probcut=" + cutoff);

        stringBuilder.append("&walltime=" + walltime);

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
