package gameLogic;

public class BlankItem extends Item{
    private final byte NIGHT_IMPACT_MOD = 0;
    private final byte MOVE_IMPACT_MOD = 0;

    public BlankItem() {
        super();
        setMoveImpactMod(MOVE_IMPACT_MOD);
        setNightImpactMod(NIGHT_IMPACT_MOD);
        setName("Nothing");
    }
}
