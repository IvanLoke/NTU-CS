package objects;

class A {
    public A() {
    };

    public void print(Object o) {
        System.out.println("A");
    }
}

class C1 extends A {
    public C1() {
    };

    public void print(String s) {
        System.out.println("C1");
    }
}

class Test1 {

    public static void main(String[] args) {
        A a2 = new C1();
        a2.print("line 2");
    }
}