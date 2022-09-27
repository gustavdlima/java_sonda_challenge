import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProbeTest {

    @Test
    void moveProbeToX3Y4(){
        Probe testProbe = new Probe("test", new Planet("mars", 5, 5), new Position(3, 3), "LMRMM", Direction.N);
        testProbe.moveProbe("M", testProbe);
        System.out.println("(" + testProbe.getPosition() + ")");
    }

    @Test
    void moveProbeToRight(){
        Probe testProbe = new Probe("testRight", new Planet("mars", 5, 5), new Position(3, 3), "LMRMM", Direction.N);
        testProbe.moveProbe("R", testProbe);
        System.out.println(testProbe.getName() + "= " + testProbe.getDirection());
    }

    @Test
    void moveProbeToLeft(){
        Probe testProbe = new Probe("testLeft", new Planet("mars", 5, 5), new Position(3, 3), "LMRMM", Direction.N);
        testProbe.moveProbe("L", testProbe);
        System.out.println(testProbe.getName() + "= " + testProbe.getDirection());
    }

    @Test
    void takeTestProbeInProbeList(){
        Probe testProbe = new Probe("test", new Planet("mars", 5, 5), new Position(3, 3), "LMRMM", Direction.N);
        List<Probe> probeList = new ArrayList<>();
        probeList.add(testProbe);
        System.out.println("probe = " + Probe.takeSelectedProbe("test", probeList).getName());
    }
}