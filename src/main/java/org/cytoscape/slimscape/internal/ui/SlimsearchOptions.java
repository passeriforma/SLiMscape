package org.cytoscape.slimscape.internal.ui;

/*
 * @author: Kevin O'Brien
 */
public class SlimsearchOptions {
    private boolean dismask;
    private double cutoff = 0.1;
    private String customParameters = "";
    private boolean consmask;

    public SlimsearchOptions() {
        super();
    }

    public boolean isDismask() {
        return dismask;
    }

    public void setDismask(boolean dismask) {
        this.dismask = dismask;
    }

    public double getCutoff() {
        return cutoff;
    }

    public boolean isUsingConservation() {
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
