import java.util.List;

public class MilkWay extends Galaxy{
    public MilkWay(String name, List<Planet> planetList) {
        super(name, planetList);
        newPlanet("mars", 5, 5);
        newPlanet("earth", 10, 10);
    }

    public void printPlanets() {
        System.out.println("solar system planets: ");
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

}
