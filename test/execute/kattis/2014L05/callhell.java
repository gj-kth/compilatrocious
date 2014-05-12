// EXT:SPILL

class Factorial {
    public static void main(String [] str) {

        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        int g;
        int h;
        int i;
        int j;
        int k;
        int l;
        int m;
        int n;
        int o;
        int p;
        int q;
        int r;
        int s;
        int t;
        int u;
        int v;

        int a2;
        int b2;
        int c2;
        int d2;
        int e2;
        int f2;
        int g2;
        int h2;
        int i2;
        int j2;
        int k2;
        int l2;
        int m2;
        int n2;
        int o2;
        int p2;
        int q2;
        int r2;
        int s2;
        int t2;
        int u2;
        int v2;

        monster M;

        a = 1;
        b = 2;
        c = 3;
        d = 4;
        e = 5;
        f = 6;
        g = 7;
        h = 8;
        i = 9;
        j = 10;
        k = 11;
        l = 12;
        m = 13;
        n = 14;
        o = 15;
        p = 16;
        q = 17;
        r = 18;
        s = 19;
        t = 20;
        u = 21;
        v = 22;

        a2 = 1;
        b2 = 2;
        c2 = 3;
        d2 = 4;
        e2 = 5;
        f2 = 6;
        g2 = 7;
        h2 = 8;
        i2 = 9;
        j2 = 10;
        k2 = 11;
        l2 = 12;
        m2 = 13;
        n2 = 14;
        o2 = 15;
        p2 = 16;
        q2 = 17;
        r2 = 18;
        s2 = 19;
        t2 = 20;
        u2 = 21;
        v2 = 22;

        a = a + b + a2;
        b = b + c + b2;
        c = c + d + c2;
        d = d + e + d2;
        e = e + f + e2;
        f = f + g + f2;
        g = g + h + g2;
        h = h + i + h2;
        i = i + j + i2;
        j = j + k + j2;
        k = k + l + k2;
        l = l + m + l2;
        m = m + n + m2;
        n = n + o + n2;
        o = o + p + o2;
        p = p + q + p2;
        q = q + r + q2;
        r = r + s + r2;
        s = s + t + s2;
        t = t + u + t2;
        u = u + v + u2;
        v = v + a + v2;

        System.out.println(v);

        M = new monster();

        System.out.println(M.devour(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v));
        System.out.println(M.devour(a2,b2,c2,d2,e2,f2,g2,h2,i2,j2,k2,l2,m2,n2,o2,p2,q2,r2,s2,t2,u2,v2));

    }
}

class monster {

    int aa;
    int bb;
    int cc;
    int dd;
    int ee;
    int ff;
    int gg;
    int hh;
    int ii;
    int jj;
    int kk;
    int ll;
    int mm;
    int nn;
    int oo;
    int pp;
    int qq;
    int rr;
    int ss;
    int tt;
    int uu;
    int vv;

    public int devour(int a, int b, int c, int d, int e, int f, int g, int h, int i, int j, int k, int l, int m, int n, int o, int p, int q, int r, int s, int t, int u, int v) {
        vv = a + vv;
        uu = b + uu;
        tt = c + tt;
        ss = d + ss;
        rr = e + rr;
        qq = f + qq;
        pp = g + pp;
        oo = h + oo;
        nn = i + nn;
        mm = j + mm;
        ll = k + ll;
        kk = l + kk;
        jj = m + jj;
        ii = n + ii;
        hh = o + hh;
        gg = p + gg;
        ff = q + ff;
        ee = r + ee;
        dd = s + dd;
        cc = t + cc;
        bb = u + bb;
        aa = v + aa;
        return vv+uu+tt+ss+rr+qq+pp+oo+nn+mm+ll+kk+jj+ii+hh+gg+ff+ee+dd+cc+bb+aa;
    }

}
