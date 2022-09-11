import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Planet actualPlanet;
        Probe actualProbe;
        List<Probe> probeList = new ArrayList<>();
        SolarSystem solarSystem = new SolarSystem("solarSystem", new ArrayList<>());

        while (true) {
            System.out.println();
            System.out.println("choose the name of the planet you want or type -newplanet to create one.");
            solarSystem.printPlanets();
            String inputPlanetName = s.next();
            if (inputPlanetName.equals("-newplanet"))
                actualPlanet = solarSystem.newPlanet();
            else
                actualPlanet = solarSystem.takePlanetInput(inputPlanetName);

            Probe sonda1 = new Probe("sonda1", actualPlanet, new Position(1,2), "LMLMLMLMM", Direction.N);
            probeList.add(sonda1);
            Probe sonda2 = new Probe("sonda2", actualPlanet, new Position(3,3), "MMRMMRMRRML", Direction.E);
            probeList.add(sonda2);

            System.out.println();
            System.out.println("Choose the name of the probe you want or -newprobe to create a probe.");
            Probe.printAllProbes(probeList);
            String inputProbe = s.next();
            if (inputProbe.equals("-newprobe")) {
                actualProbe = Probe.newProbe(actualPlanet, probeList);
                System.out.println();
                System.out.println("enter the command to move the probe (R (Right) and L (Left) to change the direction 90° and M to move. Ex: LMRMLL):");
                actualProbe.setCommands(s.next());
            }
            else
                actualProbe = Probe.takeProbeInput(inputProbe, probeList);

            System.out.println("to run the probe commands type -startprobe.");
            String inputProbeStart = s.next();
            if (inputProbeStart.equals("-startprobe")) {
                actualProbe.executeCommand(actualProbe);
                cli.printProbeLocationAndPosition(actualProbe);
            }
            break;
        }
    }

    public Position transformCartesianPlaneToMatrix(Position position, int planetY) {
        System.out.print("old  " + position);
        position.setY((planetY - position.getY()));
        position.setX(position.getX() - 1);
        System.out.println(" new = " + position);
        return position;
    }
}
