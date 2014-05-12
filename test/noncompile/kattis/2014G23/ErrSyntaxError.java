//EXT:!CNE
/* Various syntax errors */

/* This is a comment */
// This is a comment as well
// // wow /; /f/ so valid/d.s/dfj3  ;'';[äpåäöäåkl j;;l;
/*
 * Much JavaDoc comment
 *
 */
class StarClass {
    public static void main(String[] main) {
        System.out.println(new _test().m1());
    }
}

class _test {
    public int m1() {
        int assign = 2; // Assignment in declaration

        return 0;
    }

    /**/
    /******/
    /******/

    public void Voider() { // Void doesn't exist
        
    }

    public int Inter() {
        // No return value
    }

    public boolean Blocker() {
        while(1 != 1) {
            int i; // Invalid without extension
            i = i + i;
        }
        return false;
    }

    public Invalider() { // No return type
        return 2;
    }

    public boolean First() { // Nested methods are not allowed
        public int Second() {
            return 0;
        }
        return false;
    }

    public int xxx() {
        return 0;
        return 2; // Invalid double return
    }

    public int me() {
        return 2;
    }

    public int implThis() {
        return me(); // Implicit this
    }
}


// Method outside class
public int Outside(boolean yes) {
    return 123;
}

///* /* */ */ // Valid
/* /* */ */ // Invalid nested comment
