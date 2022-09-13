import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Probe actualProbe = null;
        Planet actualPlanet = null;
        Scanner s = new Scanner(System.in);
        List<Probe> probeList = new ArrayList<>();
        MilkWay milkWay = new MilkWay("milkWay", new ArrayList<>());

        Probe sonda1 = new Probe("sonda1", milkWay.getPlanet("mars"), new Position(1,2), "LMLMLMLMM", Direction.N);
        probeList.add(sonda1);
        Probe sonda2 = new Probe("sonda2", milkWay.getPlanet("earth"), new Position(3,3), "MMRMMRMRRML", Direction.E);
        probeList.add(sonda2);

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

        while (true) {
            String input = s.next();
            if (input.equals("-newplanet")) {
                actualPlanet = milkWay.newPlanet();
                System.out.println();
                continue ;
            }
            if (input.equals("-listplanets")) {
                milkWay.printPlanets();
                System.out.println();
                System.out.println("Choose the planet (Ex: mars): ");
                input = s.next();
                actualPlanet = milkWay.getPlanet(input);
                continue ;
            }
            if (input.equals("-newprobe")) {
                if (actualPlanet == null) {
                    System.out.println("before creating a new probe you must to choose a planet!");
                    continue ;
                }
                actualProbe = Probe.newProbe(actualPlanet);
                System.out.println("enter the command to move the probe (to change the direction 90° use R = right and L = left and M to move. Ex: LMRMLL):");
                actualProbe.setCommands(s.next());
                probeList.add(actualProbe);
                continue ;
            }
            if (input.equals("-listprobes")) {
                Probe.printAllProbes(probeList);
                System.out.println("Choose the probe (Ex: sonda1): ");
                input = s.next();
                actualProbe = Probe.takeSelectedProbe(input, probeList);
                actualPlanet = actualProbe.getPlanet();
                continue ;
            }
            if (input.equals("-startprobe")) {
                if (actualProbe == null) {
                    System.out.println("before start a probe you must choose a probe!");
                    continue ;
                }
                actualProbe.executeCommand(actualProbe);
                continue ;
            }
            if (input.equals("-newprobecommand")) {
                if (actualProbe == null) {
                    System.out.println("before execute a command you must choose a probe!");
                    continue ;
                }
                input = s.next();
                actualProbe.setCommands(input);
            }
            if (input.equals("-commands")) {
                System.out.print("  -commands");
                System.out.println("              Show all commands");
                System.out.print("  -newplanet");
                System.out.println("              Create a new planet");
                System.out.print("  -listplanets");
                System.out.println("            Show all available planets");
                System.out.print("  -newprobe");
                System.out.println("               Create a new probe");
                System.out.print("  -listprobes");
                System.out.println("             Show all available probes");
                System.out.print("  -executeprobe");
                System.out.println("           Start the probe");
                System.out.print("  -newprobecommand");
                System.out.println("        Gives a new command to the selected probe");
                System.out.println();
            }
            if (input.equals("-info")){
                if (actualProbe == null) {
                    System.out.println("before knowing the information you must choose a probe!");
                    continue ;
                }
                System.out.println("[probe]");
                System.out.println(actualProbe.getName() + " [" +actualProbe.getPosition() + "] facing " + actualProbe.getDirection() + " with the command: " + actualProbe.getCommands());
                if (actualPlanet == null) {
                    System.out.println("before knowing the information you must choose a planet!");
                    continue ;
                }
                System.out.println("[planet]");
                System.out.println(actualPlanet.getName() + " [" + actualPlanet.getX() + " ," + actualPlanet.getY() + "]");

            }
            if (input.equals("-exit"))
                break ;
        }
    }

}
