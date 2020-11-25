package gameLogic;

import lombok.Data;

@Data
public abstract class Item {
    protected byte nightImpactMod;
    protected byte moveImpactMod;
    String name;

    public Item() {
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
