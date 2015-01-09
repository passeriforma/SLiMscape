package org.cytoscape.slimscape.internal;


import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.slimscape.internal.ui.SlimfinderOptions;
import org.cytoscape.slimscape.internal.ui.SlimfinderOptionsPanel;

import java.util.ArrayList;
import java.util.List;

public class RunSlimfinder {
    CyNetwork network;
    private SlimfinderOptionsPanel optionsPanel;

    public RunSlimfinder(CyNetwork network, List<CyNode> selected, SlimfinderOptionsPanel optionsPanel) {
        this.network = network;
        this.optionsPanel = optionsPanel;

        List<String> uniprotIDs = getNodeIds(selected);

        String url = constructUrl(optionsPanel, uniprotIDs);

        // TODO: Send a request to the REST server

    }

    // Gets the uniprot IDs of each selected element, and adds them to a list
    private List<String> getNodeIds(List<CyNode> selected) {
        List<String> uniprotIDs = new ArrayList<String>();

        for (CyNode node : selected) {
            String name = network.getRow(node).get(CyNetwork.NAME, String.class); // Gets uniprot ID
            uniprotIDs.add(name);
        }

        return uniprotIDs;
    }

    // Gets the current state of the options panel, and constructs the URL to send to the REST server
    private String constructUrl(SlimfinderOptionsPanel optionsPanel, List<String> uniprotIDs) {
        // Get state of SlimsearchOptionsPanel
        SlimfinderOptions options = optionsPanel.getSLiMFinderOptions();
        boolean dismask = options.getDismask();
        boolean conservation = options.getConsmask();
        boolean featuremask = options.getFeaturemask();
        String walltime = options.getWalltime();
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

        String featuremaskS = "&consmask=";
        if (featuremask) {
            featuremaskS = featuremaskS + "T";
        } else {
            featuremaskS = featuremaskS + "F";
        }

        String prob = "&probcut=" + cutoff;

        walltime = "&walltime=" + walltime;

        custom = custom.replace("\n", "&");
        custom = custom.replace(" ", "");
        custom = "&" + custom;

        String ids = "&uniprotid=";
        for (String id : uniprotIDs) {
            ids = ids + id + ",";
        }
        ids = ids.substring(0, ids.length() - 1);

        // Make the final string


        return ("http://rest.slimsuite.unsw.edu.au/slimprob" + ids + prob + dismaskS + consmaskS + featuremaskS +
                walltime + custom);
    }
}
