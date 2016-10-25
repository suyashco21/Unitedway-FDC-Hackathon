package sample.data.jpa.domain;

import java.util.List;
import java.math.*;

/**
 * Created by anoopagarwal on 10/19/16.
 */
public class Polygon {

    private List<List<BigDecimal[]>> coordinates;

    public List<List<BigDecimal[]>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<BigDecimal[]>> coordinates) {
        this.coordinates = coordinates;
    }
}
