package by.fpmibsu.be_healthy.postgres;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl( "jdbc:postgresql://127.0.0.1:5432/be_healthy?useUnicode=true&amp;characterEncoding=utf8");
        config.setUsername( "postgres" );
        config.setPassword( "78873483mmv" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );
    }

    private DataSource() {}
    public static void makeTest(){
        config.setJdbcUrl( "jdbc:postgresql://127.0.0.1:5432/be_healthy_test?useUnicode=true&amp;characterEncoding=utf8");
        ds = new HikariDataSource( config );
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    public static void close() {
        ds.close();
    }
}
