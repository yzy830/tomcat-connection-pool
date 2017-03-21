package org.apache.tomcat.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.tomcat.jdbc.pool.DataSource;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        DataSource pool = new DataSource();
        
        pool = new org.apache.tomcat.jdbc.pool.DataSource();        
        pool.setDriverClassName("com.mysql.jdbc.Driver");
        pool.setUrl("jdbc:mysql://192.168.7.247:3306/pxsj?characterEncoding=utf8&noAccessToProcedureBodies=true&logger=org.apache.tomcat.jdbc.MySqlLogger&profileSQL=true");
        pool.setUsername("pxsjuser");
        pool.setPassword("pxsj_123");
        
        pool.setDefaultAutoCommit(true);
        pool.setInitialSize(10);
        pool.setMaxActive(100);
        pool.setMaxIdle(30);
        pool.setMinIdle(10);
        pool.setTestOnBorrow(true);
        pool.setTestOnReturn(false);
        pool.setTestWhileIdle(true);
        pool.setValidationQuery("select 1");
        pool.setValidationInterval(30000);
        pool.setTimeBetweenEvictionRunsMillis(30000);
        pool.setMinEvictableIdleTimeMillis(30000);
        pool.setNumTestsPerEvictionRun(30);
        pool.setRemoveAbandoned(true);
        pool.setRemoveAbandonedTimeout(60);
        pool.setLogAbandoned(true);
        pool.setDefaultTransactionIsolation(2);
        pool.setUseEquals(false);
        
        try(Connection conn = pool.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select * from pxsj_soa.t_d_order_base where order_id = 15")) {
            
            System.out.println("connection is closed: " + conn.isClosed());
            
            while(rs.next()) {
                System.out.println(rs.getLong("order_id"));
                System.out.println(rs.getString("order_no"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
