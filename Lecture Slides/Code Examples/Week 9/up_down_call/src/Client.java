/*
 * We have up calls and down calls in programming languages with
 * inheritance hierarchy's.
 *
 * Downcall: calling a method in a subclass
 * Upcall: calling a method in a superclass
 */
public class Client {
    private static Supplier supplier = new SpecialSupplier();

    public static void main(String[] args) {
        System.out.println(supplier.perform());
        System.out.println(supplier.upCall());
    }
}


class Supplier {
    private String internal;
	
    public Supplier() {
        internal = "I'm a Supplier";
    }

    /* when perform() is called on an instance of SpecialSupplier, the downCall()
     * method SpecialSupplier will be invoked instead() */
    public String perform() {
        return downCall();
    }

    public String upCall() {
        return internal;
    }

    protected String downCall() {
        return internal;
    }
}


class SpecialSupplier extends Supplier {
    /* sub class method is calling a method from the superclass.
    * This method has a dependency on the internal behavior of the method
    * in the superclass.
    * If the method in the superclass changes what it returns, then this method
    * has to be amended to accommodate this. */
    @Override
    public String upCall() {
        return super.upCall() + " who is Special";
    }

    @Override
    protected String downCall() {
        return "I'm Special";
    }
}