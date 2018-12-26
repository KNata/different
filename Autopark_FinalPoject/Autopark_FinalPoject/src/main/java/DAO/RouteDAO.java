package DAO;

import DBConnection.ConnectionPool;
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
                preparedStatement.setDate(8, anEntity.getRouteStartTime());
                preparedStatement.setDate(9, anEntity.getRouteEndTime());
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
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(selectAllSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int routeID = resultSet.getInt("routeID");
                String routeTitle = resultSet.getString("routeName");
                String driverID = resultSet.getString("driverID");
                String busID = resultSet.getString("busID");
                String cityOfDeparture = resultSet.getString("cityOfDeparture");
                String cityOfArrival = resultSet.getString("cityOfArrival");
                int routeDuration = resultSet.getInt("routeDuration");
                Date departureTime = resultSet.getDate("departureTime");
                Date arrivalTime = resultSet.getDate("arrivalTime");

                Route theRoute = Route.newBuilder().setRouteID(routeID).setRouteTitle(routeTitle)
                        .setDriver(driverDAO.findByID(driverID).getDriverID()).setBus(busDAO.findByID(busID).getBusID()).setRouteBegin(cityOfDeparture)
                        .setRouteEnd(cityOfArrival).setRouteDuration(routeDuration)
                        .setRouteStartTime(departureTime).setRouteEndTime(arrivalTime).build();
                routeList.add(theRoute);
                savePoint = conn.setSavepoint();
            }
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
                    Date departureTime = resultSet.getDate("departureTime");
                    Date arrivalTime = resultSet.getDate("arrivalTime");
                    theRoute = Route.newBuilder().setRouteID(routeID).setRouteTitle(routeTitle)
                            .setDriver(driverDAO.findByID(driverID).getDriverID()).setBus(busDAO.findByID(busID).getBusID()).setRouteBegin(cityOfDeparture)
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
                    Date departureTime = resultSet.getDate("departureTime");
                    Date arrivalTime = resultSet.getDate("arrivalTime");
                    theRoute = Route.newBuilder().setRouteID(routeID).setRouteTitle(routeTitle)
                            .setDriver(driverDAO.findByID(driverID).getDriverID()).setBus(busDAO.findByID(busID).getBusID()).setRouteBegin(cityOfDeparture)
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

    public boolean update(int routeID, String driverID, String busID, Date departureTime, Date arrivalTime) {
        boolean wasUpdated = false;
        String updateSQL = "update `mydb`.`Route` set  driverID = ?, busID = ?, departureTime = ?, arrivalTime = ? where routeID = ?";
        BusDAO busDAO = new BusDAO();
        DriverDAO driverDAO = new DriverDAO();
        if (routeID == 0 && driverID == null && busID == null && departureTime == null && arrivalTime == null) {
            wasUpdated = false;
        }
        if (busDAO.findByID(busID) == null && driverDAO.findByID(driverID) == null) {
            wasUpdated = false;
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, driverID);
            preparedStatement.setString(2, busID);
            preparedStatement.setDate(3, departureTime);
            preparedStatement.setDate(4, arrivalTime);
            preparedStatement.setInt(5, routeID);
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
}