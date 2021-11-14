
public class Position {
    private int x;
    private int y;

    public Position(int x, int y ){
        this.x = x;
        this.y = y;
    }
    public void setX(int x) {this.x = x;}

    public void setY(int y) {this.y = y;}

    public int getX() {return this.x;}

    public int getY() {return this.y;}

    @Override
    public boolean equals(Object o){
        if (this == o) return true;

        if(o == null) return false;

        if(getClass() != o.getClass()) return false;

        Position p = (Position) o;
        return this.x == p.getX() && this.y == p.getY();
    }
}

