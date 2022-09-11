public class Planet {
    private String name;
    private final int x;
    private final int y;
    private Probe[][] area;
    private String name;

    public Planet(int x, int y, Probe[][] area, String name) {
        this.x = x;
        this.y = y;
        this.area = area;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Probe[][] getArea() {
        return area;
    }
}
