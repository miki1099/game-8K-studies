package gameLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SiteParameters {
    public byte level;
    public List<Routes> route;


    public SiteParameters(byte level,Routes baseRoute, Routes ...routes) {
        route = new ArrayList<>();
        this.level = level;
        route.add(baseRoute);
        Collections.addAll(this.route, routes);
    }

    public boolean equals(SiteParameters obj) {
        return (this.level == obj.level && this.route.equals(obj.route));
    }

    public byte impactFromMove(SiteParameters destination){
        byte impactMod = 0;
        if(this.route.contains(Routes.C) && this.level >= 2){
            impactMod = -5;
        } else if(this.route.contains(Routes.D) && this.level >= 3){
            impactMod = -2;
        }

        if(this.level < destination.level){
            return (byte)(-5*destination.level + impactMod);
        } else {
            return (byte)((-5*destination.level + impactMod)/2);
        }
    }

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

}
