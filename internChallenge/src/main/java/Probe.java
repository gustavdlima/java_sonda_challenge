import java.util.List;
import java.util.Scanner;

public class Probe {
    static Scanner s;
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
        System.out.println();
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

    public void placeProbe(Position position) {
        Probe temp = planet.getArea()[position.getY()][position.getX()];

        setPosition(position);
    }
    public Probe removeProbe(Position position) {
        Probe probeTemp = haveAProbe(position);
        if (probeTemp == null) {
            return null;
        } else {
            Probe[][] areaTemp = planet.getArea();
            areaTemp[position.getY()][position.getX()] = null;
            this.planet.setArea(areaTemp);
            return probeTemp;
        }
    }

    public Probe haveAProbe(Position position) {
        if (haveAProbe(position) == null)
            return null;
        else
            return planet.getArea()[position.getY()][position.getX()];
    }

    public void executeCommand() {
        String[] command;
        command = getCommands().split("");
        setPosition(planet.transformCartesianPlaneToMatrix(position, planet.getY()));
        placeProbe(position);
        for (int i = 0; i < commands.length(); i++) {
            if (command[i].equals("R"))
                setDirection(moveRight());
            if (command[i].equals("L"))
                setDirection(moveLeft());
            if (command[i].equals("M"))
                moveProbe();
        }
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

//    public void moveProbe() {
//        System.out.println("Antes = " + getPosition() + " Direcao = " + getDirection());
//        Position oldPosition = getPosition();
//        removeProbe(oldPosition);
//        if (getDirection() == Direction.N) {
//            if (getPosition().getY() == 0)
//                setPosition((planet.getY() - 1), getPosition().getX());
//            else
//                setPosition((getPosition().getY() - 1), getPosition().getX());
//        }
//        if (getDirection() == Direction.E) {
//            if (getPosition().getX() == (planet.getX() - 1))
//                setPosition(getPosition().getY(), 0);
//            else
//                setPosition(getPosition().getY(), getPosition().getX() + 1);
//        }
//        if (getDirection() == Direction.S) {
////            if (planet.getY() > (y + 1))
////                setPosition(planet.transformCartesianPlaneToMatrix(y, planet.getY()), x);
//            if (getPosition().getY() == (planet.getY()))
//                setPosition(0, getPosition().getX());
//            else {
//                if (getPosition().getY() == (planet.getY() - 1) || getPosition().getX() == (planet.getX() - 1))
//                    setPosition(getPosition().getY(), getPosition().getX());
//                else
//                    setPosition(getPosition().getY() + 1, getPosition().getX());
//            }
//        }
//        if (getDirection() == Direction.W) {
//            if (getPosition().getX() == 0)
//                setPosition(getPosition().getY(), planet.getX() - 1);
//            else {
//                setPosition(getPosition().getY(), getPosition().getX() - 1);
//            }
//        }
//        System.out.println("Depois = " + position + " Direcao = " + getDirection());
//        placeProbe(getPosition());
//    }
}
