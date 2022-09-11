import java.util.List;
import java.util.Scanner;

public class Probe {
    Scanner s;
    String      name;
    Planet      planet;
    Position    position;
    String      commands;
    Direction   direction;

    public Probe(String name, Planet planet, Position position, String commands, Direction direction) {
        this.name = name;
        this.planet = planet;
        this.position = position;
        this.commands = commands;
        this.direction = direction;
        s = new Scanner(System.in);
    }

    public String getName() {
        return name;
    }

    public Planet getPlanet() {
        return planet;
    }

    public Position getPosition() {
        return position;
    }

    public String getCommands() {
        return commands;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setCommands(String commands) {
        this.commands = commands;
    }

    public static Probe newProbe(Planet actualPlanet, List<Probe> probeList) {
        Probe temp;
        System.out.println("probe name:");
        String inputProbeName = s.next();
        System.out.println("probe landing position x (only integers):");
        int inputProbeX = s.nextInt();
        System.out.println("probe landing position y (only integers:");
        int inputProbeY = s.nextInt();
        System.out.println("probe landing direction N, E, S and W (wind rose)");
        String inputProbeDirection = s.next();
        temp = new Probe(inputProbeName, actualPlanet, new Position(inputProbeX, inputProbeY), null, Direction.valueOf(inputProbeDirection));
        probeList.add(temp);
        return temp;
    }

    public static void printAllProbes(List<Probe> probeList) {
        for (int i = 0; i < probeList.size(); i++) {
            System.out.println("- " + probeList.get(i).getName() + ";");
        }
    }

    public static Probe takeProbeInput(String name, List<Probe> probeList) {
        Probe actualProbe = null;
        for (int i = 0; i < probeList.size(); i++) {
            if (probeList.get(i).getName().equals(name)) {
                actualProbe = probeList.get(i);
            }
        }
        return actualProbe;
    }

    public void placeProbe(Probe probe, Position position) {
        Probe temp = planet.getArea()[position.getY()][position.getX()];

        setPosition(position);
    }
}
