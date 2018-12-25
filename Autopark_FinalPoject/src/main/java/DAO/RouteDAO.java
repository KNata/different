package DAO;

import DBConnection.ConnectionPool;
import Model.Driver;
import Model.Route;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class RouteDAO implements AbstractDAO<String, Route> {

    private static Logger theLogger;

    static {
        theLogger = Logger.getLogger(DriverDAO.class);
    }


    @Override
    public boolean addRecord(Route anEntity) throws SQLException {
        boolean wasAdded = false;
        String insertSQL = "insert into `mydb`.`Route` values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        if (anEntity == null) {
            wasAdded = false;
        } else if (findByID(anEntity.getRouteID()) != null) {
            System.out.println("This route is already in a system");
        } else {
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            Savepoint savePoint = null;
            try {
                conn = ConnectionPool.getConnection();
                conn.setAutoCommit(false);
                preparedStatement = conn.prepareStatement(insertSQL);
                preparedStatement.setString(1, anEntity.getRouteID());
                preparedStatement.setString(2, anEntity.getRouteTitle());
                preparedStatement.setString(3, anEntity.getTheDriver().getDriverID());
                preparedStatement.setString(4, anEntity.getTheBus().getBusID());
                preparedStatement.setString(5, anEntity.getRouteBegin());
                preparedStatement.setString(6, anEntity.getRouteEnd());
                preparedStatement.setInt(7, anEntity.getRouteDuration());
                preparedStatement.setDate(8, anEntity.getRouteStartTime());
                preparedStatement.setDate(9, anEntity.getRouteEndTime());
                preparedStatement.executeUpdate();
                wasAdded = true;
                savePoint = conn.setSavepoint();
                conn.commit();
            } catch (SQLException e) {
                if (savePoint == null) {
                    conn.rollback();
                } else {
                    conn.rollback(savePoint);
                }
            } finally {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.commit();
                }
            }
        }
        return wasAdded;
    }

    @Override
    public boolean deleteRecord(String anID) throws SQLException {
        boolean wasDeleted = false;
        int convertedIDValue = Integer.valueOf(anID);
        String deleteSQL = "delete from `mydb`.`Route` where `routeID` = ?";
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
                if (savePoint == null) {
                    conn.rollback();
                } else {
                    conn.rollback(savePoint);
                }
                theLogger.error(e.getMessage());
            } finally {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.commit();
                }
            }
        }
        return wasDeleted;
    }

    @Override
    public ArrayList<Route> findAll() throws SQLException {
        String selectAllSQL = "select * from `mydb`.`Route`";
        ArrayList<Route> routeList = new ArrayList<Route>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        DriverDAO driverDAO = new DriverDAO();
        BusDAO busDAO = new BusDAO();
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(selectAllSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String routeID = resultSet.getString("routeID");
                String routeTitle = resultSet.getString("routeName");
                String driverID = resultSet.getString("driverID");
                String busID = resultSet.getString("busID");
                String cityOfDeparture = resultSet.getString("cityOfDeparture");
                String cityOfArrival = resultSet.getString("cityOfArrival");
                int routeDuration = resultSet.getInt("routeDuration");
                Date departureTime = resultSet.getDate("departureTime");
                Date arrivalTime = resultSet.getDate("arrivalTime");
                Route theRoute = Route.newBuilder().setRouteID(routeID).setRouteTitle(routeTitle)
                        .setDriver(driverDAO.findByID(driverID)).setBus(busDAO.findByID(busID)).setRouteBegin(cityOfDeparture)
                        .setRouteEnd(cityOfArrival).setRouteDuration(routeDuration)
                        .setRouteStartTime(departureTime).setRouteEndTime(arrivalTime).build();
                routeList.add(theRoute);
                savePoint = conn.setSavepoint();
            }
            conn.commit();
        } catch (SQLException e) {
            if (savePoint == null) {
                conn.rollback();
            } else {
                conn.rollback(savePoint);
            }
            theLogger.error(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                conn.commit();
            }
        }
        return routeList;
    }

    @Override
    public Route findByID(String anID) throws SQLException {
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
            preparedStatement.setString(1, anID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String routeID = resultSet.getString("routeID");
                if (routeID.equals(anID)) {
                    String routeTitle = resultSet.getString("routeName");
                    String driverID = resultSet.getString("driverID");
                    String busID = resultSet.getString("busID");
                    String cityOfDeparture = resultSet.getString("cityOfDeparture");
                    String cityOfArrival = resultSet.getString("cityOfArrival");
                    int routeDuration = resultSet.getInt("routeDuration");
                    Date departureTime = resultSet.getDate("departureTime");
                    Date arrivalTime = resultSet.getDate("arrivalTime");
                    theRoute = Route.newBuilder().setRouteID(routeID).setRouteTitle(routeTitle)
                            .setDriver(driverDAO.findByID(driverID)).setBus(busDAO.findByID(busID)).setRouteBegin(cityOfDeparture)
                            .setRouteEnd(cityOfArrival).setRouteDuration(routeDuration)
                            .setRouteStartTime(departureTime).setRouteEndTime(arrivalTime).build();
                }
            }
            savePoint = conn.setSavepoint();
            conn.commit();
        } catch (SQLException e) {
            if (savePoint == null) {
                conn.rollback();
            } else {
                conn.rollback(savePoint);
            }
            theLogger.error(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                conn.commit();
            }
        }
        return theRoute;
    }

    @Override
    public Route findByName(String aName) throws SQLException {
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
                    String routeID = resultSet.getString("routeID");
                    String driverID = resultSet.getString("driverID");
                    String busID = resultSet.getString("busID");
                    String cityOfDeparture = resultSet.getString("cityOfDeparture");
                    String cityOfArrival = resultSet.getString("cityOfArrival");
                    int routeDuration = resultSet.getInt("routeDuration");
                    Date departureTime = resultSet.getDate("departureTime");
                    Date arrivalTime = resultSet.getDate("arrivalTime");
                    theRoute = Route.newBuilder().setRouteID(routeID).setRouteTitle(routeTitle)
                            .setDriver(driverDAO.findByID(driverID)).setBus(busDAO.findByID(busID)).setRouteBegin(cityOfDeparture)
                            .setRouteEnd(cityOfArrival).setRouteDuration(routeDuration)
                            .setRouteStartTime(departureTime).setRouteEndTime(arrivalTime).build();
                }
            }
            savePoint = conn.setSavepoint();
            conn.commit();
        } catch (SQLException e) {
            if (savePoint == null) {
                conn.rollback();
            } else {
                conn.rollback(savePoint);
            }
            theLogger.error(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                conn.commit();
            }
        }
        return theRoute;
    }

    @Override
    public boolean update() {
        return false;
    }
}
