package gameLogic;

import javax.swing.*;

/**
 * Blank item class
 * @author Michal Glodek
 */
public class BlankItem extends Item{
    /** impact on climber for next day */
    private final short NIGHT_IMPACT_MOD = 0;
    /** impact on climber every move */
    private final short MOVE_IMPACT_MOD = 0;

    /**
     * Creates Blank item and set impacts
     */
    public BlankItem() {
        super();
        setMoveImpactMod(MOVE_IMPACT_MOD);
        setNightImpactMod(NIGHT_IMPACT_MOD);
        setName("Nothing");
        setActualSite(new JLabel());
    }
}
