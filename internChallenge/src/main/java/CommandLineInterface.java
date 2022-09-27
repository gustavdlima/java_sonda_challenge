import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandLineInterface {
    Scanner input = new Scanner(System.in);
    MilkWay milkWay = new MilkWay("milkWay", new ArrayList<>());

    public Probe createNewProbe() {
        System.out.println("Planet name:");
        String planetName = input.next();

        boolean hasPlanet = false;
        Planet planet = null;
        for (Planet p : milkWay.getPlanetList()) {
            if (p.getName().equals(planetName)) {
                hasPlanet = true;
                planet = p;
                break;
            }
        }
        if (!hasPlanet) planet = milkWay.addPlanetToList(planetName);

        System.out.println(System.lineSeparator() + "probe name:");
        String inputProbeName = input.next();
        int planetX = planet.getX();
        int planetY = planet.getY();

        int inputProbeX = 0;
        do {
            System.out.println("probe landing position x (only integers):");
            inputProbeX = input.nextInt();
            if (inputProbeX > planetX)
                System.out.println("position X larger than possible (máx: " + planetX + "), try again.");
        } while(inputProbeX > planetX);

        int inputProbeY = 0;
        do {
            System.out.println("probe landing position y (only integers:");
            inputProbeY = input.nextInt();
            if (inputProbeY > planetY)
                System.out.println("position Y larger than possible (máx: " + planetY + "), try again.");
        } while(inputProbeY > planetY);

        System.out.println("probe landing direction N, E, S and W (wind rose)");
        String inputProbeDirection = input.next();

        Probe probe = new Probe(inputProbeName, milkWay.getPlanet(planetName), new Position(inputProbeX, inputProbeY), null, Direction.valueOf(inputProbeDirection));
        probe.setPosition(probe.getPlanet().transformCartesianPlaneToMatrix(probe.getPosition(), probe.getPlanet().getY()));
        placeProbe(probe);
        probe.setPosition(probe.getPlanet().transformMatrixToCartesianPlane(probe.getPosition(), probe.getPlanet().getY()));
        return probe;
    }

    public static void removeProbe(Probe probe) {
        Position position = probe.getPosition();
        Probe[][] area = probe.getPlanet().getArea();
        area[position.getY()][position.getX()] = null;
    }

    public static void placeProbe(Probe probe) {
        Probe[][] areaTemp = probe.getPlanet().getArea();
        areaTemp[probe.getPosition().getY()][probe.getPosition().getX()] = probe;
    }

    public static void printAllProbes(List<Probe> probeList) {
        System.out.println();
        for (int i = 0; i < probeList.size(); i++) {
            System.out.println("- " + probeList.get(i).getName() + ";");
        }
    }

    public void systemMenu() {
        System.out.println();
        System.out.println("probe controller");
        System.out.println();
        System.out.println("[alfa version]");
        System.out.println("https://github.com/gustavdlima/java_sonda_challenge.git");
        System.out.println();
        System.out.println("Usage:");
        System.out.println("-command");
        System.out.println();
        System.out.println("Available Commands:");
        availableCommands();
    }

    public void availableCommands() {
        System.out.print("  -commands");
        System.out.println("               Show all commands");
        System.out.print("  -newplanet");
        System.out.println("              Create a new planet");
        System.out.print("  -listplanets");
        System.out.println("            Show all available planets");
        System.out.print("  -newprobe");
        System.out.println("               Create a new probe");
        System.out.print("  -listprobes");
        System.out.println("             Show all available probes");
        System.out.print("  -startprobe");
        System.out.println("             Start the probe");
        System.out.print("  -newprobecommand");
        System.out.println("        Gives a new command to the selected probe");
        System.out.print("  -info");
        System.out.println("                   Shows the information of the selected probe and planet");
        System.out.print("  -exit");
        System.out.println("                   Exit program");
        System.out.println();
    }

}
