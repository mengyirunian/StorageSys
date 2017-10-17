package com.mengyirunian.handler;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 处理从数据库中数据读取的精度问题：防止精度损失
 */
@MappedTypes(BigDecimal.class)
public class DecimalTypeHandler implements TypeHandler<BigDecimal> {
    @Override
    public void setParameter(PreparedStatement ps, int i, BigDecimal parameter, JdbcType jdbcType) throws SQLException {
        ps.setBigDecimal(i, parameter);
    }

    @Override
    public BigDecimal getResult(ResultSet rs, String columnName) throws SQLException {
        String val = rs.getString(columnName);
        return newBigDecimal(val);
    }

    @Override
    public BigDecimal getResult(ResultSet rs, int columnIndex) throws SQLException {
        String val = rs.getString(columnIndex);
        return newBigDecimal(val);
    }

    @Override
    public BigDecimal getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return newBigDecimal(cs.getString(columnIndex));
    }

    private BigDecimal newBigDecimal(String val) {
        if (StringUtils.isEmpty(val))
            return BigDecimal.ZERO;

        return new BigDecimal(val).setScale(10, BigDecimal.ROUND_DOWN);
    }
}
