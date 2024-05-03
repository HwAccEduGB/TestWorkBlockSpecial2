package model;

import java.util.ArrayList;
import java.util.List;

public class ChildsWorldShop {
    private String name;
    private List<Toy> toys;

    public ChildsWorldShop() {
        this.name = "Детский мир";
        this.toys = new ArrayList<>();
    }

    public List<Toy> getToys() {
        return toys;
    }

    public String getName() {
        return name;
    }
}
