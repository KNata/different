package DAO;

import DBConnection.ConnectionPool;
import Model.Bus;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;


public class BusDAO implements AbstractDAO<String, Bus> {


    private static Logger theLogger;


    static {
        theLogger = Logger.getLogger(BusDAO.class);
    }


    @Override
    public boolean addRecord(Bus anEntity) {
        boolean wasAdded = false;
        String insertSQL = "insert into `mydb`.`Bus` values(?, ?, ?, ?, ?)";
        if (anEntity == null) {
            wasAdded = false;
        } else if (findByID(anEntity.getBusID()) != null) {
            System.out.println("This route is already in a system");
        } else {
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            Savepoint savePoint = null;
            try {
                conn = ConnectionPool.getConnection();
                conn.setAutoCommit(false);
                preparedStatement = conn.prepareStatement(insertSQL);
                preparedStatement.setString(1, anEntity.getBusID());
                preparedStatement.setString(2, anEntity.getBusModel());
                preparedStatement.setBoolean(3, anEntity.isPassedService());
                preparedStatement.setInt(4, anEntity.getMaxCountOfPassagers());
                preparedStatement.setInt(5, anEntity.getMiles());
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
        String deleteSQL = "delete from `mydb`.`Bus` where busID = ?";
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
    public ArrayList<Bus> findAll() {
        String selectAllSQL = "select * from `mydb`.`Bus`";
        ArrayList<Bus> busList = new ArrayList<Bus>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(selectAllSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String busID = resultSet.getString("busID");
                String busName = resultSet.getString("busModel");
                int miles = resultSet.getInt("miles");
                int macCountOfPassengers = resultSet.getInt("maxPassengers");
                boolean maintence = resultSet.getBoolean("maintance");
                Bus theBus = Bus.newBuilder().setBusID(busID).setBusModel(busName)
                        .setmaxCountOfPassagers(macCountOfPassengers).setMiles(miles).setPassedService(maintence).build();
                busList.add(theBus);
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
        return busList;
    }

    @Override
    public Bus findByID(String anID) {
        String selectAllSQL = "select * from `mydb`.`Bus` where busID = ?";
        Bus theBus = null;
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
                String busID = resultSet.getString("busID");
                if (busID.equals(anID)) {
                    String busName = resultSet.getString("busModel");
                    int miles = resultSet.getInt("miles");
                    int macCountOfPassengers = resultSet.getInt("maxPassengers");
                    boolean maintence = resultSet.getBoolean("maintance");
                    theBus = Bus.newBuilder().setBusID(busID).setBusModel(busName)
                            .setmaxCountOfPassagers(macCountOfPassengers).setMiles(miles).setPassedService(maintence).build();
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
        return theBus;
    }

    @Override
    public Bus findByName(String aName) {
        String selectAllSQL = "select * from `mydb`.`Bus` where busModel = ?";
        Bus theBus = null;
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
                String busName = resultSet.getString("busModel");
                if (busName.equals(aName)) {
                    String busID = resultSet.getString("busID");
                    int miles = resultSet.getInt("miles");
                    int macCountOfPassengers = resultSet.getInt("maxPassengers");
                    boolean maintence = resultSet.getBoolean("maintance");
                    theBus = Bus.newBuilder().setBusID(busID).setBusModel(busName)
                            .setmaxCountOfPassagers(macCountOfPassengers).setMiles(miles).setPassedService(maintence).build();
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
        return theBus;
    }

    public boolean update(String busID, int milesToChange, boolean passedServiceToChange) {
        String updateSQL = "update `mydb`.`Bus` set  maintance = ?, miles = ? where busID = ?";
        boolean wasUpdated = false;
        if (milesToChange == this.findByID(busID).getMiles() || passedServiceToChange == this.findByID(busID).isPassedService() && milesToChange >= this.findByID(busID).getMiles()) {
            return wasUpdated = false;
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setBoolean(1, passedServiceToChange);
            preparedStatement.setInt(2, milesToChange);
            preparedStatement.setString(3, busID);
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
