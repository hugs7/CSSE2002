package nestedclass;

public class Outer {

    private int x;

    public Outer(int v) {
        x = v;
    }

    public void doit() {
        In i = new In(6);   // Can create an instance of an inner class
                            // here because we are running inside an outer
                            // instance (i.e. "this" exists).
        i.method();
    }

    public static void main(String args[]) {
        Outer out = new Outer(7);
        out.doit();
        // Not allowed to do this:
        // In i = new In(4);
        // We don't have an instance of Outer to be part of.

        // If for some reason we need to create an instance of an inner class
        // and link it to an existing outer instance, we use this syntax.
        In i = out.new In(4);
        i.method();
    }


    private class In {

        private int z;

        public In(int v) {
            z = v;
        }

        public void method() {
            System.out.println("" + z + " " + x); // problem if x was also declared in this class
            // To be more explicit, we would write:
            System.out.println("" + z + " " + Outer.this.x);
        }
    }

}
