/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.testfeature.auth;

import com.vng.zing.logger.ZLogger;
import com.vng.zing.testfeature.common.DataSource;
import com.vng.zing.testfeature.data.database.DataStorage;
import com.vng.zing.testfeature.data.model.Feature;
import com.vng.zing.testfeature.data.model.Role;
import com.vng.zing.testfeature.data.model.User;
import static com.vng.zing.testfeature.model.BaseModel.IS_AUTHEN;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectFilter;
import static org.dizitart.no2.objects.filters.ObjectFilters.and;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author tindpt
 */
public class UserManager {

    private static final Logger _Logger = ZLogger.getLogger(UserManager.class);
    private static BasicDataSource dataSource;
    public static UserManager userManager = new UserManager();

    private UserManager() {
        dataSource = DataSource.init();
    }

    public boolean checkHavingUser(String uid) {
        Statement stmt = null;
        ResultSet rs = null;
        boolean result = false;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(String.format("SELECT * FROM users where uid = %s", uid));
            result = rs.next();
        } catch (SQLException ex) {
            _Logger.error(ex.getMessage(), ex);
        } finally {
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
            }
            return result;
        }
    }

    public String getName(String uid) {
        Statement stmt = null;
        ResultSet rs = null;
        String result = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(String.format("SELECT * FROM users where uid = %s", uid));
            if (rs.next()) {
                result = rs.getString("name");
            }
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
            }
            return result;
        }
    }

    public String getFeaOfCMD(String cmd) {
        Statement stmt = null;
        ResultSet rs = null;
        String result = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(String.format("SELECT slug_name FROM commands inner join features on commands.feature_id=features.feature_id  where number = %s ", cmd));
            if (rs.next()) {
                result = rs.getString("slug_name");
            }
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
            }
            return result;
        }
    }

    public List<Feature> getAccessableTable(String uid) {
        if (checkAdmin(uid)) {
            return DataStorage.Instance.feaRepo.find().toList();
        } else {
            Role roleIns = DataStorage.Instance.roleRepo.find(eq("uid", uid)).firstOrDefault();
            if (roleIns != null) {
                return roleIns.getFeaLst();
            }
            return new ArrayList<Feature>();
        }
    }

    public String getUrl(String uid) {
        Statement stmt = null;
        ResultSet rs = null;
        String result = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(String.format("SELECT * FROM users where uid = %s", uid));
            if (rs.next()) {
                result = rs.getString("avatar");
            }
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
            }
            return result;
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void addUser(String uid, String name, String ava_url) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            String sql = "INSERT INTO users "
                    + String.format("VALUES (%s, '%s', '%s')", uid, name, ava_url);
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

    public void addRole(String uid, String role) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            String sql = "INSERT INTO roles (uid,role,accessible_table)"
                    + String.format("VALUES (%s, '%s','')", uid, role);
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

    public JSONArray getUserARole() {
        Statement stmt = null;
        ResultSet rs = null;
        JSONArray result = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT users.uid,name,role,accessible_table FROM users join roles on users.uid=roles.uid");
            result = new JSONArray();
            while (rs.next()) {
                JSONObject json = new JSONObject();
                json.put("uid", rs.getString("users.uid"));
                json.put("name", rs.getString("name"));
                json.put("role", rs.getString("role"));
                json.put("accessible_table", rs.getString("accessible_table"));
                result.put(json);
            }
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
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
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

    public void updateRole(String uid, String role, String[] permission) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            String sql = null;
            if (permission.length > 0) {
                sql = String.format("UPDATE roles SET role='%s', accessible_table='%s' WHERE uid=%s", role, String.join(",", permission), uid);
            } else {
                sql = String.format("UPDATE roles SET role='%s', accessible_table='' WHERE uid=%s", role, uid);
            }
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

    public boolean checkTester(String uid) {
        Cursor<User> cursor = DataStorage.Instance.userRepo.find(and(
                eq("uid", uid),
                eq("role", Role.RoleName.TESTER)));
        return cursor.hasMore();
    }

    public boolean checkGuest(String uid) {
        Cursor<User> cursor = DataStorage.Instance.userRepo.find(and(
                eq("uid", uid),
                eq("role", Role.RoleName.GUEST)));
        return cursor.hasMore();
    }

    public boolean checkAdmin(String uid) {
        if(!IS_AUTHEN) return true;
        Cursor<User> cursor = DataStorage.Instance.userRepo.find(and(
                eq("uid", uid),
                eq("role", Role.RoleName.ADMIN)));
        return cursor.hasMore();
    }

    public static void main(String[] args) {
        List accessableTable = UserManager.userManager.getAccessableTable("185230354");
    }
}
