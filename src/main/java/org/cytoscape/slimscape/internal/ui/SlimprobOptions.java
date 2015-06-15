package org.cytoscape.slimscape.internal.ui;

/*
 * @author: Kevin O'Brien
 */
public class SlimprobOptions {
    private boolean dismask;
    private String customParameters = "";
    private boolean consmask;
    private boolean featuremask;

    public SlimprobOptions() {
        super();
    }

    public boolean getDismask() {
        return dismask;
    }

    public void setDismask(boolean dismask) {
        this.dismask = dismask;
    }

    public boolean getConsmask() {
        return consmask;
    }

    public void setConsmask(boolean consmask) {
        this.consmask = consmask;
    }

    public boolean getFeaturemask () {
        return featuremask;
    }

    public void setFeaturemask(boolean featuremask) {
        this.featuremask = featuremask;
    }

    public String getCustomParameters() {
        return customParameters;
    }

    public void setCustomParameters(String customParameters) {
        this.customParameters = customParameters;
    }
}
