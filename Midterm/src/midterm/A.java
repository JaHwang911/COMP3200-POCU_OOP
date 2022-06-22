package midterm;

public class A {
    private int z;

    public A(int z) {
        this.z = z;
    }

    public class B {
        private int x;

        public B(int x) {
            this.x = x;
        }

        private void doMagic() {
            z++;
        }
    }

    public void doMagic(int x, int y) {
        B b = new B(x + y);
        b.x += 2;
        this.z *= b.x;
        b.doMagic();

        System.out.println(b.x);
        System.out.println(this.z);
    }
}
