class Program
{
  public static void main(String [] args)
  {
    Vec vec;
    Vec vec2;
    boolean changed;
    int increment;
    int count;
    boolean bdummy;
    int idummy;
    Vec vdummy;

    vec = new Vec();
    //vec.init();

    changed = vec.set(vec.getX(), vec.getY(), vec.getZ());

    System.out.println(vec.getX());
    System.out.println(vec.getY());
    System.out.println(vec.getZ());
    System.out.println(changed);

    vec2 = new Vec();
    bdummy = vec2.set(50, 50, 50);
    vdummy = vec2.rotate(0);

    System.out.println(vec2.norm());
    System.out.println(vec2.dist(vec));

    count = 0;
    increment = (5 + 5 + 1000*0);
    bdummy = vec.set(increment, increment, increment);
    while(0 < vec2.dist(vec))
    {
      bdummy = vec.set(vec.getX()+increment,
              vec.getY()+increment,
              vec.getZ()+increment);
      count = count + 1;
    }
    System.out.println(count);

    bdummy = vec.set(1, 2, 3);
    bdummy = vec2.set(3, 1, 2);
    while(!vec.equal(vec2))
    {
      vdummy = vec2.rotate(1);
    }
  }
}

class Vec
{
  int x;
  int y;
  int z;

  public boolean init()
  {
    x = 0;
    y = 0;
    z = 0;
    return true;
  }

  public boolean set(int xNew, int yNew, int zNew)
  {
    boolean changed;
    int idummy;

    changed = false;
    changed = !changed && (!(xNew < x)) && (!(x < xNew));
    changed = !changed && (!(yNew < y)) && (!(y < yNew));
    changed = !changed && (!(zNew < z)) && (!(z < zNew));

    idummy = this.setX(xNew);
    idummy = this.setY(yNew);
    idummy = this.setZ(zNew);

    return changed;
  }

  public int setX(int xNew)
  {
    x = xNew;
    return x;
  }

  public int setY(int yNew)
  {
    y = yNew;
    return y;
  }

  public int setZ(int zNew)
  {
    z = zNew;
    return z;
  }

  public int getX()
  {
    return x;
  }

  public int getY()
  {
    return y;
  }

  public int getZ()
  {
    return z;
  }

  public int norm()
  {
    //TODO
    return this.getX() * this.getX() + getY()*getY() + getZ()*getZ();
  }

  public int abs(int x)
  {
    int scalar;
    scalar = 1;
    scalar = scalar - (2*scalar);

    if(x < 0)
    {
      x = x * scalar;
    }
    else {}

    return x;
  }

  public int dist(Vec other)
  {
    int xDiff;
    int yDiff;
    int zDiff;
    
    xDiff = this.abs(other.getX() - this.getX());
    yDiff = this.abs(other.getY() - this.getY());
    zDiff = this.abs(other.getZ() - this.getZ());

    return xDiff*xDiff + yDiff*yDiff + zDiff*zDiff;
  }

  public Vec add(Vec other)
  {
    boolean bdummy;
    bdummy = this.set(this.getX()+other.getX(),
        this.getY()+other.getY(),
        this.getZ()+other.getZ());

    return this;
  }

  public Vec sub(Vec other)
  {
    boolean bdummy;
    bdummy = this.set(this.getX()-other.getX(),
        this.getY()-other.getY(),
        this.getZ()-other.getZ());

    return this;
  }

  public Vec mul(Vec other)
  {
    boolean bdummy;
    bdummy = this.set(this.getX()*other.getX(),
        this.getY()*other.getY(),
        this.getZ()*other.getZ());

    return this;
  }

  public Vec mulScalar(int scalar)
  {
    boolean bdummy;
    bdummy = this.set(this.getX()*scalar,
        this.getY()*scalar,
        this.getZ()*scalar);

    return this;
  }

  public Vec addScalar(int scalar)
  {
    boolean bdummy;
    bdummy = this.set(this.getX()+scalar,
        this.getY()+scalar,
        this.getZ()+scalar);

    return this;
  }

  public Vec subScalar(int scalar)
  {
    int tmp;
    tmp = 1;
    tmp = tmp - (2*tmp);
    return this.addScalar(scalar * tmp);
  }

  public Vec rotate(int k)
  {
    int tmp;

    if(!(k < 1) && !(1 < k))
    {
      tmp = x;
      x = y;
      y = z;
      z = tmp;
    }
    else {}

    if(!(k < 2) && !(2 < k))
    {
      //xyz -> zxy
      tmp = z;
      z = y;
      y = x;
      x = tmp;
    }
    else {}

    return this;
  }

  public boolean equal(Vec other)
  {
    boolean res; 

    res =
         (!(other.getX() < x)) && (!(x < other.getX()))
      && (!(other.getY() < y)) && (!(y < other.getY()))
      && (!(other.getZ() < z)) && (!(z < other.getZ()));

    return res;
  }
}
