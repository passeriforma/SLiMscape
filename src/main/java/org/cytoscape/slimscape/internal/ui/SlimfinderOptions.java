package org.cytoscape.slimscape.internal.ui;

/**
 *
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

    public boolean isUsingConservation() {
        return consmask;
    }

    public void setUsingConservation(boolean usingConservation) {
        this.consmask = usingConservation;
    }

    public void setFeaturemask(boolean featuremask) {
        this.featuremask = featuremask;
    }

    public void setDismask(boolean dismaskBoolean) {
        this.dismask = dismaskBoolean;
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

    public void setConsmask(boolean consmask) {
        this.consmask = consmask;
    }

    public void setCustomParameters(String customParameters) {
        this.customParameters = customParameters;
    }

    private String booleanToString(boolean input) {
        if (input == true) {
            return "T";
        } else if (input == false) {
            return "F";
        }
        return null;
    }
}
