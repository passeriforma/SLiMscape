package org.cytoscape.slimscape.internal;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTableUtil;
import org.cytoscape.util.swing.OpenBrowser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StartButtonActionListener implements ActionListener {
    private SlimPanel panel;
    private CyApplicationManager manager;
    private OpenBrowser openBrowser;

    public StartButtonActionListener(SlimPanel panel, CyApplicationManager manager,
                                     OpenBrowser openBrowser) {
        this.panel = panel;
        this.manager = manager;
        this.openBrowser = openBrowser;
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
            //JOptionPane.showMessageDialog(null, "Node name: " + network.getRow(node).get(CyNetwork.NAME, String.class));
            String name = network.getRow(node).get(CyNetwork.NAME, String.class);
            this.openBrowser.openURL("http://www.uniprot.org/uniprot/" + name);
        }
        // JOptionPane.showMessageDialog(null, "Number of selected nodes: " + nodes.size());
    }
}
