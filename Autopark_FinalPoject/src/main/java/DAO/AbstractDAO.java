package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AbstractDAO<K, T> {

    /**
     * Basic class, which is to be implemented by ALL DAO classes
     *
     * @param <K> key type
     * @param <T> model type
     */

    boolean addRecord(T anEntity) throws SQLException;

    boolean deleteRecord(K anID) throws SQLException;

    ArrayList<T> findAll() throws SQLException;

    T findByID(K anID) throws SQLException;

    T findByName(K aName) throws SQLException;

    }
