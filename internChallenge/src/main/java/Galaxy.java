import java.util.ArrayList;
import java.util.List;

public class Galaxy {
    private String name;
    private List<Planet> planetList;

    public void addPlanet(Planet planet) {
        this.planetList.add(planet);
    }

}
