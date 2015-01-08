package org.cytoscape.slimscape.internal.ui;

/*
 * @author: Kevin O'Brien
 */
public class SlimfinderOptions {

    private boolean dismask;
    private double cutoff;
    private String walltime;
    private boolean consmask;
    boolean featuremask;

    private String customParameters = "";

    public SlimfinderOptions() {
        super();
    }

    public void setFeaturemask(boolean featuremask) {
        this.featuremask = featuremask;
    }

    public boolean getFeaturemask() {
        return featuremask;
    }

    public void setDismask(boolean dismaskBoolean) {
        this.dismask = dismaskBoolean;
    }

    public boolean getDismask() {
        return dismask;
    }

    public void setConsmask(boolean consmask) {
        this.consmask = consmask;
    }

    public boolean getConsmask() {
        return consmask;
    }

    public double getCutoff() {
        return cutoff;
    }

    public void setCutoff(double cutoff) {
        this.cutoff = cutoff;
    }

    public void setWalltime(String walltime) {
        this.walltime = walltime;
    }

    public String getWalltime() {
        return walltime;
    }

    public void setCustomParameters(String customParameters) {
        this.customParameters = customParameters;
    }

    public String getCustomParameters() {
        return customParameters;
    }
}
