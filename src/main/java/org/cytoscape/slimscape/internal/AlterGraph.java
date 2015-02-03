package org.cytoscape.slimscape.internal;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTableUtil;
import org.cytoscape.view.model.CyNetworkView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AlterGraph extends AbstractCyAction {
    CyApplicationManager manager;
    List<String> uniprotIDs;
    CyEventHelper eventHelper;

    public AlterGraph (List<String> uniprotIDs, final CyApplicationManager manager, CyEventHelper eventHelper) {
        super(null);
        this.manager = manager;
        this.uniprotIDs = uniprotIDs;
        this.eventHelper = eventHelper;

        CyNetwork network = manager.getCurrentNetwork();
        CyNetworkView view = manager.getCurrentNetworkView();

        try {
            // Get state of graph
            List<CyNode> nodes = CyTableUtil.getNodesInState(network, "selected", true);
            // TODO: Alter the graph. Need to work out the fastest way to iterate.
            JOptionPane.showMessageDialog(null, "trying");
        } catch (Exception e){ // No graph
            JOptionPane.showMessageDialog(null, "HI");
            // Add nodes to empty graph
            //for (String id: uniprotIDs) {
            //    CyNode node = network.addNode();
            //    network.getRow(node).set(CyNetwork.NAME, id);
            //}
            //eventHelper.flushPayloadEvents();
            //view.updateView();
        }
        // No graph present, create one


    }

    @Override public void actionPerformed(ActionEvent actionEvent) {

    }
}
