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
}
