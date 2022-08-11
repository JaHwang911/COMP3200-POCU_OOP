package test;

import java.util.ArrayList;

public class Manager {
    private ArrayList<Foo> foos;

    public Manager(ArrayList<Foo> foos) {
        this.foos = foos;
    }

    public void doAllFoos() {
        for (Foo f : this.foos) {
            f.wingardiumLeviosa();
        }
    }
}
