package DAO;

import DBConnection.ConnectionPool;
import Model.Bus;
import Model.Driver;
import Model.Route;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.apache.log4j.Logger;

import java.awt.image.AreaAveragingScaleFilter;
import java.sql.*;
import java.util.ArrayList;

public class RouteDAO implements AbstractDAO<String, Route> {

    private static Logger theLogger;
    private String driverID;

    static {
        theLogger = Logger.getLogger(DriverDAO.class);
    }

    @Override
    public boolean addRecord(Route anEntity) {
        boolean wasAdded = false;
        String convertedRouteID = String.valueOf(anEntity.getRouteID());
        String insertSQL = "insert into `mydb`.`Route` values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        if (anEntity == null) {
            wasAdded = false;
        } else if (findByID(convertedRouteID) != null) {
            System.out.println("This route is already in a system");
        } else {
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            Savepoint savePoint = null;
            try {
                conn = ConnectionPool.getConnection();
                conn.setAutoCommit(false);
                preparedStatement = conn.prepareStatement(insertSQL);
                preparedStatement.setInt(1, anEntity.getRouteID());
                preparedStatement.setString(2, anEntity.getRouteTitle());
                preparedStatement.setString(3, anEntity.getDriverID());
                preparedStatement.setString(4, anEntity.getBusID());
                preparedStatement.setString(5, anEntity.getRouteBegin());
                preparedStatement.setString(6, anEntity.getRouteEnd());
                preparedStatement.setInt(7, anEntity.getRouteDuration());
                preparedStatement.setString(8, anEntity.getRouteStartTime());
                preparedStatement.setString(9, anEntity.getRouteEndTime());
                preparedStatement.executeUpdate();
                wasAdded = true;
                savePoint = conn.setSavepoint();
                conn.commit();
            } catch (SQLException e) {
                try {
                    if (savePoint == null) {
                        conn.rollback();
                    } else {
                        conn.rollback(savePoint);
                    }
                } catch (SQLException ee) {}
                System.err.println(e.getMessage());
                theLogger.error(e.getMessage());
            } finally {
                try {
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (conn != null) {
                        conn.commit();
                    }
                } catch (SQLException e) {}
            }
        }
        return wasAdded;
    }

    @Override
    public boolean deleteRecord(String anID) {
        boolean wasDeleted = false;
        int convertedIDValue = Integer.valueOf(anID);
        String deleteSQL = "delete from `mydb`.`Route` where routeID = ?";
        if (anID == null) {
            wasDeleted = false;
        } else {
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            Savepoint savePoint = null;
            try {
                conn = ConnectionPool.getConnection();
                conn.setAutoCommit(false);
                preparedStatement = conn.prepareStatement(deleteSQL);
                preparedStatement.setInt(1, convertedIDValue);
                preparedStatement.executeUpdate();
                wasDeleted = true;
                savePoint = conn.setSavepoint();
                conn.commit();
            } catch (SQLException e) {
                try {
                    if (savePoint == null) {
                        conn.rollback();
                    } else {
                        conn.rollback(savePoint);
                    }
                } catch (SQLException ee) {}
                System.err.println(e.getMessage());
                theLogger.error(e.getMessage());
            } finally {
                try {
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (conn != null) {
                        conn.commit();
                    }
                } catch (SQLException e) {}
            }
        }
        return wasDeleted;
    }

    @Override
    public ArrayList<Route> findAll() {
        String selectAllSQL = "select * from `mydb`.`Route`";
        ArrayList<Route> routeList = new ArrayList<Route>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        DriverDAO driverDAO = new DriverDAO();
        BusDAO busDAO = new BusDAO();
        try {
            conn = ConnectionPool.getConnection();
            preparedStatement = conn.prepareStatement(selectAllSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int routeID = resultSet.getInt("routeID");
               // System.out.println("1");
                String routeTitle = resultSet.getString("routeName");
             //  System.out.println("2");
                String driverID = resultSet.getString("driverID");
             //   System.out.println("3");
                String busID = resultSet.getString("busID");
           //   System.out.println("4");
                String cityOfDeparture = resultSet.getString("cityOfDeparture");
            //    System.out.println("5");
                String cityOfArrival = resultSet.getString("cityOfArrival");
             //   System.out.println("6");
                int routeDuration = resultSet.getInt("routeDuration");
             //  System.out.println("7");
                String departureTime = resultSet.getString("departureTime");
          //   System.out.println("8");
                String arrivalTime = resultSet.getString("arrivalTime");

//                Driver theDriver =  driverDAO.findByID(driverID);
         //      System.out.println(theDriver.toString());

          //     Bus theBus = busDAO.findByID(busID);
           //     System.out.println(theBus.toString());

                Route theRoute = Route.newBuilder().setRouteID(routeID).setRouteTitle(routeTitle)
                        .setDriver(driverID).setBusID(busID).setRouteBegin(cityOfDeparture)
                        .setRouteEnd(cityOfArrival).setRouteDuration(routeDuration)
                        .setRouteStartTime(departureTime).setRouteEndTime(arrivalTime).build();
                routeList.add(theRoute);
           //   System.out.println("+");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            theLogger.error(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.commit();
                }
            } catch (SQLException e) {}
        }
        return routeList;
    }

    @Override
    public Route findByID(String anID) {
        int convertedRouteID = Integer.valueOf(anID);
        String selectAllSQL = "select * from `mydb`.`Route` where `routeID` = ?";
        BusDAO busDAO = new BusDAO();
        DriverDAO driverDAO = new DriverDAO();
        Route theRoute = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(selectAllSQL);
            preparedStatement.setInt(1, convertedRouteID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int routeID = resultSet.getInt("routeID");
                if (routeID == convertedRouteID) {
                    String routeTitle = resultSet.getString("routeName");
                    String driverID = resultSet.getString("driverID");
                    String busID = resultSet.getString("busID");
                    String cityOfDeparture = resultSet.getString("cityOfDeparture");
                    String cityOfArrival = resultSet.getString("cityOfArrival");
                    int routeDuration = resultSet.getInt("routeDuration");
                    String departureTime = resultSet.getString("departureTime");
                    String arrivalTime = resultSet.getString("arrivalTime");
                    theRoute = Route.newBuilder().setRouteID(routeID).setRouteTitle(routeTitle)
                            .setDriver(driverID).setBusID(busID).setRouteBegin(cityOfDeparture)
                            .setRouteEnd(cityOfArrival).setRouteDuration(routeDuration)
                            .setRouteStartTime(departureTime).setRouteEndTime(arrivalTime).build();
                }
            }
            savePoint = conn.setSavepoint();
            conn.commit();
        } catch (SQLException e) {
            try {
                if (savePoint == null) {
                    conn.rollback();
                } else {
                    conn.rollback(savePoint);
                }
            } catch (SQLException ee) {}
            System.err.println(e.getMessage());
            theLogger.error(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.commit();
                }
            } catch (SQLException e) {}
        }
        return theRoute;
    }

    @Override
    public Route findByName(String aName) {
        String selectAllSQL = "select * from `mydb`.`Route` where `routeName` = ?";
        BusDAO busDAO = new BusDAO();
        DriverDAO driverDAO = new DriverDAO();
        Route theRoute = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(selectAllSQL);
            preparedStatement.setString(1, aName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String routeTitle = resultSet.getString("routeName");
                if (routeTitle.equals(aName)) {
                    int routeID = resultSet.getInt("routeID");
                    String driverID = resultSet.getString("driverID");
                    String busID = resultSet.getString("busID");
                    String cityOfDeparture = resultSet.getString("cityOfDeparture");
                    String cityOfArrival = resultSet.getString("cityOfArrival");
                    int routeDuration = resultSet.getInt("routeDuration");
                    String departureTime = resultSet.getString("departureTime");
                    String arrivalTime = resultSet.getString("arrivalTime");
                    theRoute = Route.newBuilder().setRouteID(routeID).setRouteTitle(routeTitle)
                            .setDriver(driverID).setBusID(busID).setRouteBegin(cityOfDeparture)
                            .setRouteEnd(cityOfArrival).setRouteDuration(routeDuration)
                            .setRouteStartTime(departureTime).setRouteEndTime(arrivalTime).build();
                }
            }
            savePoint = conn.setSavepoint();
            conn.commit();
        } catch (SQLException e) {
            try {
                if (savePoint == null) {
                    conn.rollback();
                } else {
                    conn.rollback(savePoint);
                }
            } catch (SQLException ee) {}
            System.err.println(e.getMessage());
            theLogger.error(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.commit();
                }
            } catch (SQLException e) {}
        }
        return theRoute;
    }

    public boolean update(int routeID, String driverID, String busID, String departureTime, String arrivalTime, int duration) {
        boolean wasUpdated = false;
        String updateSQL = "update `mydb`.`Route` set  driverID = ?, busID = ?, departureTime = ?, arrivalTime = ?, " +
                "routeDuration where routeID = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, driverID);
            preparedStatement.setString(2, busID);
            preparedStatement.setString(3, departureTime);
            preparedStatement.setString(4, arrivalTime);
            preparedStatement.setInt(5, duration);
            preparedStatement.setInt(6, routeID);
            preparedStatement.executeUpdate();
            wasUpdated = true;
            savePoint = connection.setSavepoint();
            connection.commit();
        } catch (SQLException e) {
            if (savePoint == null) {
                try {
                    connection.rollback();
                }catch (SQLException ee) {
                    theLogger.error(e.getMessage());
                }
            } else {
                try {
                    connection.rollback(savePoint);
                }catch (SQLException ee) {
                    theLogger.error(e.getMessage());
                }
            }
            theLogger.error(e.getMessage());
            System.err.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    theLogger.error(e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    theLogger.error(e.getMessage());
                }
            }

        }
        return wasUpdated;
    }

    public ArrayList<Route> showDriverInfo() {
        String sql = "select routeID, routeName, busID, cityOfDeparture, cityOfArrival, departureTime, " +
                "arrivalTime from `mydb`.`Route` where driverID = ?";
        ArrayList<Route> routeList = new ArrayList<Route>();
        Route theDriverStory = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        BusDAO busDAO = new BusDAO();
        try {
            conn = ConnectionPool.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, driverID);
            System.out.println("Inside RouteDAO driverID" + driverID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int routeID = resultSet.getInt("routeID");
                String routeName = resultSet.getString("routeName");
                String busID = resultSet.getString("busID");
                String cityOfDeparture = resultSet.getString("cityOfDeparture");
                String cityOfArrival = resultSet.getString("cityOfArrival");
                String departureTime = resultSet.getString("departureTime");
                String arrivalTime = resultSet.getString("arrivalTime");
                theDriverStory = Route.newBuilder().setRouteID(routeID).setRouteTitle(routeName)
                        .setBusID(busID).setRouteBegin(cityOfDeparture)
                        .setRouteEnd(cityOfArrival).setRouteBegin(cityOfDeparture).setRouteEndTime(arrivalTime).build();
                routeList.add(theDriverStory);
            }
        } catch (SQLException e) {
            theLogger.error(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.commit();
                }
            } catch (SQLException e) {}
        }
        return routeList;
    }

    public void setDriverID(String aDriverID) {
        driverID = aDriverID;
    }

    public String getDriverID() {
        return driverID;
    }
}