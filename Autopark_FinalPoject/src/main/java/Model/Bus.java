package Model;

import java.util.Objects;

public class Bus {

    private String busID;
    private String busModel;
    private int maxCountOfPassagers;
    private int miles;
    private boolean passedService;

    public static Bus.Builder newBuilder() {
        return new Bus().new Builder();
    }

    public String getBusID() {
        return busID;
    }

    public String getBusModel() {
        return busModel;
    }

    public int getMaxCountOfPassagers() {
        return maxCountOfPassagers;
    }

    public int getMiles() {
        return miles;
    }

    public boolean isPassedService() {
        return passedService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return maxCountOfPassagers == bus.maxCountOfPassagers &&
                miles == bus.miles &&
                passedService == bus.passedService &&
                Objects.equals(busID, bus.busID) &&
                Objects.equals(busModel, bus.busModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(busID, busModel, maxCountOfPassagers, miles, passedService);
    }

    @Override
    public String toString() {
        return "Bus (" +
                "busID = '" + busID + '\'' +
                ", busModel = '" + busModel + '\'' +
                ", maxCountOfPassagers = " + maxCountOfPassagers +
                ", miles = " + miles +
                ", passedService = " + passedService +
                ')';
    }

    public class Builder {

        private Builder() {
        }

        public Builder setBusID(String aBusID) {
            busID = aBusID;
            return this;
        }

        public Builder setBusModel(String aBusModel) {
            busModel = aBusModel;
            return this;
        }

        public Builder setmaxCountOfPassagers(int aMaxCountOfPassagers) {
            maxCountOfPassagers = aMaxCountOfPassagers;
            return this;
        }

        public Builder setMiles(int aMiles) {
            miles = aMiles;
            return this;
        }

        public Builder setPassedService(boolean aService) {
            passedService = aService;
            return this;
        }

        public Bus build() {
            return Bus.this;
        }
    }
}
