package org.cytoscape.slimscape.internal.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/*
 * @author: Kevin O'Brien
 */
public class SlimfinderOptionsPanel extends JPanel {
    private JTextField lengthCutoffTextField = null;
    private JCheckBox disorderMakingCheckBox = null;
    private JTextArea customParametersTextArea = null;
    private JCheckBox conservationMakingCheckBox;
    private JCheckBox featureMaskingCheckBox = null;
    private JCheckBox ambiguityCheckBox = null;
    private JTextField wildcardCutoffTextField = null;

    public SlimfinderOptionsPanel() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{450, 0};
        gridBagLayout.rowHeights = new int[]{97, 93, 97, 97, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
        gbl_maskingPanel.rowHeights = new int[]{0, 0, 0, 0};
        gbl_maskingPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        gbl_maskingPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        maskingPanel.setLayout(gbl_maskingPanel);

        this.disorderMakingCheckBox = new JCheckBox("Disorder Masking");
        disorderMakingCheckBox.setSelected(true);
        GridBagConstraints gbc_disorderMakingCheckBox = new GridBagConstraints();
        gbc_disorderMakingCheckBox.anchor = GridBagConstraints.WEST;
        gbc_disorderMakingCheckBox.insets = new Insets(0, 0, 5, 5);
        gbc_disorderMakingCheckBox.gridx = 0;
        gbc_disorderMakingCheckBox.gridy = 0;
        maskingPanel.add(disorderMakingCheckBox, gbc_disorderMakingCheckBox);

        this.conservationMakingCheckBox = new JCheckBox("Conservation Masking");
        conservationMakingCheckBox.setSelected(false);
        GridBagConstraints gbc_conservationMakingCheckBox = new GridBagConstraints();
        gbc_conservationMakingCheckBox.insets = new Insets(0, 0, 5, 5);
        gbc_conservationMakingCheckBox.gridx = 0;
        gbc_conservationMakingCheckBox.gridy = 1;
        maskingPanel.add(conservationMakingCheckBox, gbc_conservationMakingCheckBox);

        this.featureMaskingCheckBox = new JCheckBox("Feature Masking");
        featureMaskingCheckBox.setSelected(false);
        GridBagConstraints gbc_featureMaskingCheckBox = new GridBagConstraints();
        gbc_featureMaskingCheckBox.anchor = GridBagConstraints.WEST;
        gbc_featureMaskingCheckBox.insets = new Insets(0, 0, 0, 5);
        gbc_featureMaskingCheckBox.gridx = 0;
        gbc_featureMaskingCheckBox.gridy = 2;
        maskingPanel.add(featureMaskingCheckBox, gbc_featureMaskingCheckBox);

        JPanel SLiMChance = new JPanel();
        SLiMChance.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "SLiMBuild",
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

        JLabel labelLengthCutoff = new JLabel("Maximum SLiM Length:");
        labelLengthCutoff.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblProbabilityCutOff = new GridBagConstraints();
        gbc_lblProbabilityCutOff.anchor = GridBagConstraints.WEST;
        gbc_lblProbabilityCutOff.insets = new Insets(0, 0, 0, 5);
        gbc_lblProbabilityCutOff.gridx = 0;
        gbc_lblProbabilityCutOff.gridy = 0;
        SLiMChance.add(labelLengthCutoff, gbc_lblProbabilityCutOff);

        this.lengthCutoffTextField = new JTextField();
        lengthCutoffTextField.setHorizontalAlignment(SwingConstants.LEFT);
        lengthCutoffTextField.setText("5");
        GridBagConstraints gbc_txtCons = new GridBagConstraints();
        gbc_txtCons.anchor = GridBagConstraints.WEST;
        gbc_txtCons.gridx = 1;
        gbc_txtCons.gridy = 0;
        SLiMChance.add(lengthCutoffTextField, gbc_txtCons);
        lengthCutoffTextField.setColumns(10);

        JLabel labelWildcardCutoff = new JLabel("Max. Consecutive Wildcards:");
        labelWildcardCutoff.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblWildcardCutOff = new GridBagConstraints();
        gbc_lblWildcardCutOff.anchor = GridBagConstraints.WEST;
        gbc_lblWildcardCutOff.insets = new Insets(0, 0, 0, 5);
        gbc_lblWildcardCutOff.gridx = 0;
        gbc_lblWildcardCutOff.gridy = 1;
        SLiMChance.add(labelWildcardCutoff, gbc_lblWildcardCutOff);

        this.wildcardCutoffTextField = new JTextField();
        wildcardCutoffTextField.setHorizontalAlignment(SwingConstants.LEFT);
        wildcardCutoffTextField.setText("2");
        GridBagConstraints gbc_txtWild = new GridBagConstraints();
        gbc_txtWild.anchor = GridBagConstraints.WEST;
        gbc_txtWild.gridx = 1;
        gbc_txtWild.gridy = 1;
        SLiMChance.add(wildcardCutoffTextField, gbc_txtWild);
        wildcardCutoffTextField.setColumns(10);

        this.ambiguityCheckBox = new JCheckBox("Ambiguous motifs");
        ambiguityCheckBox.setSelected(true);
        GridBagConstraints gbc_ambiguousCheckBox = new GridBagConstraints();
        gbc_ambiguousCheckBox.anchor = GridBagConstraints.WEST;
        gbc_ambiguousCheckBox.insets = new Insets(0, 0, 0, 5);
        gbc_ambiguousCheckBox.gridx = 0;
        gbc_ambiguousCheckBox.gridy = 2;
        SLiMChance.add(ambiguityCheckBox, gbc_ambiguousCheckBox);

        JPanel customPanel = new JPanel();
        customPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Custom Parameters",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
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

        this.customParametersTextArea = new JTextArea();
        GridBagConstraints gbc_customParametersTextArea = new GridBagConstraints();
        gbc_customParametersTextArea.fill = GridBagConstraints.BOTH;
        gbc_customParametersTextArea.gridx = 0;
        gbc_customParametersTextArea.gridy = 0;
        customPanel.add(customParametersTextArea, gbc_customParametersTextArea);
    }

    public SlimfinderOptions getSLiMFinderOptions()
    {
        SlimfinderOptions slimfinderOptions = new SlimfinderOptions();
        slimfinderOptions.setDismask(disorderMakingCheckBox.isSelected());
        slimfinderOptions.setConsmask(conservationMakingCheckBox.isSelected());
        slimfinderOptions.setFeaturemask(featureMaskingCheckBox.isSelected());
        slimfinderOptions.setCutoff(Integer.parseInt(lengthCutoffTextField.getText()));
        slimfinderOptions.setCustomParameters(customParametersTextArea.getText());
        slimfinderOptions.setAmbiguity(ambiguityCheckBox.isSelected());
        slimfinderOptions.setWidlcard(Integer.parseInt(wildcardCutoffTextField.getText()));
        return slimfinderOptions;
    }

}
