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
import java.util.*;

public class AlterGraph extends AbstractCyAction {
    CyApplicationManager manager;
    List<String> uniprotIDs;
    List<String> upc;
    CyEventHelper eventHelper;
    CyNetworkManager networkManager;
    CyNetworkViewFactory networkViewFactory;

    public AlterGraph (List<String> uniprotIDs, List<String> occNodes, List<String> upc, CyApplicationManager manager, CyEventHelper eventHelper,
                       CyNetworkFactory networkFactory, CyNetworkManager networkManager,
                       CyNetworkViewFactory networkViewFactory, CyNetworkViewManager networkViewManager) {
        super(null);
        this.uniprotIDs = uniprotIDs;
        this.upc = upc;
        this.manager = manager;
        this.eventHelper = eventHelper;
        this.networkManager = networkManager;
        this.networkViewFactory = networkViewFactory;


        try { // TODO: Alter the pre existing graph. Need to work out the fastest way to iterate.
            CyNetwork network = manager.getCurrentNetwork();
            // Get state of graph
            List<CyNode> nodes = CyTableUtil.getNodesInState(network, "selected", true);
            JOptionPane.showMessageDialog(null, "trying");

            //alterSLiMNodes(occNodes, null, network, networkViewManager);

        } catch (Exception e){ // No network, need to make a new one

            CyNetwork newNetwork = networkFactory.createNetwork();
            newNetwork.getRow(newNetwork).set(CyNetwork.NAME, "SLiMOutput");
            networkManager.addNetwork(newNetwork);

            Map<String, CyNode> nodeIds = new HashMap<String, CyNode>(); // Loses 7 nodes!!!

            // Creates the graph
            addNodes(uniprotIDs, nodeIds, newNetwork, networkViewManager);
            //alterSLiMNodes(occNodes, nodeIds, newNetwork, networkViewManager);
            addUpcConnections(upc, nodeIds, newNetwork);

            networkManager.addNetwork(newNetwork);


        }
    }

    private void addNodes (List<String> uniprotIDs, Map<String, CyNode> nodeIds, CyNetwork newNetwork, CyNetworkViewManager networkViewManager) {
        for (String id : uniprotIDs) {
            if (!nodeIds.containsKey(id)) {
                CyNode node = newNetwork.addNode();
                newNetwork.getRow(node).set(CyNetwork.NAME, id);
                nodeIds.put(id, node);
            }
        }
        eventHelper.flushPayloadEvents();

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

    private void alterSLiMNodes (List<String> occNodes, Map<String, CyNode> nodeIds, CyNetwork newNetwork, CyNetworkViewManager networkViewManager) {




    }

    private void addUpcConnections (List<String> upc, Map<String, CyNode> nodeIds, CyNetwork newNetwork) {
        for(String line : upc) {
            String[] elements = line.split("\\s");
            for (int a=0; a<elements.length-1; a++) {
                for (int b=a+1; b<elements.length; b++) {
                    if (a != b) {
                        CyNode node1 = nodeIds.get(elements[a]);
                        CyNode node2 = nodeIds.get(elements[b]);

                        if (node1 == null) {
                            CyNode node = newNetwork.addNode();
                            newNetwork.getRow(node).set(CyNetwork.NAME, elements[a]);
                            nodeIds.put(elements[a], node);
                        }
                        if (node2 == null) {
                            CyNode node = newNetwork.addNode();
                            newNetwork.getRow(node).set(CyNetwork.NAME, elements[b]);
                            nodeIds.put(elements[b], node);
                        }
                        newNetwork.addEdge(node1, node2, true);

                    }
                }
            }
        }
        eventHelper.flushPayloadEvents();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
