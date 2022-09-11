public class Planet{
    private String name;
    private final int x;
    private final int y;
    private Probe[][] area;

    public Planet(String name, int x, int y, Planet planet) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.area = new Probe[y][x];
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
