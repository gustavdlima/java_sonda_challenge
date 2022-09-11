public class Planet{
    private String name;
    private final int x;
    private final int y;
    private Probe[][] area;

    public Planet(String name, int x, int y) {
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

    public void setArea(Probe[][] area) {
        this.area = area;
    }

    public Position transformCartesianPlaneToMatrix(Position position, int planetY) {
        System.out.print("old  " + position);
        position.setY((planetY - position.getY()));
        position.setX(position.getX() - 1);
        System.out.println(" new = " + position);
        return position;
    }

    public Position transformMatrixToCartesianPlane(Position position, int planetY) {
        System.out.print("old  " + position);
        if (position.getY() == 0 )
            position.setY(planetY);
        else
            position.setY(planetY - position.getY());
        position.setX(position.getX() + 1);
        System.out.println(" new = " + position);
        return position;
    }
}
