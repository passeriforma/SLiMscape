package org.cytoscape.slimscape.internal.ui;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTableUtil;
import org.cytoscape.util.swing.OpenBrowser;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/*
 * @author: Kevin O'Brien
 */
public class SlimsearchRunPanel extends JPanel {
    CyApplicationManager manager;
    private JTextArea motifTextArea = null;
    final CyNetwork network;
    OpenBrowser openBrowser;

	/**
	 * Create the panel.
	 */
    public SlimsearchRunPanel (final CyApplicationManager manager, OpenBrowser openBrowser) {

        this.openBrowser = openBrowser;
        this.manager = manager;
        final CyNetwork network = manager.getCurrentNetwork();
        this.network = network;

        setBackground(new Color(238, 238, 238));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 558, 0 };
        gridBagLayout.rowHeights = new int[] { 208, 0 };
        gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
        setLayout(gridBagLayout);

        JPanel runSLiMFinderPanel = new JPanel();
        runSLiMFinderPanel.setBorder(new TitledBorder(null, "Run SLiMSearch",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GridBagLayout gbl_runSLiMFinderPanel = new GridBagLayout();
        gbl_runSLiMFinderPanel.columnWidths = new int[] { 466, 0 };
        gbl_runSLiMFinderPanel.rowHeights = new int[] { 25, 110, 0, 0, 0 };
        gbl_runSLiMFinderPanel.columnWeights = new double[] { 1.0,
                Double.MIN_VALUE };
        gbl_runSLiMFinderPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE };
        runSLiMFinderPanel.setLayout(gbl_runSLiMFinderPanel);
        GridBagConstraints gbc_runSLiMFinderPanel = new GridBagConstraints();
        gbc_runSLiMFinderPanel.fill = GridBagConstraints.BOTH;
        gbc_runSLiMFinderPanel.gridx = 0;
        gbc_runSLiMFinderPanel.gridy = 0;
        add(runSLiMFinderPanel, gbc_runSLiMFinderPanel);
        JButton runSLiMSearchButton = new JButton("RunSLiMSearch");

        // Get selected nodes in the graph and send them for processing
        runSLiMSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CyNetwork network = manager.getCurrentNetwork();
                List<CyNode> selected = CyTableUtil.getNodesInState(network, "selected", true);

                if (selected.size() > 0) {
                    if (motifTextArea.getText().length() > 0) {
                        String motif = motifTextArea.getText(); // THIS IS MY PROBLEM
                        runSlimsearch(network, selected, motif);
                    } else {
                        JOptionPane.showMessageDialog(null, "No motif in the text area!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No nodes selected!");
                }
            }
        });

        GridBagConstraints gbc_runSLiMSearchButton = new GridBagConstraints();
        gbc_runSLiMSearchButton.anchor = GridBagConstraints.NORTHWEST;
        gbc_runSLiMSearchButton.insets = new Insets(0, 0, 5, 0);
        gbc_runSLiMSearchButton.gridx = 0;
        gbc_runSLiMSearchButton.gridy = 0;
        runSLiMFinderPanel.add(runSLiMSearchButton, gbc_runSLiMSearchButton);

        JPanel slimSearchOptionsPanel = new JPanel();
        slimSearchOptionsPanel.setBorder(new TitledBorder(new LineBorder(
                new Color(184, 207, 229)), "Parameters", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(51, 51, 51)));
        GridBagConstraints gbc_slimSearchOptionsPanel = new GridBagConstraints();
        gbc_slimSearchOptionsPanel.insets = new Insets(0, 0, 5, 0);
        gbc_slimSearchOptionsPanel.fill = GridBagConstraints.BOTH;
        gbc_slimSearchOptionsPanel.gridx = 0;
        gbc_slimSearchOptionsPanel.gridy = 1;
        runSLiMFinderPanel.add(slimSearchOptionsPanel,
                gbc_slimSearchOptionsPanel);
        GridBagLayout gbl_slimSearchOptionsPanel = new GridBagLayout();
        gbl_slimSearchOptionsPanel.columnWidths = new int[] { 0, 0 };
        gbl_slimSearchOptionsPanel.rowHeights = new int[] { 0, 0, 0, 0 };
        gbl_slimSearchOptionsPanel.columnWeights = new double[] { 1.0,
                Double.MIN_VALUE };
        gbl_slimSearchOptionsPanel.rowWeights = new double[] { 1.0, 0.0, 1.0,
                Double.MIN_VALUE };
        slimSearchOptionsPanel.setLayout(gbl_slimSearchOptionsPanel);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 5, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        slimSearchOptionsPanel.add(panel, gbc_panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 0, 0, 0 };
        gbl_panel.rowHeights = new int[] { 0, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

        JLabel motifLabel = new JLabel("Motifs:");
        GridBagConstraints gbc_motifLabel = new GridBagConstraints();
        gbc_motifLabel.anchor = GridBagConstraints.WEST;
        gbc_motifLabel.insets = new Insets(0, 0, 5, 5);
        gbc_motifLabel.gridx = 0;
        gbc_motifLabel.gridy = 1;
        slimSearchOptionsPanel.add(motifLabel, gbc_motifLabel);

        motifTextArea = new JTextArea();
        GridBagConstraints gbc_textArea = new GridBagConstraints();
        gbc_textArea.insets = new Insets(0, 0, 0, 5);
        gbc_textArea.fill = GridBagConstraints.BOTH;
        gbc_textArea.gridx = 0;
        gbc_textArea.gridy = 2;
        slimSearchOptionsPanel.add(motifTextArea, gbc_textArea);

    }

    public void runSlimsearch(CyNetwork network, List<CyNode> selected, String motif) {
        // get FASTA for each
        // To do this, get the uniprot ID and ask uniprot.
        // Then, get info from the options panel
        // Finally, send to server
        // Should the last bits be in another class?
        for (CyNode node : selected) {
            //JOptionPane.showMessageDialog(null, "Node name: " + network.getRow(node).get(CyNetwork.NAME, String.class));
            String name = network.getRow(node).get(CyNetwork.NAME, String.class);
            this.openBrowser.openURL("http://www.uniprot.org/uniprot/" + name);
        }
    }
}