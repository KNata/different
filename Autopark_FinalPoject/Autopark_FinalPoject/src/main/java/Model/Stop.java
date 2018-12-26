package Model;

import java.sql.Date;
import java.util.Objects;

public class Stop {

    private Driver theDriver;
    private Bus theBus;
    private String stopName;
    private Date arrivalTime;
    private Date departureTime;
    private double stopDuration;

    public Driver getDriver() {
        return theDriver;
    }

    public Bus getBus() {
        return theBus;
    }

    public String getStopName() {
        return stopName;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public double getStopDuration() {
        return stopDuration;
    }

    public static Stop.Builder newBuilder() {
        return new Stop().new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stop stop = (Stop) o;
        return Objects.equals(theBus, stop.theBus) &&
                Objects.equals(theBus, stop.theBus) && Objects.equals(stopName, stop.stopName) &&
                Objects.equals(arrivalTime, stop.arrivalTime) &&
                Objects.equals(departureTime, stop.departureTime) &&
                Objects.equals(stopDuration, stop.stopDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(theDriver, theBus, stopName, arrivalTime, departureTime, stopDuration);
    }

    @Override
    public String toString() {
        return "Stop (" +
                "driver = '" + theDriver.toString() + '\'' +
                ", bus = '" + theBus.toString() + '\'' +
                ", stopName = '" + stopName + '\'' +
                ", arrivalTime = " + arrivalTime +
                ", departureTime = " + departureTime +
                ", stopdDuration = " + stopDuration +
                ')';
    }

    public class Builder {

        private Builder() {
        }

        public Stop.Builder setStopTitle(String aStopTitle) {
            stopName = aStopTitle;
            return this;
        }

        public Stop.Builder setbusArrival(Date aBusArrival) {
            arrivalTime = aBusArrival;
            return this;
        }

        public Stop.Builder setBusDeparture(Date aBusDeparture) {
            departureTime = aBusDeparture;
            return this;
        }

        public Stop.Builder setStopDuration(int aStopDuration) {
            stopDuration = aStopDuration;
            return this;
        }

        public Stop.Builder setRouteStartTime(Date aRouteStartTime) {
            departureTime = aRouteStartTime;
            return this;
        }

        public Stop.Builder setRouteEndTime(Date aRouteEndTime) {
            arrivalTime = aRouteEndTime;
            return this;
        }

        public Stop.Builder setDriver(Driver aDriver) {
            theDriver = aDriver;
            return this;
        }

        public Stop.Builder setBus(Bus aBus) {
            theBus = aBus;
            return this;
        }

        public Stop build() {
            return Stop.this;
        }
    }
}
