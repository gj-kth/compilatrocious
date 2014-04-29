/*
 Test that classes, variables and method names are allowed to be the same.
*/

class Main0 {
    public static void main(String[] args) {

    }
}

class Links {
    Links Links;

    public Links Links() {
        Links = new Links();
        return Links;
    }
}
