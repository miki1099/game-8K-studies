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
}
