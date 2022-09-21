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

    public void setPosition(Position position) {
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

    public void moveProbe(String command, Probe probe) {
        char[] commands = command.toCharArray();
        probe.setPosition(probe.getPlanet().transformCartesianPlaneToMatrix(probe.getPosition(), probe.getPlanet().getY()));
        for (int i = 0; i < commands.length; i++) {
            if (commands[i] == ('R'))
                probe.setDirection(moveRight(probe.getDirection()));
            if (commands[i] == ('L'))
                probe.setDirection(moveLeft(probe.getDirection()));
            if (commands[i] == ('M') && checkCollision(probe)) {
                moveProbeForward(probe);
            }
        }
        probe.setPosition(probe.getPlanet().transformMatrixToCartesianPlane(probe.getPosition(), probe.getPlanet().getY()));
    }

    private Direction moveRight(Direction direction) {
        if (direction == Direction.N)
            return Direction.E;
        if (direction == Direction.E)
            return Direction.S;
        if (direction == Direction.S)
            return Direction.W;
        if (direction == Direction.W)
            return Direction.N;
        return null;
    }

    private Direction moveLeft(Direction direction) {
        if (direction == Direction.N)
            return Direction.W;
        if (direction == Direction.E)
            return Direction.N;
        if (direction == Direction.S)
            return Direction.E;
        if (direction == Direction.W)
            return Direction.S;
        return null;
    }

    private boolean checkCollision(Probe probe) {
        Probe[][] area = probe.getPlanet().getArea();
        int probeY = probe.getPosition().getY();
        int probeX = probe.getPosition().getX();
        int planetY = probe.getPlanet().getY();
        int planetX = probe.getPlanet().getX();
        Direction direction = probe.getDirection();

        if (probeY == 0) probeY = planetY;
        if (direction.equals(Direction.N) && area[probeY - 1][probeX] != null) {
            System.out.println("Impossible to move to area " + probeX + " " + (probeY - 1));
            return false;
        }
        probeY = probe.getPosition().getY();

        if (probeY == planetY - 1) probeY = -1;
        if (direction.equals(Direction.S) && area[probeY + 1][probeX] != null) {
            System.out.println("Impossible to move to area");
            return false;
        }
        probeY = probe.getPosition().getY();

        if (probeX == planetX - 1) probeX = -1;
        if (direction.equals(Direction.E) && area[probeY][probeX + 1] != null) {
            System.out.println("Impossible to move");
            return false;
        }
        probeX = probe.getPosition().getX();

        if (probeX == 0) probeX = planetX;
        if (direction.equals(Direction.W) && area[probeY][probeX - 1] != null) {
            System.out.println("Impossible to move");
            return false;
        }
        return true;
    }

    private void moveProbeForward(Probe probe) {
        Direction direction = probe.getDirection();
        int probeY = probe.getPosition().getY();
        int probeX = probe.getPosition().getX();
        int planetY = probe.getPlanet().getY();
        int planetX = probe.getPlanet().getX();

        CommandLineInterface.removeProbe(probe);
        if (direction == Direction.N) {
            if (probeY == 0)
                probe.getPosition().setY(planetY - 1);
            else
                probe.getPosition().setY(probeY - 1);
        }
        if (direction == Direction.E) {
            if (probeX == planetX - 1)
                probe.getPosition().setX(0);
            else
                probe.getPosition().setX(probeX + 1);
        }
        if (direction == Direction.S) {
            if (probeY == planetY - 1)
                probe.getPosition().setY(0);
            else
                probe.getPosition().setY(probeY + 1);
        }
        if (direction == Direction.W) {
            if (probeX == 0)
                probe.getPosition().setX(planetX - 1);
            else {
                probe.getPosition().setX(probeX - 1);
            }
        }
        CommandLineInterface.placeProbe(probe);
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

}
