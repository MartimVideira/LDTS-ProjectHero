public class Wall {
    private Position position;
    private String representation;
    public Wall(int x, int y){this.position = new Position(x,y);}

    public Wall(Position pos){ this.position = pos;}

    public void setPosition(Position pos) { this.position = pos;}

    public Position getPosition() {return this.position;}

}
