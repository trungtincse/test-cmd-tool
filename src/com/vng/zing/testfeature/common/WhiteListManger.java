/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.testfeature.common;

import com.vng.zing.logger.ZLogger;
import com.vng.zing.testfeature.data.database.DataStorage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

/**
 *
 * @author tindpt
 */
public class WhiteListManger {

    private static final Logger _Logger = ZLogger.getLogger(WhiteListManger.class);
    private static BasicDataSource dataSource;
    public static WhiteListManger whiteListManger = new WhiteListManger();

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private WhiteListManger() {
        dataSource = DataSource.init();
        assert dataSource != null;
    }

    public List<String> getAllUID() {
        return DataStorage.Instance.wlRepo.find().toList().stream().map(i -> {
            return i.uid;
        }).collect(Collectors.toList());
    }

    public void addUID(String uid) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            String sql = "INSERT INTO white_list (uid)"
                    + String.format(" VALUES (%s)", uid);
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            _Logger.error(ex.getMessage(), ex);
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                _Logger.error(ex.getMessage(), ex);
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                _Logger.error(ex.getMessage(), ex);
            }//end finally try
        }
    }

    public void deleteUID(String uid) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            String sql = "DELETE FROM white_list "
                    + String.format(" WHERE uid= %s", uid);
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            _Logger.error(ex.getMessage(), ex);
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                _Logger.error(ex.getMessage(), ex);
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                _Logger.error(ex.getMessage(), ex);
            }//end finally try
        }
    }

    public boolean checkUid(String uid) {
        Statement stmt = null;
        ResultSet rs = null;
        boolean result = false;
        Connection conn = null;
        if (uid == null) {
            return false;
        }
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(String.format("SELECT * FROM white_list where uid = %s", uid));
            result = rs.next();
        } catch (SQLException ex) {
            _Logger.error(ex.getMessage(), ex);
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    _Logger.error(ex.getMessage(), ex);
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    _Logger.error(ex.getMessage(), ex);
                } // ignore

                stmt = null;
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                _Logger.error(ex.getMessage(), ex);
            }//end finally try
            return result;
        }
    }

    public void updateUID(String b_uid, String a_uid) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            String sql = String.format("UPDATE white_list SET uid=%s WHERE uid=%s", a_uid, b_uid);
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            _Logger.error(ex.getMessage(), ex);
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                _Logger.error(ex.getMessage(), ex);
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                _Logger.error(ex.getMessage(), ex);
            }//end finally try
        }
    }

    public static void main(String[] args) {
    }
}
