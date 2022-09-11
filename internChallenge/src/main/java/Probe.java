public class Probe {
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
}
