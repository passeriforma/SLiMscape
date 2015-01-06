package org.cytoscape.slimscape.internal.ui;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/*
 * @author: Kevin O'Brien
 */
public class SlimsearchOptionsPanel extends JPanel {

    private JTextField probabililtyCutoffTextField;
    private JCheckBox disorderMakingCheckBox;
    private JTextArea customParametersTextArea;
    private JCheckBox conservationCheckBox;

    public SlimsearchOptionsPanel () {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{450, 0};
        gridBagLayout.rowHeights = new int[]{97, 93, 97, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JPanel maskingPanel = new JPanel();
        maskingPanel.setBorder(new TitledBorder(null, "Masking", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GridBagConstraints gbc_maskingPanel = new GridBagConstraints();
        gbc_maskingPanel.fill = GridBagConstraints.BOTH;
        gbc_maskingPanel.insets = new Insets(0, 0, 5, 0);
        gbc_maskingPanel.gridx = 0;
        gbc_maskingPanel.gridy = 0;
        add(maskingPanel, gbc_maskingPanel);
        GridBagLayout gbl_maskingPanel = new GridBagLayout();
        gbl_maskingPanel.columnWidths = new int[]{0, 0, 0};
        gbl_maskingPanel.rowHeights = new int[]{0, 0, 0};
        gbl_maskingPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        gbl_maskingPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        maskingPanel.setLayout(gbl_maskingPanel);

        this.disorderMakingCheckBox = new JCheckBox("Disorder Masking");
        this.disorderMakingCheckBox.setSelected(true);
        GridBagConstraints gbc_disorderMakingCheckBox = new GridBagConstraints();
        gbc_disorderMakingCheckBox.anchor = GridBagConstraints.WEST;
        gbc_disorderMakingCheckBox.insets = new Insets(0, 0, 5, 5);
        gbc_disorderMakingCheckBox.gridx = 0;
        gbc_disorderMakingCheckBox.gridy = 0;
        maskingPanel.add(this.disorderMakingCheckBox, gbc_disorderMakingCheckBox);

        conservationCheckBox = new JCheckBox("Conservation Masking");
        conservationCheckBox.setSelected(true);
        GridBagConstraints gbc_conservationCheckBox = new GridBagConstraints();
        gbc_conservationCheckBox.anchor = GridBagConstraints.WEST;
        gbc_conservationCheckBox.insets = new Insets(0, 0, 0, 5);
        gbc_conservationCheckBox.gridx = 0;
        gbc_conservationCheckBox.gridy = 1;
        maskingPanel.add(conservationCheckBox, gbc_conservationCheckBox);

        JPanel SLiMChance = new JPanel();
        SLiMChance.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "SLiM Chance",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        GridBagConstraints gbc_SLiMChance = new GridBagConstraints();
        gbc_SLiMChance.fill = GridBagConstraints.BOTH;
        gbc_SLiMChance.insets = new Insets(0, 0, 5, 0);
        gbc_SLiMChance.gridx = 0;
        gbc_SLiMChance.gridy = 1;
        add(SLiMChance, gbc_SLiMChance);
        GridBagLayout gbl_SLiMChance = new GridBagLayout();
        gbl_SLiMChance.columnWidths = new int[]{0, 0, 0};
        gbl_SLiMChance.rowHeights = new int[]{0, 0};
        gbl_SLiMChance.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_SLiMChance.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        SLiMChance.setLayout(gbl_SLiMChance);

        JLabel labelProbabilityCutOff = new JLabel("Probability cut-off:");
        labelProbabilityCutOff.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblProbabilityCutOff = new GridBagConstraints();
        gbc_lblProbabilityCutOff.anchor = GridBagConstraints.EAST;
        gbc_lblProbabilityCutOff.insets = new Insets(0, 0, 0, 5);
        gbc_lblProbabilityCutOff.gridx = 0;
        gbc_lblProbabilityCutOff.gridy = 0;
        SLiMChance.add(labelProbabilityCutOff, gbc_lblProbabilityCutOff);

        probabililtyCutoffTextField = new JTextField();
        probabililtyCutoffTextField.setHorizontalAlignment(SwingConstants.LEFT);
        probabililtyCutoffTextField.setText("1");
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.anchor = GridBagConstraints.WEST;
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 0;
        SLiMChance.add(probabililtyCutoffTextField, gbc_textField);
        probabililtyCutoffTextField.setColumns(10);

        JPanel customPanel = new JPanel();
        customPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Custom Parameters", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        GridBagConstraints gbc_customPanel = new GridBagConstraints();
        gbc_customPanel.fill = GridBagConstraints.BOTH;
        gbc_customPanel.gridx = 0;
        gbc_customPanel.gridy = 2;
        add(customPanel, gbc_customPanel);
        GridBagLayout gbl_customPanel = new GridBagLayout();
        gbl_customPanel.columnWidths = new int[]{0, 0};
        gbl_customPanel.rowHeights = new int[]{0, 0};
        gbl_customPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_customPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        customPanel.setLayout(gbl_customPanel);

        customParametersTextArea = new JTextArea();
        GridBagConstraints gbc_customParametersTextArea = new GridBagConstraints();
        gbc_customParametersTextArea.fill = GridBagConstraints.BOTH;
        gbc_customParametersTextArea.gridx = 0;
        gbc_customParametersTextArea.gridy = 0;
        customPanel.add(customParametersTextArea, gbc_customParametersTextArea);
    }

    public SlimsearchOptions getSLiMSearchOptions() {
        SlimsearchOptions slimsearchOptions = new SlimsearchOptions();
        slimsearchOptions.setDismask(disorderMakingCheckBox.isEnabled());
        slimsearchOptions.setCutoff(Double.parseDouble(probabililtyCutoffTextField.getText()));
        slimsearchOptions.setCustomParameters(customParametersTextArea.getText());
        slimsearchOptions.setConsmask(conservationCheckBox.isSelected());
        return slimsearchOptions;
    }
}
