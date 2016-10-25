package sample.data.jpa.domain;

/**
 * Created by anoopagarwal on 10/19/16.
 */
public class Feature {

   private Properties properties;
   private Polygon geometry;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Polygon getGeometry() {
        return geometry;
    }

    public void setGeometry(Polygon geometry) {
        this.geometry = geometry;
    }
}
