package org.cytoscape.slimscape.internal.ui;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SlimsearchOptionsPanel extends JPanel {

    private JTextField probabililtyCutoffTextField;
    private JCheckBox disorderMakingCheckBox;
    private JTextArea customParametersTextArea;
    private JCheckBox conservationCheckBox;

    public SlimsearchOptionsPanel () {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel maskingPanel = new JPanel();
        maskingPanel.setBorder(new TitledBorder(null, "Masking", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        maskingPanel.setPreferredSize(new Dimension(400, 100));
        maskingPanel.setMaximumSize(new Dimension(400, 100));
        maskingPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        disorderMakingCheckBox = new JCheckBox("Disorder Masking");
        disorderMakingCheckBox.setSelected(true);
        disorderMakingCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        maskingPanel.add(disorderMakingCheckBox);

        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(350, 2));
        spacer.setMaximumSize(new Dimension(350, 2));
        maskingPanel.add(spacer);

        conservationCheckBox = new JCheckBox("Conservation Masking");
        conservationCheckBox.setSelected(true);
        conservationCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        maskingPanel.add(conservationCheckBox);

        this.add(maskingPanel);

        JPanel SLiMChance = new JPanel();
        SLiMChance.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "SLiM Chance", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        SLiMChance.setAlignmentX(Component.LEFT_ALIGNMENT);;
        SLiMChance.setPreferredSize(new Dimension(400, 100));
        SLiMChance.setMaximumSize(new Dimension(400, 100));


        JLabel labelProbabilityCutOff = new JLabel("Probability cut-off:");
        labelProbabilityCutOff.setHorizontalAlignment(SwingConstants.LEFT);
        SLiMChance.add(labelProbabilityCutOff);

        probabililtyCutoffTextField = new JTextField();
        probabililtyCutoffTextField.setHorizontalAlignment(SwingConstants.LEFT);
        probabililtyCutoffTextField.setText("1");
        SLiMChance.add(probabililtyCutoffTextField);
        probabililtyCutoffTextField.setColumns(10);

        this.add(SLiMChance);

        JPanel customPanel = new JPanel();
        customPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Custom Parameters", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        customPanel.setPreferredSize(new Dimension(400, 100));
        customPanel.setMaximumSize(new Dimension(400, 100));
        customPanel.setAlignmentX(Component.LEFT_ALIGNMENT);


        customParametersTextArea = new JTextArea(4, 34);
        customPanel.add(customParametersTextArea);

        this.add(customPanel);
    }
}
