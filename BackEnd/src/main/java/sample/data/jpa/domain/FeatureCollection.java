package sample.data.jpa.domain;

import java.util.List;

/**
 * Created by anoopagarwal on 10/19/16.
 */
public class FeatureCollection {

    private List<Feature> features;

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "FeatureCollection{" +
                "features=" + features.size() +
                '}';
    }
}
