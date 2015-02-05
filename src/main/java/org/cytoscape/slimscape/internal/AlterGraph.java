package org.cytoscape.slimscape.internal;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.*;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.List;

public class AlterGraph extends AbstractCyAction {
    CyApplicationManager manager;
    List<String> uniprotIDs;
    CyEventHelper eventHelper;
    CyNetworkManager networkManager;
    CyNetworkViewFactory networkViewFactory;

    public AlterGraph (List<String> uniprotIDs, CyApplicationManager manager, CyEventHelper eventHelper,
                       CyNetworkFactory networkFactory, CyNetworkManager networkManager,
                       CyNetworkViewFactory networkViewFactory, CyNetworkViewManager networkViewManager) {
        super(null);
        this.manager = manager;
        this.uniprotIDs = uniprotIDs;
        this.eventHelper = eventHelper;
        this.networkManager = networkManager;
        this.networkViewFactory = networkViewFactory;

        try {
            CyNetwork network = manager.getCurrentNetwork();
            // Get state of graph
            List<CyNode> nodes = CyTableUtil.getNodesInState(network, "selected", true);
            // TODO: Alter the graph. Need to work out the fastest way to iterate.
            JOptionPane.showMessageDialog(null, "trying");
        } catch (Exception e){ // No network, need to make a new one

            CyNetwork newNetwork = networkFactory.createNetwork();
            newNetwork.getRow(newNetwork).set(CyNetwork.NAME, "SLiMOutput");
            networkManager.addNetwork(newNetwork);

            // Add nodes to empty graph
            for (String id: uniprotIDs) {
                CyNode node = newNetwork.addNode(); // Null pointer exception
                newNetwork.getRow(node).set(CyNetwork.NAME, id);
            }
            eventHelper.flushPayloadEvents();

            networkManager.addNetwork(newNetwork);
            
            // Add network view
            final Collection<CyNetworkView> views = networkViewManager.getNetworkViews(newNetwork);
            CyNetworkView myView = null;
            if(views.size() != 0) {
                myView = views.iterator().next();
            }

            if (myView == null) {
                // create a new view for my network
                myView = networkViewFactory.createNetworkView(newNetwork);
                networkViewManager.addNetworkView(myView);
            } else {
                System.out.println("networkView already existed.");
            }

        }
    }

    @Override public void actionPerformed(ActionEvent actionEvent) {

    }
}
