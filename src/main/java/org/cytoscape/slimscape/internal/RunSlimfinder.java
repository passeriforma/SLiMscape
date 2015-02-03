package org.cytoscape.slimscape.internal;


import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.slimscape.internal.ui.SlimfinderOptions;
import org.cytoscape.slimscape.internal.ui.SlimfinderOptionsPanel;

import javax.swing.*;
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

        JOptionPane.showMessageDialog(null, stringBuilder.toString());

        return (stringBuilder.toString());
    }
}
