package gameLogic;

class Backpack extends Item {
    private final byte NIGHT_IMPACT_MOD = 10;
    private final byte MOVE_IMPACT_MOD = -15;

    public Backpack() {
        super();
        setMoveImpactMod(MOVE_IMPACT_MOD);
        setNightImpactMod(NIGHT_IMPACT_MOD);
        setName("Backpack");
    }
}
