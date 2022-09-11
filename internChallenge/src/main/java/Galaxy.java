import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Galaxy {
    protected String name;
    protected List<Planet> planetList;
    protected Scanner s;

    public Galaxy(String name, List<Planet> planetList) {
        this.name = name;
        this.planetList = planetList;
        s = new Scanner(System.in);
    }

    public String getName() {
        return name;
    }
    protected List<Planet> getPlanetList() {
        return planetList;
    }
    protected void addPlanet(Planet planet) {
        this.planetList.add(planet);
    }
    protected Planet newPlanet() {
        System.out.print("planet name: ");
        String planetName = s.next();
        System.out.print("planet x axis (only integers): ");
        int planetX = s.nextInt();
        System.out.print("planet y axis (only integers): ");
        int planetY = s.nextInt();
        planetList.add(new Planet(planetName, planetX, planetY));
    }
}
