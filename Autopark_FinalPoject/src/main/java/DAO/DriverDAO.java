package DAO;

import DBConnection.ConnectionPool;
import Model.Driver;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DriverDAO implements AbstractDAO<String, Driver> {

    private static Logger theLogger;

    static {
        theLogger = Logger.getLogger(DriverDAO.class);
    }

    @Override
    public boolean addRecord(Driver anEntity) throws SQLException {
        boolean wasAdded = false;
        String insertSQL = "insert into `mydb`.`Driver` values(?, ?)";
        if (anEntity == null) {
            wasAdded = false;
        } else if (findByID(anEntity.getDriverID()) != null) {
            System.out.println("This route is already in a system");
        } else {
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            Savepoint savePoint = null;
            try {
                conn = ConnectionPool.getConnection();
                conn.setAutoCommit(false);
                preparedStatement = conn.prepareStatement(insertSQL);
                preparedStatement.setString(1, anEntity.getDriverID());
                preparedStatement.setString(2, anEntity.getDriverName());
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
        String deleteSQL = "delete from `mydb`.`Driver` where driverID = ?";
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
                preparedStatement.setString(1, anID);
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
    public ArrayList<Driver> findAll() throws SQLException {
        String selectAllSQL = "select * from `mydb`.`Driver`";
        ArrayList<Driver> driverList = new ArrayList<Driver>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(selectAllSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String driverID = resultSet.getString("driverID");
                String driverName = resultSet.getString("driverName");
                Driver theDriver = Driver.newBuilder().setDriverID(driverID).setDriverName(driverName).build();
                driverList.add(theDriver);
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
        return driverList;
    }

    @Override
    public Driver findByID(String anID) throws SQLException {
        String selectAllSQL = "select * from `mydb`.`Driver` where driverID = ?";
        Driver theDriver = null;
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
                String driverID = resultSet.getString("driverID");
                if (driverID.equals(anID)) {
                    String driverName = resultSet.getString("driverName");
                    theDriver = Driver.newBuilder().setDriverID(driverID).setDriverName(driverName).build();
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
        return theDriver;
    }

    @Override
    public Driver findByName(String aName) throws SQLException {
        String selectAllSQL = "select * from `mydb`.`Driver` where driverName = ?";
        Driver theDriver = null;
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
                String driverName = resultSet.getString("driverName");
                if (driverName.equals(aName)) {
                    String driverID = resultSet.getString("driverID");
                    theDriver = Driver.newBuilder().setDriverID(driverID).setDriverName(driverName).build();
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
        return theDriver;
    }

    @Override
    public boolean update() {
        String updateSQL = "";
        throw new UnsupportedOperationException();
    }
}
