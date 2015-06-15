package org.cytoscape.slimscape.internal.ui;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/*
 * @author: Kevin O'Brien
 */
public class SlimprobOptionsPanel extends JPanel {

    private JCheckBox disorderMakingCheckBox;
    private JTextArea customParametersTextArea;
    private JCheckBox conservationCheckBox;
    private JCheckBox featureMaskingCheckBox;

    public SlimprobOptionsPanel() {
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

        disorderMakingCheckBox = new JCheckBox("Disorder Masking");
        disorderMakingCheckBox.setSelected(true);
        GridBagConstraints gbc_disorderMakingCheckBox = new GridBagConstraints();
        gbc_disorderMakingCheckBox.anchor = GridBagConstraints.WEST;
        gbc_disorderMakingCheckBox.insets = new Insets(0, 0, 5, 5);
        gbc_disorderMakingCheckBox.gridx = 0;
        gbc_disorderMakingCheckBox.gridy = 0;
        maskingPanel.add(disorderMakingCheckBox, gbc_disorderMakingCheckBox);

        conservationCheckBox = new JCheckBox("Conservation Masking");
        disorderMakingCheckBox.setSelected(false);
        GridBagConstraints gbc_conservationCheckBox = new GridBagConstraints();
        gbc_conservationCheckBox.anchor = GridBagConstraints.WEST;
        gbc_conservationCheckBox.insets = new Insets(0, 0, 5, 5);
        gbc_conservationCheckBox.gridx = 0;
        gbc_conservationCheckBox.gridy = 1;
        maskingPanel.add(conservationCheckBox, gbc_conservationCheckBox);

        this.featureMaskingCheckBox = new JCheckBox("Feature Masking");
        featureMaskingCheckBox.setSelected(false);
        GridBagConstraints gbc_featureMaskingCheckBox = new GridBagConstraints();
        gbc_featureMaskingCheckBox.anchor = GridBagConstraints.WEST;
        gbc_featureMaskingCheckBox.insets = new Insets(0, 0, 5, 5);
        gbc_featureMaskingCheckBox.gridx = 0;
        gbc_featureMaskingCheckBox.gridy = 2;
        maskingPanel.add(featureMaskingCheckBox, gbc_featureMaskingCheckBox);

        JPanel customPanel = new JPanel();
        customPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Custom Parameters", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        GridBagConstraints gbc_customPanel = new GridBagConstraints();
        gbc_customPanel.fill = GridBagConstraints.BOTH;
        gbc_customPanel.gridx = 0;
        gbc_customPanel.gridy = 1;
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

    public SlimprobOptions getSlimprobOptions() {
        SlimprobOptions options = new SlimprobOptions();
        options.setDismask(disorderMakingCheckBox.isSelected());
        options.setConsmask(conservationCheckBox.isSelected());
        options.setCustomParameters(customParametersTextArea.getText());
        options.setFeaturemask(featureMaskingCheckBox.isSelected());
        return options;
    }
}
