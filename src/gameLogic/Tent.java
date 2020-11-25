package gameLogic;

class Tent extends Item {
    private final byte NIGHT_IMPACT_MOD = 15;
    private final byte MOVE_IMPACT_MOD = -20;

    public Tent() {
        super();
        setMoveImpactMod(MOVE_IMPACT_MOD);
        setNightImpactMod(NIGHT_IMPACT_MOD);
        setName("Tent");
    }
}
