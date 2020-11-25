package gameLogic;

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


    public byte getNightImpactMod() {
        return nightImpactMod;
    }

    public byte getMoveImpactMod() {
        return moveImpactMod;
    }

    public void setNightImpactMod(byte nightImpactMod) {
        this.nightImpactMod = nightImpactMod;
    }

    public void setMoveImpactMod(byte moveImpactMod) {
        this.moveImpactMod = moveImpactMod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
