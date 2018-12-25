package Model;

import java.sql.Date;
import java.util.Objects;

public class Route {

    private String routeID;
    private String routeTitle;
    private String cityOfDeparture;
    private String cityOfArrival;
    private int routeDuration;
    private Date departureTime;
    private Date arrivalTime;

    private Driver theDriver;
    private Bus theBus;

    public String getRouteID() {
        return routeID;
    }

    public String getRouteTitle() {
        return routeTitle;
    }

    public String getRouteBegin() {
        return cityOfDeparture;
    }

    public String getRouteEnd() {
        return cityOfArrival;
    }

    public int getRouteDuration() {
        return routeDuration;
    }

    public Date getRouteStartTime() {
        return departureTime;
    }

    public Date getRouteEndTime() {
        return arrivalTime;
    }

    public Driver getTheDriver() {
        return theDriver;
    }

    public Bus getTheBus() {
        return theBus;
    }

    public static Route.Builder newBuilder() {
        return new Route().new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return routeID == route.routeID &&
                routeDuration == route.routeDuration &&
                Objects.equals(routeTitle, route.routeTitle) &&
                Objects.equals(cityOfDeparture, route.cityOfDeparture) &&
                Objects.equals(cityOfArrival, route.cityOfArrival) &&
                Objects.equals(departureTime, route.departureTime) &&
                Objects.equals(arrivalTime, route.arrivalTime) &&
                Objects.equals(theDriver, route.theDriver) &&
                Objects.equals(theBus, route.theBus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeID, routeTitle, cityOfDeparture, cityOfArrival, routeDuration, departureTime, arrivalTime, theDriver, theBus);
    }

    @Override
    public String toString() {
        return "Route (" +
                "routeID = " + routeID +
                ", routeTitle = '" + routeTitle + '\'' +
                ", cityOfDeparture = '" + cityOfDeparture + '\'' +
                ", cityOfArrival = '" + cityOfArrival + '\'' +
                ", routeDuration = " + routeDuration +
                ", departureTime = " + departureTime +
                ", arrivalTime = " + arrivalTime +
                ", theDriver = " + theDriver.toString() +
                ", theBus = " + theBus.toString() +
                ')';
    }

    public class Builder {

        private Builder() {
        }

        public Builder setRouteID(String aRouteID) {
            routeID = aRouteID;
            return this;
        }

        public Builder setRouteTitle(String aRouteTitle) {
            routeTitle = aRouteTitle;
            return this;
        }

        public Builder setRouteBegin(String aRouteBegin) {
            cityOfDeparture = aRouteBegin;
            return this;
        }

        public Builder setRouteEnd(String aRouteEnd) {
            cityOfArrival = aRouteEnd;
            return this;
        }

        public Builder setRouteDuration(int aRouteDuration) {
            routeDuration = aRouteDuration;
            return this;
        }

        public Builder setRouteStartTime(Date aRouteStartTime) {
            departureTime = aRouteStartTime;
            return this;
        }

        public Builder setRouteEndTime(Date aRouteEndTime) {
            arrivalTime = aRouteEndTime;
            return this;
        }

        public Builder setDriver(Driver aDriver) {
            theDriver = aDriver;
            return this;
        }

        public Builder setBus(Bus aBus) {
            theBus = aBus;
            return this;
        }

        public Route build() {
            return Route.this;
        }
    }
}
