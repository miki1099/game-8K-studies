package gameLogic;

/**
 * Item tent class
 * @author Michal Glodek
 */
public class Tent extends Item {
    /** impact on climber for next day */
    private final short NIGHT_IMPACT_MOD = 15;
    /** impact on climber every move */
    private final short MOVE_IMPACT_MOD = -20;

    /**
     * Creates tent and set impacts
     */
    public Tent() {
        super();
        setMoveImpactMod(MOVE_IMPACT_MOD);
        setNightImpactMod(NIGHT_IMPACT_MOD);
        setName("Tent");
    }
}
