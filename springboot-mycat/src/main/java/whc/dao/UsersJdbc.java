package whc.dao;

import org.springframework.stereotype.Component;
import whc.entity.Users;

import javax.activation.UnsupportedDataTypeException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 注意：使用自己的jdbc连接是可以实现分库分表的
 *
 * @Author Administrator
 * @Date 2019/7/7 10:20
 */
@Component
public class UsersJdbc {
    /**
     * 查询列表
     *
     * @param
     * @return
     * @throws
     */
    @SuppressWarnings("unchecked")
    public List<Users> executeQuery(String sql) throws Exception {
        return executeQuery(sql, Collections.EMPTY_LIST);
    }

    /**
     * 根据条件查询列表
     *
     * @param
     * @return
     * @throws
     */
    public List<Users> executeQuery(String sql, List<Object> params) throws Exception {
        List<Users> usersList = new ArrayList<Users>();
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement statement = cn.prepareStatement(sql)) {
            setParams(statement, params);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Users users = new Users(rs.getLong("id"), rs.getString("name"), rs.getInt("age"),
                        rs.getString("email"));
                usersList.add(users);
            }
        }
        return usersList;
    }

    private void setParams(PreparedStatement statement, List<Object> params) throws Exception {
        for (int i = 0; i < params.size(); i++) {
            Object value = params.get(i);
            if (value instanceof Integer) {
                statement.setInt(i + 1, (int) value);
            } else if (value instanceof Long) {
                statement.setLong(i + 1, (long) value);
            } else if (value instanceof String) {
                statement.setString(i + 1, value.toString());
            } else if (value instanceof BigDecimal) {
                statement.setBigDecimal(i + 1, (BigDecimal) value);
            } else if (value instanceof Date) {
                statement.setDate(i + 1, (java.sql.Date) value);
            } else {
                throw new UnsupportedDataTypeException("不支持的参数类型!");
            }
        }
    }

    /**
     * 测试分布式事务
     *
     * @throws Exception
     */
    public void executeUpdateTransaction() throws Exception {
        try (Connection cn = ConnectionManager.getConnection()) {
            cn.setAutoCommit(false);
            try {
                String sql1 = "insert into t_users(id, name, age, email) values(400002,'admin',20,'@qq.com')";// 让它成功
                String sql2 = "insert into t_users(id, name, age, email) values(1,'whc',30,'@163.com')";// 让它失败
                PreparedStatement statement1 = cn.prepareStatement(sql1);
                PreparedStatement statement2 = cn.prepareStatement(sql2);
                statement1.executeUpdate();
                statement2.executeUpdate();
                cn.commit();
            } catch (Exception e) {
                cn.rollback();
                throw new RuntimeException(e);
            } finally {
                cn.setAutoCommit(true);
            }
        }
    }

    public void executeUpdate(String sql, List<Object> params) throws Exception {
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement statement = cn.prepareStatement(sql)) {
            setParams(statement, params);
            statement.executeUpdate();
        }
    }


    public Object executeScalar(String sql) throws Exception {
        throw new UnsupportedOperationException("TODO-");
    }

    public Object executeScalar(String sql, List<Object> params) throws Exception {
        throw new UnsupportedOperationException("TODO-");
    }
}
