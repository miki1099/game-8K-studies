package gameLogic;


import lombok.Builder;
import lombok.Data;

import javax.swing.*;

/**
 * Climber class
 * @author Michal Glodek
 */
@Data
@Builder
class Climber {
    /** current place on game board in GamePanel */
    private JLabel currentPosition;
    /** site parameters on current place */
    private SiteParameters siteParameters;
    /** acclimation level */
    private short acclimation;
    /** moves made in the same day */
    private short movesInOneDay;
    private boolean isAlive;
    private boolean madeToTop;
    private boolean readyToEndGame;
    /** Item with climber have on him */
    private Item item;
    /** every move it will be multiplied by moves in the day*/
    private final short MOVES_IN_DAY_MOD = -7;

    /**
     * every move it will be multiplied by moves in the day
     * @return impact on climber
     * */
    public short impactFromMovesInOneDay(){
        return (short) (movesInOneDay*MOVES_IN_DAY_MOD);
    }
}
