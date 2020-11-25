package gameLogic;

public class Backpack extends Item {
    private final short NIGHT_IMPACT_MOD = 10;
    private final short MOVE_IMPACT_MOD = -15;

    public Backpack() {
        super();
        setMoveImpactMod(MOVE_IMPACT_MOD);
        setNightImpactMod(NIGHT_IMPACT_MOD);
        setName("Backpack");
    }
}
