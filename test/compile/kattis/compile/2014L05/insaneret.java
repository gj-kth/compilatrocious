class Factorial {
    public static void main(String [] str) {
        x foo;
        //y = new y().flip();
        foo = new x().pop();
        //  x:y:::x:::y:::x (type transfers)
        //foo = foo.x().x().y().y();
        foo = foo.bounce().bounce().bounce().y();
        //    foo        y.bounce          y.y()
        //      x.bounce           x.bounce
    }
}

class y {

    x x;
    y y;

    public boolean pop() {
        return false;
    }

    public x bounce() {
        return new x();
    }

    public x flop() {
        y x;
        x y;
        return this.x().y().x();
    }

    public x x() {
        return x;
    }

    public x y() {
        return x;
    }

    public y flip(x y) {
        return y.y();
    }

}

class x {

    public y bounce() {
        return new y();
    }

    public y x() {
        return new y();
    }

    public y y() {
        return new y();
    }

    public x z() {
        return this.bounce().bounce().bounce().y();
    }

    public x flop() {
        y x;
        x y;
        y = new x();
        return y.pop();
    }

    public boolean flip() {
        y x;
        x y;
        x = new y();
        return x.pop();
    }

    public x pop() {
        return this;
    }

}
