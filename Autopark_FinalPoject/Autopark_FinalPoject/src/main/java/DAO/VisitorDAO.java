package DAO;

import DBConnection.ConnectionPool;
import Model.Visitor;
import org.apache.log4j.Logger;

import javax.servlet.jsp.jstl.sql.SQLExecutionTag;
import java.sql.*;
import java.util.ArrayList;

public class VisitorDAO  {

    private static Logger theLogger;

    static {
        theLogger = Logger.getLogger(DriverDAO.class);
    }

    public boolean addRecord(Visitor anEntity) {
        String insertSQL = "insert into `mydb`.`Visitor` values(?, ?, ?, ?, ?, ?)";
        boolean wasAdded = false;
        if (anEntity == null) {
            wasAdded = false;
        } else if (findByLogin(anEntity.getVisitorLogin()) != null) {
            System.out.println("This visitor is already in out database");
        } else {
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            Savepoint savePoint = null;
            try {
                conn = ConnectionPool.getConnection();
                conn.setAutoCommit(false);
                preparedStatement = conn.prepareStatement(insertSQL);
                preparedStatement.setInt(1, anEntity.getVisitorID());
                preparedStatement.setString(2, anEntity.getVisitorLogin());
                preparedStatement.setString(3, anEntity.getVisitorPassword());
                preparedStatement.setString(4, anEntity.getVisitorRole());
                preparedStatement.setString(5, anEntity.getVisitorName());
                preparedStatement.setString(6, anEntity.getTheDriver().getDriverID());
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
                } catch (SQLException ee) {
                }
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
                } catch (SQLException e) {
                }
            }
        }
        return wasAdded;
    }

    public boolean deleteRecord(String anID)  {
        int visitorID = Integer.valueOf(anID);
        boolean wasDeleted = false;
        String deleteSQL = "delete from `mydb`.`Visitor` where visitorID = ?";
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
                } catch (SQLException ee) {
                }
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
                } catch (SQLException e) {
                }
            }
        }
        return wasDeleted;
    }

    public ArrayList<Visitor> findAll() {
        String selectAllSQL = "select * from `mydb`.`Visitor`";
        ArrayList<Visitor> visitorList = new ArrayList<Visitor>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(selectAllSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int visitorID = resultSet.getInt("visitorID");
                String visitorLogin = resultSet.getString("login");
                String visitorPassword = resultSet.getString("password");
                String visitorName = resultSet.getString("visitorName");
                String visitorRole = resultSet.getString("visitorRole");
                String driverID = resultSet.getString("Driver_driverID");
                DriverDAO driverDAO = new DriverDAO();
                Visitor theVisitor = Visitor.newBuilder().setVisitorID(visitorID).setVisitorName(visitorName)
                        .setVisitorLogin(visitorLogin).setVisitorPassword(visitorPassword).setVisitorRole(visitorRole)
                        .setDriver(driverDAO.findByID(driverID)).build();
                visitorList.add(theVisitor);
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
        return visitorList;
    }

    public Visitor findByLogin(String anID)  {
        String selectAllSQL = "select * from `mydb`.`Visitor` where login = ?";
        Visitor theVisitor = null;
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
                String visitorLogin = resultSet.getString("login");
                if (visitorLogin.equals(anID)) {
                    int vositorID = resultSet.getInt("visitorID");
                    String visitorPassword = resultSet.getString("password");
                    String visitorRole = resultSet.getString("visitorRole");
                    String visitorName = resultSet.getString("visitorName");
                    String driverID = resultSet.getString("Driver_driverID");
                    DriverDAO driverDAO = new DriverDAO();
                    theVisitor = Visitor.newBuilder().setVisitorID(vositorID).setVisitorLogin(visitorLogin)
                            .setVisitorPassword(visitorPassword).setVisitorRole(visitorRole).setDriver(driverDAO
                                    .findByID(driverID)).setVisitorName(visitorName).build();
                }
            }
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
        return theVisitor;
    }

    public Visitor findByLoginAndPassword(String aLogin, String aPasswod)  {
        String selectAllSQL = "select * from `mydb`.`Visitor` where login = ? and password = ?";
        Visitor theVisitor = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(selectAllSQL);
            preparedStatement.setString(1, aLogin);
            preparedStatement.setString(2, aPasswod);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String visitorLogin = resultSet.getString("login");
                String visitorPassword = resultSet.getString("password");
                if (visitorLogin.equals(aLogin) && visitorPassword.equals(aPasswod)) {
                    int vositorID = resultSet.getInt("visitorID");
                    String visitorRole = resultSet.getString("visitorRole");
                    String visitorName = resultSet.getString("visitorName");
                    String driverID = resultSet.getString("Driver_driverID");
                    DriverDAO driverDAO = new DriverDAO();
                    theVisitor = Visitor.newBuilder().setVisitorID(vositorID).setVisitorLogin(visitorLogin)
                            .setVisitorPassword(visitorPassword).setVisitorRole(visitorRole).setDriver(driverDAO
                                    .findByID(driverID)).setVisitorName(visitorName).build();
                }
            }
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
        return theVisitor;
    }


    public Visitor findByName(String aName) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public boolean update(String visitorLogin, String passwordToChange) {
        String updateSQL = "update `mydb`.`Visitor` set  password = ? where login = ?";
        boolean wasUpdated = false;
        if (passwordToChange.equals(this.findByLogin(visitorLogin))) {
            return wasUpdated = false;
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, passwordToChange);
            preparedStatement.setString(2, visitorLogin);
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

    public boolean updateForAdmin(String visitorLogin, String passwordToChange, String roleToChange) {
        String updateSQL = "update `mydb`.`Visitor` set  password = ?, visitorRole = ? where login = ?";
        boolean wasUpdated = false;
        if (passwordToChange.equals(this.findByLogin(visitorLogin))) {
            return wasUpdated = false;
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Savepoint savePoint = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, passwordToChange);
            preparedStatement.setString(2, roleToChange);
            preparedStatement.setString(3, visitorLogin);
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

    public Visitor.ROLE getRoleByLoginAndPassword(String login, String password) {
        Visitor.ROLE neededRole = null;
            ArrayList<Visitor> visitorList = findAll();
            for(int i = 0; i < visitorList.size(); i++) {
                if (visitorList.get(i).getVisitorLogin().equals(login) && visitorList.get(i).getVisitorPassword().equals(password)) {
                    neededRole = visitorList.get(i).getRole();
                }
            }
        return neededRole;
    }
}
