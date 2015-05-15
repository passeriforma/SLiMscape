package org.cytoscape.slimscape.internal.ui;

/*
 * @author: Kevin O'Brien
 */
public class SlimprobOptions {
    private boolean dismask;
    private double cutoff = 0.1;
    private String customParameters = "";
    private boolean consmask;

    public SlimprobOptions() {
        super();
    }

    public boolean getDismask() {
        return dismask;
    }

    public void setDismask(boolean dismask) {
        this.dismask = dismask;
    }

    public boolean getConservation() {
        return consmask;
    }

    public void setConsmask(boolean consmask) {
        this.consmask = consmask;
    }

    public void setCutoff(double cutoff) {
        this.cutoff = cutoff;
    }

    public String getCustomParameters() {
        return customParameters;
    }

    public void setCustomParameters(String customParameters) {
        this.customParameters = customParameters;
    }
}
