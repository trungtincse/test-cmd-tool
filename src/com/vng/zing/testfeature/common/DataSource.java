/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.testfeature.common;

import com.vng.zing.configer.ZConfig;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author tindpt
 */
public class DataSource {
        public static BasicDataSource init() {
        String user = ZConfig.Instance.getString(DataSource.class, "main", "user", "");
        String password = ZConfig.Instance.getString(DataSource.class, "main", "password", "");
        String dbname = ZConfig.Instance.getString(DataSource.class, "main", "dbname", "dev_command_server");
        String host = ZConfig.Instance.getString(DataSource.class, "main", "host", "localhost:80");
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        String url = String.format("jdbc:mysql://%s/%s?useUnicode=yes&characterEncoding=UTF-8", host, dbname);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }
}
