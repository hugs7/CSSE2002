
// has at least one declared constructor
// No default constructor
class Test1 {

    public Test1(int x) {
    }
}

// Has a default constructor explicitly declared
class Test2 {

    public Test2() {
    }
}

// No explicit constructor, default automatically added
class Test3 {

}

public class Constructors {

    public static void main(String[] args) {
        Test1 t1 = new Test1(5);
        Test1 t1a = new Test1();
        Test2 t2 = new Test2();
        Test3 t3 = new Test3();
    }


}
