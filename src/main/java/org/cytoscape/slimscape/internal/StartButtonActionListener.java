package org.cytoscape.slimscape.internal;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.CyTableUtil;
import org.osgi.framework.BundleContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StartButtonActionListener implements ActionListener {
    private SlimPanel panel;
    private CyApplicationManager manager;
    private BundleContext context;

    public StartButtonActionListener(SlimPanel panel, CyApplicationManager manager,
                                     BundleContext context) {
        this.panel = panel;
        this.manager = manager;
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (manager.getCurrentNetwork() == null) {
            return;
        }

        CyNetwork network = manager.getCurrentNetwork();
        // Get a list of all selected nodes
        List<CyNode> nodes = CyTableUtil.getNodesInState(network, "selected", true);

        for (CyNode node : nodes) {
            JOptionPane.showMessageDialog(null, "Node name: " + network.getRow(node).get(CyNetwork.NAME, String.class));
        }
        JOptionPane.showMessageDialog(null, "Number of selected nodes: " + nodes.size());
    }
}
