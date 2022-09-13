import java.util.List;
import java.util.Scanner;

public class Probe {
    static Scanner s;
    private String      name;
    private Planet      planet;
    private Position    position;
    private String      commands;
    private Direction   direction;

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

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    private void setPosition(Position position) {
    }

    public void setPosition(int x, int y) {
        this.position = new Position(x, y);
    }

    public void setCommands(String commands) {
        this.commands = commands;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public static Probe newProbe(Planet actualPlanet) {
        Probe temp;
        System.out.println();
        System.out.println("probe name:");
        String inputProbeName = s.next();
        System.out.println("probe landing position x (only integers):");
        int inputProbeX = s.nextInt();
        if (inputProbeX > actualPlanet.getX())
        {
            System.out.println("position X larger than possible (máx: " + actualPlanet.getX() + "), try again.");
            newProbe(actualPlanet);
        }
        System.out.println("probe landing position y (only integers:");
        int inputProbeY = s.nextInt();
        if (inputProbeY > actualPlanet.getY())
        {
            System.out.println("position Y larger than possible (máx: " + actualPlanet.getY() + "), try again.");
            newProbe(actualPlanet);
        }
        System.out.println("probe landing direction N, E, S and W (wind rose)");
        String inputProbeDirection = s.next();
        temp = new Probe(inputProbeName, actualPlanet, new Position(inputProbeX, inputProbeY), null, Direction.valueOf(inputProbeDirection));
        return temp;
    }

    public static void printAllProbes(List<Probe> probeList) {
        System.out.println();
        for (int i = 0; i < probeList.size(); i++) {
            System.out.println("- " + probeList.get(i).getName() + ";");
        }
    }

    public void printProbeLocationAndPosition() {
        System.out.print("The probe " + getName() + " points " + getDirection().toString() + " ");
        System.out.print( "and is located on the x=" + (getPosition().getX()) + " and y=" + (getPosition().getY()) + " ");
        System.out.println("axis of planet " + getPlanet().getName() + "!");
    }

    public static Probe takeSelectedProbe(String name, List<Probe> probeList) {
        Probe actualProbe = null;
        for (int i = 0; i < probeList.size(); i++) {
            if (probeList.get(i).getName().equals(name)) {
                actualProbe = probeList.get(i);
            }
        }
        return actualProbe;
    }

    public void placeProbe(Probe probe) {
        probe.position = position;
        probe.direction = direction;
        Probe[][] areaTemp = planet.getArea();
        areaTemp[position.getX()][position.getY()] = probe;
        planet.setArea(areaTemp);
    }

    public Probe removeProbe(Probe probe, Position position) {
        Probe probeTemp = planet.positionAreaIsFree(probe, position);
        if (probeTemp == null) {
            return null;
        } else {
            Probe[][] areaTemp = planet.getArea();
            areaTemp[position.getY()][position.getX()] = null;
            this.planet.setArea(areaTemp);
            return probeTemp;
        }
    }

    public boolean executeCommand(Probe probe) {
        String[] command;
        command = getCommands().split("");
        setPosition(planet.transformCartesianPlaneToMatrix(position, planet.getY()));
        placeProbe(probe);
        for (int i = 0; i < commands.length(); i++) {
            if (planet.positionAreaIsFree(probe, position) != null) {
                System.out.println("has an obstacle at the probe's home position (" + getPosition().getX() + ", " + (getPosition().getY()) + ")");
                return false;
            }
            if (command[i].equals("R"))
                setDirection(moveRight());
            if (command[i].equals("L"))
                setDirection(moveLeft());
            if (command[i].equals("M"))
                   moveProbe(probe);
        }
        setPosition(planet.transformMatrixToCartesianPlane(position, planet.getY()));
        return true;
    }

    private Direction moveRight() {
        if (getDirection().toString().equals("N"))
            return Direction.E;
        if (getDirection().toString().equals("E"))
            return Direction.S;
        if (getDirection().toString().equals("S"))
            return Direction.W;
        if (getDirection().toString().equals("W"))
            return Direction.N;
        return null;
    }

    private Direction moveLeft() {
        if (getDirection().toString().equals("N"))
            return Direction.W;
        if (getDirection().toString().equals("E"))
            return Direction.N;
        if (getDirection().toString().equals("S"))
            return Direction.E;
        if (getDirection().toString().equals("W"))
            return Direction.S;
        return null;
    }

    public void moveProbe(Probe probe) {
        Position oldPosition = getPosition();
        removeProbe(probe, oldPosition);
        if (getDirection() == Direction.N) {
            if (getPosition().getY() == 0)
                setPosition(getPosition().getX(), (planet.getY() - 1));
            else
                setPosition(getPosition().getX(), (getPosition().getY() - 1));
        }
        if (getDirection() == Direction.E) {
            if (getPosition().getX() == (planet.getX() - 1))
                setPosition(0, getPosition().getY());
            else
                setPosition((getPosition().getX() + 1), getPosition().getY());
        }
        if (getDirection() == Direction.S) {
            if (getPosition().getY() == (planet.getY() - 1))
                setPosition(getPosition().getX(), 0);
            else
                setPosition(getPosition().getX(), (getPosition().getY() + 1));
        }
        if (getDirection() == Direction.W) {
            if (getPosition().getX() == 0)
                setPosition((planet.getX() - 1), getPosition().getY());
            else {
                setPosition((getPosition().getX() - 1), getPosition().getY());
            }
        }
        placeProbe(probe);
    }
}
