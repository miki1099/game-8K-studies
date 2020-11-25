package gameLogic;

import lombok.Data;

import javax.swing.*;

@Data
public abstract class Item {
    protected short nightImpactMod;
    protected short moveImpactMod;
    protected JLabel actualSite;
    String name;

    public Item() {
    }

    public boolean equals(Item item){
        return item.getMoveImpactMod() == this.moveImpactMod &&
                item.getNightImpactMod() == this.nightImpactMod &&
                item.name.equals(this.name);
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
