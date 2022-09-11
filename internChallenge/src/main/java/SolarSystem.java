import java.util.List;
import java.util.Scanner;

public class SolarSystem extends Galaxy{
    public SolarSystem(String name, List<Planet> planetList) {
        super(name, planetList);
        newPlanet("Mars", 5, 5);
        newPlanet("Earth", 10, 10);
    }

    public void printPlanets() {
        System.out.println();
        System.out.println("Solar system planets: ");
        for (int i = 0; i < planetList.size(); i++)
            System.out.println("- " + planetList.get(i).getName() + " (" + (planetList.get(i).getX()) + "x" + (planetList.get(i).getY()) + ")" + ";");
    }

    public void newPlanet(String name, int x, int y) {
        planetList.add(new Planet(name, x, y));
    }

    @Override
    public String toString() {
        return "SolarSystem";
    }

    public Planet takePlanetInput(String name) {
        Planet actualPlanet = null;
        for (int i = 0; i < planetList.size(); i++) {
            if (planetList.get(i).getName().equals(name)) {
                actualPlanet = planetList.get(i);
            }
        }
        return actualPlanet;
    }
}
