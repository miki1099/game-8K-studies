package gameLogic;

import lombok.Data;

import javax.swing.*;
/**
 * Item abstract class
 * @author Michal Glodek
 */
@Data
public abstract class Item {
    /** impact on climber for next day */
    protected short nightImpactMod;
    /** impact on climber every move */
    protected short moveImpactMod;
    /** actual site of item */
    protected JLabel actualSite;
    /** item name */
    String name;

    /**
     * Creates new item
     */
    public Item() {
    }

    /**
     * equals method compare this method to argument
     * @param item other item toi compare
     * @return true if equal other false
     */
    public boolean equals(Item item){
        return item.getMoveImpactMod() == this.moveImpactMod &&
                item.getNightImpactMod() == this.nightImpactMod &&
                item.name.equals(this.name);
    }

    @Override
    public String toString() {
        return name;
    }
    /**
     * name getter
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * name setter
     */
    public void setName(String name) {
        this.name = name;
    }
}
