package gameLogic;

public class Tent extends Item {
    private final short NIGHT_IMPACT_MOD = 15;
    private final short MOVE_IMPACT_MOD = -20;

    public Tent() {
        super();
        setMoveImpactMod(MOVE_IMPACT_MOD);
        setNightImpactMod(NIGHT_IMPACT_MOD);
        setName("Tent");
    }
}
