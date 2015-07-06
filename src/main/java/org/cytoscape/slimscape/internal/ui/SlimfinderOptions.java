package org.cytoscape.slimscape.internal.ui;

/*
 * @author: Kevin O'Brien
 */
public class SlimfinderOptions {

    private boolean dismask;
    private int cutoff;
    private String length;
    private boolean consmask;
    boolean featuremask;
    boolean ambiguity;
    int wildcard;

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

    public void setAmbiguity(boolean ambiguity) {
        this.ambiguity = ambiguity;
    }

    public boolean getAmbiguity() {
        return ambiguity;
    }

    public int getWildcard() {
        return wildcard;
    }

    public void setWidlcard(int wildcard) {
        this.wildcard = wildcard;
    }

    public int getCutoff() {
        return cutoff;
    }

    public void setCutoff(int cutoff) {
        this.cutoff = cutoff;
    }

    public void setCustomParameters(String customParameters) {
        this.customParameters = customParameters;
    }

    public String getCustomParameters() {
        return customParameters;
    }
}
