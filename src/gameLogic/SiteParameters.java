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
