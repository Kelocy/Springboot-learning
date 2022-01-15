package com.cy.store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class StoreApplicationTests {
    @Autowired  // 自动装配
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

    /*
     * 数据连接池
     * Hikari: 管理数据库的连接对象
     * HikariProxyConnection@914629851 wrapping com.mysql.cj.jdbc.ConnectionImpl@44864536
     */

    @Test
    void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }


}
