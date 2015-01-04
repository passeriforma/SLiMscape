package org.cytoscape.slimscape.internal.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SlimsearchRunPanel extends JPanel {
    
    public SlimsearchRunPanel () {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setMaximumSize(new Dimension(400, 400));
        this.setPreferredSize(new Dimension(400, 400));

        // Creates label and button required to run SLiMSearch
        this.setBorder(new TitledBorder(null, "Run SLiMSearch",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        JButton runSLiMSearchButton = new JButton("RunSLiMSearch");
        runSLiMSearchButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        // TODO: Add action listener
        this.add(runSLiMSearchButton);

        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(350, 4));
        spacer.setMaximumSize(new Dimension(350, 4));
        this.add(spacer);

        // Creates panel for input of motifs to search for
        JPanel slimSearchOptionsPanel = new JPanel();
        slimSearchOptionsPanel.setBorder(new TitledBorder(new LineBorder(
                new Color(184, 207, 229)), "Parameters", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(51, 51, 51)));
        slimSearchOptionsPanel.setPreferredSize(new Dimension(400, 100));
        slimSearchOptionsPanel.setMaximumSize(new Dimension(400, 100));
        this.add(slimSearchOptionsPanel);

        JPanel panel = new JPanel();
        slimSearchOptionsPanel.add(panel);

        JLabel motifLabel = new JLabel("Motifs:");
        slimSearchOptionsPanel.add(motifLabel);

        JTextArea motifTextArea = new JTextArea(1, 15);
        slimSearchOptionsPanel.add(motifTextArea);
    }
}
