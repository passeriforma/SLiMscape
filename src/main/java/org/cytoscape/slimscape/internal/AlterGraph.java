package org.cytoscape.slimscape.internal;

import org.cytoscape.app.CyAppAdapter;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.*;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.model.View;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.cytoscape.view.presentation.property.NodeShapeVisualProperty;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.view.vizmap.VisualStyle;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class AlterGraph {
    CyApplicationManager manager;
    List<String> uniprotIDs;
    List<String> upc;
    CyEventHelper eventHelper;
    CyNetworkManager networkManager;
    CyNetworkViewFactory networkViewFactory;
    VisualMappingManager visualMappingManager;
    CyAppAdapter adapter;

    public AlterGraph (String networkName, List<String> uniprotIDs, Map<String, ArrayList<String>> occNodes, List<String> upc, CyApplicationManager manager,
                       CyEventHelper eventHelper, CyNetworkFactory networkFactory, CyNetworkManager networkManager,
                       CyNetworkViewFactory networkViewFactory, CyNetworkViewManager networkViewManager,
                       VisualMappingManager visualMappingManager, CyAppAdapter adapter) {
        this.uniprotIDs = uniprotIDs;
        this.upc = upc;
        this.manager = manager;
        this.eventHelper = eventHelper;
        this.networkManager = networkManager;
        this.networkViewFactory = networkViewFactory;
        this.visualMappingManager = visualMappingManager;
        this.adapter = adapter;

        int nodeSize = 0;

        try {
            CyNetwork network = manager.getCurrentNetwork();
            List<CyNode> nodes = CyTableUtil.getNodesInState(network, "selected", true);
            nodeSize = nodes.size();
        } catch (Exception e) {
            // This is dealt with below, in the else block
        }

        // Attempts to alter the preexisting graph. If that fails, makes a new one.
        if (nodeSize != 0) {
            CyNetwork network = manager.getCurrentNetwork();
            List<CyNode> nodes = CyTableUtil.getNodesInState(network, "selected", true);
            // Adds selected nodes to a Map
            Map<String, CyNode> nodeIds = new HashMap<String, CyNode>();
            for (CyNode node : nodes) {
                String name = network.getRow(node).get(CyNetwork.NAME, String.class);
                if (!nodeIds.containsKey(name)) {
                    nodeIds.put(name, node);
                }
            }

            Map<String, ArrayList<String>> occ = null;
            for (String occNode : occNodes.keySet()) {
                String id = occNode.split("_")[3];
                occ.put(id, occNodes.get(id));
            }

            SLiMNodeStyle(occ, nodeIds, manager, visualMappingManager);

        } else { // No network, need to make a new one
            CyNetwork newNetwork = networkFactory.createNetwork();
            newNetwork.getRow(newNetwork).set(CyNetwork.NAME, networkName);
            networkManager.addNetwork(newNetwork);

            Map<String, CyNode> nodeIds = new HashMap<String, CyNode>();

            try {
                // Creates the graph
                addNodes(uniprotIDs, nodeIds, newNetwork, networkViewManager, manager);
                SLiMNodeStyle(occNodes, nodeIds, manager, visualMappingManager);
                addUpcConnections(upc, nodeIds, newNetwork);

                networkManager.addNetwork(newNetwork);


                //CyLayoutAlgorithmManager alMan = adapter.getCyLayoutAlgorithmManager();
                //CyLayoutAlgorithm algor = alMan.getDefaultLayout();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }
    }

    /**
     * @desc - Adds nodes to the protein network from the information returned by the Slim* run.
     * @param uniprotIDs - list of all Uniprot IDs input to the returned run.
     * @param nodeIds - map linking all selected Uniprot IDs to their CyNodes, for easy access to the network.
     * @param newNetwork - CyNetwork of the network being altered.
     * @param networkViewManager - NetworkViewManager for the network being altered. Initialised in CyActivator.
     * @param manager - CyApplicationManager for the network being altered. Initialised in CyActivator.
     */
    public void addNodes (List<String> uniprotIDs, Map<String, CyNode> nodeIds, CyNetwork newNetwork,
                          CyNetworkViewManager networkViewManager, CyApplicationManager manager) {

        // Make the node table, and add appropriate columns
        CyTable table = newNetwork.getDefaultNodeTable();
        table.createColumn("Gene", String.class, false);
        table.createColumn("Species", String.class, false);
        table.createColumn("ID", String.class, false);

        // Add the nodes and their attributes
        for (String id : uniprotIDs) {
            if (!nodeIds.containsKey(id)) {
                CyNode node = newNetwork.addNode();
                table.getRow(node.getSUID()).set("Gene", id.split("_")[0]);
                table.getRow(node.getSUID()).set("Species", id.split("_")[1]);
                table.getRow(node.getSUID()).set("ID", id.split("_")[3]);
                table.getRow(node.getSUID()).set("name", id.split("_")[3]);
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

        CyNetworkView networkView =  manager.getCurrentNetworkView();
        for (Object o : nodeIds.entrySet()) {
            Map.Entry pairs = (Map.Entry) o;
            CyNode node = (CyNode) pairs.getValue();
            View<CyNode> nodeView = networkView.getNodeView(node);
            nodeView.setLockedValue(BasicVisualLexicon.NODE_SHAPE, NodeShapeVisualProperty.ELLIPSE);
            nodeView.setLockedValue(BasicVisualLexicon.NODE_BORDER_PAINT, Color.BLACK);
            nodeView.setLockedValue(BasicVisualLexicon.NODE_SIZE, 60.0);
        }
    }

    /**
     * @desc - Alters the visual style of the nodes which are known to contain SLiMs.
     * @param occNodes - List of Uniprot IDs of proteins known to contain SLiMs.
     * @param nodeIds - map linking all selected Uniprot IDs to their CyNodes, for easy access to the graph.
     * @param manager - CyApplicationManager for the network being altered. Initialised in CyActivator.
     * @param visualMappingManager - VisualMappingManager for the network. Initialised in CyActivator.
     */
    public void SLiMNodeStyle (Map<String, ArrayList<String>> occNodes, Map<String, CyNode> nodeIds, CyApplicationManager manager,
                               VisualMappingManager visualMappingManager) {
        CyNetworkView networkView =  manager.getCurrentNetworkView();

        // Get the nodes to be changing, and record how many SLiMs are there
        Map<CyNode, Integer> slimNodes = new HashMap<CyNode, Integer>();
        for (String id : occNodes.keySet()) {
            if (nodeIds.containsKey(id)) {
                CyNode node = nodeIds.get(id); // We should have the node here
                if (!slimNodes.containsKey(node)) { //TODO: Check this works
                    ArrayList<String> patterns = occNodes.get(id);
                    Set<String> noDupl = new HashSet<String>();
                    noDupl.addAll(patterns);
                    patterns.clear();
                    patterns.addAll(noDupl);
                    slimNodes.put(node, patterns.size());
                }
            }
        }

        // Alter the nodes accordingly.
        for (CyNode node : slimNodes.keySet()) {
            View<CyNode> nodeView = networkView.getNodeView(node);

            // The colour of the nodes changes depending on the number of SLiMs present.
            // More SLiMs means a darker colour
            if (slimNodes.get(node) == 1) {
                nodeView.setLockedValue(BasicVisualLexicon.NODE_FILL_COLOR, Color.RED);
            } else {
                nodeView.setLockedValue(BasicVisualLexicon.NODE_FILL_COLOR, new Color(180, 0, 0));

            }
            nodeView.setLockedValue(BasicVisualLexicon.NODE_SHAPE, NodeShapeVisualProperty.DIAMOND);
            nodeView.setLockedValue(BasicVisualLexicon.NODE_BORDER_PAINT, Color.BLACK);
            nodeView.setLockedValue(BasicVisualLexicon.NODE_SIZE, 60.0);
        }

        VisualStyle style = visualMappingManager.getCurrentVisualStyle();
        style.apply(networkView);
        networkView.updateView();
    }

    /**
     * @desc - Function to add UPC connections to a Cytoscape network.
     * @param upc - list of strings consisting of \s separated lists of connected nodes; drawn from upc output of slim*.
     * @param nodeIds - map linking Uniprot IDs to their CyNodes, for easy access to the network.
     * @param newNetwork - CyNetwork of the network being altered.
     */
    public void addUpcConnections (List<String> upc, Map<String, CyNode> nodeIds, CyNetwork newNetwork) {
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

}
