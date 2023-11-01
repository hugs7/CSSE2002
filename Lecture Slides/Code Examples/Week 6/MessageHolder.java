// to be able to use the clone() method we have to implement the Cloneable interface
public class MessageHolder implements Cloneable {

    private StringBuilder mess;

    public MessageHolder() {
        mess = new StringBuilder("<empty>");
    }

    public MessageHolder(String s) {
        mess = new StringBuilder(s);
    }

    public void addText(String s) {
        mess.append(s);
    }

    @Override
    public String toString() {
        return mess.toString();
    }

    @Override
    public Object clone() { // clone() method is protected in Object class.
        //return cl1();     // overriding it here it is made public
        return cl2();
        //return null;
    }

    /** Shallow copy method.
     * Copies the object, manages all the class details correctly.
     * But I haven't copied the 'mess' variable's object,
     * I've copied the reference that the 'mess' variable has.
     *
     * After this m1 and m2 are referring to the same StringBuilder, even
     * though m1 and m2 are different objects.
     * There are times when this approach is suitable.
     */
    private Object cl1() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
        }
        return o;
    }

    /** Deep copy method.
     * Demonstrates the general template for cloning.
     */
    private Object cl2() {
        // set reference object to null
        Object o = null;
        try {
            /* we call super.clone() to ensure we are correctly copying things from
            the parent class */
            MessageHolder m = (MessageHolder) super.clone();

            /* StringBuilder is not Cloneable, so we need to do this ourselves.
            (there are more compact ways) */
            m.mess = new StringBuilder(mess.length());
            // Copy each character from original StringBuilder object to
            // same position in new StringBuilder object
            for (int i = 0; i < mess.length(); ++i) {
                m.mess.append(mess.charAt(i));
            }
            return m;
        } catch (CloneNotSupportedException e) {
        }
        return o; // have to return in case an exception gets thrown
    }

}