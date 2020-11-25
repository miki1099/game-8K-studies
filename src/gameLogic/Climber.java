package gameLogic;


import lombok.Builder;
import lombok.Data;

import javax.swing.*;

@Data
@Builder
class Climber {
    private JLabel currentPosition;
    private SiteParameters siteParameters;
    private short acclimation;
    private short movesInOneDay;
    private boolean isAlive;
    private boolean madeToTop;
    private Item item;

    private final short MOVES_IN_DAY_MOD = -7;

    public short impactFromMovesInOneDay(){
        return (short) (movesInOneDay*MOVES_IN_DAY_MOD);
    }
}
