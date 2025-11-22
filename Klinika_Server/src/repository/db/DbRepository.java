/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.db;

import java.sql.SQLException;
import repository.Repository;

/**
 *
 * @author Filip
 */
public interface DbRepository<T> extends Repository<T> {
    default public void connect() throws SQLException, ClassNotFoundException{
        DbConnectionFactory.getInstance().getConnection();
    }
    
    default public void disconnect() throws SQLException, ClassNotFoundException{
        DbConnectionFactory.getInstance().getConnection().close();
    }
    
    default public void commit() throws SQLException, ClassNotFoundException{
        DbConnectionFactory.getInstance().getConnection().commit();
    }
    
    default public void rollback() throws SQLException, ClassNotFoundException{
        DbConnectionFactory.getInstance().getConnection().rollback();
    }
}
