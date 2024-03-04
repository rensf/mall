package com.sys.common.mybatis.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * StringToList 类型转换器
 *
 * @author rensf
 * @date 2024/2/29
 */
public class StringToListTypeHandler implements TypeHandler<List<String>> {

    @Override
    public void setParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            ps.setString(i, String.join(",", parameter));
        } else {
            ps.setNull(i, jdbcType.TYPE_CODE);
        }
    }

    @Override
    public List<String> getResult(ResultSet rs, String columnName) throws SQLException {
        String result = rs.getString(columnName);
        return result == null ? null : Arrays.asList(result.split(","));
    }

    @Override
    public List<String> getResult(ResultSet rs, int columnIndex) throws SQLException {
        String result = rs.getString(columnIndex);
        return result == null ? null : Arrays.asList(result.split(","));
    }

    @Override
    public List<String> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String result = cs.getString(columnIndex);
        return result == null ? null : Arrays.asList(result.split(","));
    }
}
