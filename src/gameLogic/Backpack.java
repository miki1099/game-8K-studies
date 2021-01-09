package gameLogic;

/**
 * Item backpack class
 * @author Michal Glodek
 */
public class Backpack extends Item {
    /** impact on climber for next day */
    private final short NIGHT_IMPACT_MOD = 10;
    /** impact on climber every move */
    private final short MOVE_IMPACT_MOD = -15;

    /**
     * Creates backpack and set impacts
     */
    public Backpack() {
        super();
        setMoveImpactMod(MOVE_IMPACT_MOD);
        setNightImpactMod(NIGHT_IMPACT_MOD);
        setName("Backpack");
    }
}
