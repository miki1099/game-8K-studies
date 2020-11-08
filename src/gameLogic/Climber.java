package gameLogic;


import lombok.Builder;
import lombok.Data;

import javax.swing.*;

@Data
@Builder
class Climber {
    private JLabel currentPosition;
    private SiteParameters siteParameters;
    private byte acclimation;
    private boolean isAlive;
    private byte movesInOneDay;

    private final byte MOVES_IN_DAY_MOD = -7;

    public byte impactFromMovesInOneDay(){
        return (byte) (movesInOneDay*MOVES_IN_DAY_MOD);
    }
}
