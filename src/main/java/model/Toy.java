package model;

public class Toy {
    private int ID;
    private String nameToy;
    private int chance;

    public Toy(int ID, String nameToy, int chance) {
        this.ID = ID;
        this.nameToy = nameToy;
        this.chance = chance;
    }

    @Override
    public String toString() {
        return "Toy {" +
                "ID = " + ID +
                ", тип игрушки = '" + nameToy + '\'' +
                ", шанс выпадения данного типа игрушек = " + chance +
                '}';
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public int getChance() {
        return chance;
    }

    public String getNameToy() {
        return nameToy;
    }

}
