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
    public Planet getPlanet(String name) {
        Planet actualPlanet = null;
        for (int i = 0; i < planetList.size(); i++) {
            if (planetList.get(i).getName().equals(name)) {
                actualPlanet = planetList.get(i);
            }
        }
        return actualPlanet;
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
        return addPlanetToList(planetName);
    }

    public Planet addPlanetToList(String planetName) {
        Planet planet;
        for (Planet p : planetList) {
            if (p.getName().equals(planetName)) {
                return p;
            }
        }

        System.out.print("planet x axis (only integers): ");
        int planetX = s.nextInt();
        System.out.print("planet y axis (only integers): ");
        int planetY = s.nextInt();
        planet = new Planet(planetName, planetX, planetY);
        planetList.add(planet);
        return planet;
    }

}
