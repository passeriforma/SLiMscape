package org.cytoscape.slimscape.internal.ui;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTableUtil;
import org.cytoscape.slimscape.internal.RunSlimprob;
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
public class SlimprobRunPanel extends JPanel {
    CyApplicationManager manager;
    private JTextArea motifTextArea = null;
    private JTextArea idTextArea = null;
    final CyNetwork network;
    OpenBrowser openBrowser;
    private SlimprobOptionsPanel optionsPanel;

	/**
	 * Create the panel.
	 */
    public SlimprobRunPanel(final CyApplicationManager manager, final OpenBrowser openBrowser,
                            final SlimprobOptionsPanel optionsPanel) {

        this.openBrowser = openBrowser;
        this.manager = manager;
        CyNetwork network = manager.getCurrentNetwork();
        this.network = network;
        this.optionsPanel = optionsPanel;

        setBackground(new Color(238, 238, 238));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 558, 0 };
        gridBagLayout.rowHeights = new int[] { 208, 0 };
        gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
        setLayout(gridBagLayout);

        JPanel runSLiMFinderPanel = new JPanel();
        runSLiMFinderPanel.setBorder(new TitledBorder(null, "Run Slimprob",
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
        JButton runSlimprobButton = new JButton("RunSlimprob");

        // Get selected nodes in the graph and send them for processing
        runSlimprobButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CyNetwork network = manager.getCurrentNetwork();
                // There is a past runs ID in the box
                if (idTextArea.getText().length() > 0) {
                    // Send request to the server for that page
                    String id = idTextArea.getText();
                    try {
                        // new SlimprobPrepareResults(("http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id + "&rest=full"));
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                } else {
                    List<CyNode> selected = CyTableUtil.getNodesInState(network, "selected", true);
                    if (selected.size() > 0) {
                        if (motifTextArea.getText().length() > 0) {
                            String motif = motifTextArea.getText();
                            new RunSlimprob(network, selected, motif, optionsPanel);
                        } else {
                            JOptionPane.showMessageDialog(null, "No motif in the text area!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No nodes selected!");
                    }
                }
            }
        });

        GridBagConstraints gbc_runSlimprobButton = new GridBagConstraints();
        gbc_runSlimprobButton.anchor = GridBagConstraints.NORTHWEST;
        gbc_runSlimprobButton.insets = new Insets(0, 0, 5, 0);
        gbc_runSlimprobButton.gridx = 0;
        gbc_runSlimprobButton.gridy = 0;
        runSLiMFinderPanel.add(runSlimprobButton, gbc_runSlimprobButton);

        JPanel slimprobOptionsPanel = new JPanel();
        slimprobOptionsPanel.setBorder(new TitledBorder(new LineBorder(
                new Color(184, 207, 229)), "Parameters", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(51, 51, 51)));
        GridBagConstraints gbc_slimprobOptionsPanel = new GridBagConstraints();
        gbc_slimprobOptionsPanel.insets = new Insets(0, 0, 5, 0);
        gbc_slimprobOptionsPanel.fill = GridBagConstraints.BOTH;
        gbc_slimprobOptionsPanel.gridx = 0;
        gbc_slimprobOptionsPanel.gridy = 1;
        runSLiMFinderPanel.add(slimprobOptionsPanel,
                gbc_slimprobOptionsPanel);
        GridBagLayout gbl_slimprobOptionsPanel = new GridBagLayout();
        gbl_slimprobOptionsPanel.columnWidths = new int[] { 0, 0 };
        gbl_slimprobOptionsPanel.rowHeights = new int[] { 0, 0, 0, 0 };
        gbl_slimprobOptionsPanel.columnWeights = new double[] { 1.0,
                Double.MIN_VALUE };
        gbl_slimprobOptionsPanel.rowWeights = new double[] { 1.0, 0.0, 1.0,
                Double.MIN_VALUE };
        slimprobOptionsPanel.setLayout(gbl_slimprobOptionsPanel);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 5, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        slimprobOptionsPanel.add(panel, gbc_panel);
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
        slimprobOptionsPanel.add(motifLabel, gbc_motifLabel);

        motifTextArea = new JTextArea();
        GridBagConstraints gbc_textArea = new GridBagConstraints();
        gbc_textArea.insets = new Insets(0, 0, 0, 5);
        gbc_textArea.fill = GridBagConstraints.BOTH;
        gbc_textArea.gridx = 0;
        gbc_textArea.gridy = 2;
        slimprobOptionsPanel.add(motifTextArea, gbc_textArea);

        JLabel idLabel = new JLabel("Run ID:");
        GridBagConstraints gbc1_motifLabel = new GridBagConstraints();
        gbc1_motifLabel.anchor = GridBagConstraints.WEST;
        gbc1_motifLabel.insets = new Insets(0, 0, 5, 5);
        gbc1_motifLabel.gridx = 0;
        gbc1_motifLabel.gridy = 3;
        slimprobOptionsPanel.add(idLabel, gbc1_motifLabel);

        idTextArea = new JTextArea();
        GridBagConstraints gbc1_textArea = new GridBagConstraints();
        gbc1_textArea.insets = new Insets(0, 0, 0, 5);
        gbc1_textArea.fill = GridBagConstraints.BOTH;
        gbc1_textArea.gridx = 0;
        gbc1_textArea.gridy = 4;
        slimprobOptionsPanel.add(idTextArea, gbc1_textArea);
    }
}