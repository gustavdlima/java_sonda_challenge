import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CommandLineInterface CLI = new CommandLineInterface();
        MilkWay milkWay = new MilkWay("milkWay", new ArrayList<>());
        Planet currentPlanet = null;
        Probe currentProbe = null;
        List<Probe> probes = new ArrayList<>();

        CLI.systemMenu();
        while (true) {
            System.out.print(": ");
            String option = sc.next();
            if (option.equals("-newplanet")) {
                milkWay.newPlanet();
                System.out.println();
                continue;
            }
            if (option.equals("-listplanets")) {
                milkWay.printPlanets();
                System.out.println();
                System.out.println("Choose the planet (Ex: mars): ");
                option = sc.next();
                currentPlanet = milkWay.getPlanet(option);
                continue ;
            }
            if (option.equals("-newprobe")) {
                currentProbe = CLI.createNewProbe();
                System.out.println("enter the command to move the probe (to change the direction 90° use R = right and L = left and M to move. Ex: LMRMLL):");
                String commands = sc.next();
                currentProbe.setCommands(commands);
                probes.add(currentProbe);
                continue ;
            }
            if (option.equals("-listprobes")) {
                CommandLineInterface.printAllProbes(probes);
                System.out.println("Choose the probe (Ex: sonda1): ");
                String probeName = sc.next();
                currentProbe = Probe.takeSelectedProbe(probeName, probes);
                currentPlanet = currentProbe.getPlanet();
                continue ;
            }
            if (option.equals("-startprobe")) {
                if (currentProbe == null) {
                    System.out.println("before start a probe you must choose a probe!");
                    continue ;
                }
                System.out.println("probe started!");
                currentProbe.moveProbe(currentProbe.getCommands(), currentProbe);
                continue ;
            }
            if (option.equals("-newprobecommand")) {
                if (currentProbe == null) {
                    System.out.println("before execute a command you must choose a probe!");
                    continue ;
                }
                String commands = sc.next();
                System.out.println("new commands added");
                currentProbe.setCommands(commands);
            }
            if (option.equals("-commands")) {
                CLI.availableCommands();
            }
            if (option.equals("-info")){
                if (currentProbe == null) {
                    System.out.println("before knowing the information you must choose a probe!");
                    continue ;
                }
                System.out.println("[probe]");
                System.out.println(currentProbe.getName() + " [" + currentProbe.getPosition() + "] facing " + currentProbe.getDirection() + " with the command: " + currentProbe.getCommands());
                if (currentPlanet == null) {
                    System.out.println("before knowing the information you must choose a planet!");
                    continue ;
                }
                System.out.println("[planet]");
                System.out.println(currentPlanet.getName() + " [" + currentPlanet.getX() + " ," + currentPlanet.getY() + "]");

            }
            if (option.equals("-exit"))
                break ;
        }
    }

}
