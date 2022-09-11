import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Planet actualPlanet;

        SolarSystem solarSystem = new SolarSystem("solarSystem", new ArrayList<>());

        while (true) {
            System.out.println("choose the name of the planet you want or type -newplanet to create one.");
            solarSystem.printPlanets();
            String inputPlanetName = s.next();
            if (inputPlanetName.equals("-newplanet"))
                actualPlanet = solarSystem.newPlanet();
            else
                actualPlanet = solarSystem.takePlanetInput(inputPlanetName);
            System.out.println("choose the name of the probe you want or -newprobe to create a probe.");
            Ui.printAllProbes(cli.probeList);
            String inputProbe = s.next();
            if (inputProbe.equals("-newprobe")) {
                actualProbe = cli.newProbe(actualPlanet);
            }
            else if (inputProbe.equals("-probes")) {
                actualProbe = cli.takeProbeInput(inputProbe);
            }
            System.out.println("enter the command to move the probe (R and L to choose direction and M to move. Ex: LMRMLL):");
            actualProbe.setCommands(s.next());
            System.out.println("to run the probe commands type -startprobe.");
            String inputProbeStart = s.next();
            if (inputProbeStart.equals("-startprobe")) {
                actualProbe.executeCommand(actualProbe);
                cli.printProbeLocationAndPosition(actualProbe);
            }
            break;
        }
    }
}
