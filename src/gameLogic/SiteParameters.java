package gameLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Site parameters class can count impact for climber and check move legality
 * @author Michal Glodek
 */
public class SiteParameters {
    /** site level (height) */
    public short level;
    /** routes with goes by site */
    public List<Routes> route;

    /**
     * Creates site parameters
     * @param level height
     * @param baseRoute min 1 route has to go by site
     * @param routes other optional routes
     */
    public SiteParameters(short level,Routes baseRoute, Routes ...routes) {
        route = new ArrayList<>();
        this.level = level;
        route.add(baseRoute);
        Collections.addAll(this.route, routes);
    }

    /**
     * compare objects by level and routes
     * @param obj other site parameters to compare
     * @return true if equal else false
     */
    public boolean equals(SiteParameters obj) {
        return (this.level == obj.level && this.route.equals(obj.route));
    }

    /**
     * calculate impact from move
     * @param destination parameters of destination site
     * @return impact on climber
     */
    public short impactFromMove(SiteParameters destination){
        short impactMod = 0;
        if(this.level == destination.level){
            return (short) 0;
        }

        if(this.route.contains(Routes.C) && this.level >= 2){
            impactMod = -5;
        } else if(this.route.contains(Routes.D) && this.level >= 3){
            impactMod = -2;
        }

        if(this.level < destination.level){
            return (short)(-5*destination.level + impactMod);
        } else {
            return (short)((-5*destination.level + impactMod)/2);
        }
    }

    /**
     * checks if move is legal by level and reoutes
     * @param site1 destination site parameters
     * @return boolean
     */
    public boolean isLegalToMove( SiteParameters site1){
        boolean contains = false;
        if(Math.abs(site1.level - this.level) <= 1){
            for(Routes routes : site1.route){
                if(this.route.contains(routes)){
                    contains = true;
                    break;
                }
            }
            return contains;
        } else{
            return false;
        }
    }

    /**
     * give impact from height for next day
     * @return impact on climbers
     */
    public short getImpactFromSiteNextDay(){
        switch (this.level){
            case 0:
                return 20;
            case 1:
                return 10;
            case 2:
                return 0;
            case 3:
                return -5;
            case 4:
                return -20;
            case 5:
                return -35;
        }
        return 0;
    }

}
