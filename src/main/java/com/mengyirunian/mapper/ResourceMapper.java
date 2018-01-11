package com.mengyirunian.mapper;

import com.mengyirunian.entity.Resource;
import com.mengyirunian.entity.ResourceCriteria;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

public interface ResourceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resource
     *
     * @mbggenerated Fri Jan 12 02:20:54 CST 2018
     */
    @SelectProvider(type=ResourceSqlProvider.class, method="countByExample")
    int countByExample(ResourceCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resource
     *
     * @mbggenerated Fri Jan 12 02:20:54 CST 2018
     */
    @DeleteProvider(type=ResourceSqlProvider.class, method="deleteByExample")
    int deleteByExample(ResourceCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resource
     *
     * @mbggenerated Fri Jan 12 02:20:54 CST 2018
     */
    @Delete({
        "delete from resource",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resource
     *
     * @mbggenerated Fri Jan 12 02:20:54 CST 2018
     */
    @Insert({
        "insert into resource (func_parent, func_action, ",
        "func_parent_name, func_action_name, ",
        "valid, create_at, ",
        "update_at)",
        "values (#{funcParent,jdbcType=VARCHAR}, #{funcAction,jdbcType=VARCHAR}, ",
        "#{funcParentName,jdbcType=VARCHAR}, #{funcActionName,jdbcType=VARCHAR}, ",
        "#{valid,jdbcType=INTEGER}, #{createAt,jdbcType=TIMESTAMP}, ",
        "#{updateAt,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Resource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resource
     *
     * @mbggenerated Fri Jan 12 02:20:54 CST 2018
     */
    @InsertProvider(type=ResourceSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(Resource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resource
     *
     * @mbggenerated Fri Jan 12 02:20:54 CST 2018
     */
    @SelectProvider(type=ResourceSqlProvider.class, method="selectByExample")
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="func_parent", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="func_action", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="func_parent_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="func_action_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="valid", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="create_at", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="update_at", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP)
    })
    List<Resource> selectByExampleWithRowbounds(ResourceCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resource
     *
     * @mbggenerated Fri Jan 12 02:20:54 CST 2018
     */
    @SelectProvider(type=ResourceSqlProvider.class, method="selectByExample")
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="func_parent", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="func_action", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="func_parent_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="func_action_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="valid", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="create_at", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="update_at", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP)
    })
    List<Resource> selectByExample(ResourceCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resource
     *
     * @mbggenerated Fri Jan 12 02:20:54 CST 2018
     */
    @Select({
        "select",
        "id, func_parent, func_action, func_parent_name, func_action_name, valid, create_at, ",
        "update_at",
        "from resource",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="func_parent", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="func_action", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="func_parent_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="func_action_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="valid", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="create_at", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="update_at", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP)
    })
    Resource selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resource
     *
     * @mbggenerated Fri Jan 12 02:20:54 CST 2018
     */
    @UpdateProvider(type=ResourceSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resource
     *
     * @mbggenerated Fri Jan 12 02:20:54 CST 2018
     */
    @UpdateProvider(type=ResourceSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Resource record, @Param("example") ResourceCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resource
     *
     * @mbggenerated Fri Jan 12 02:20:54 CST 2018
     */
    @UpdateProvider(type=ResourceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Resource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resource
     *
     * @mbggenerated Fri Jan 12 02:20:54 CST 2018
     */
    @Update({
        "update resource",
        "set func_parent = #{funcParent,jdbcType=VARCHAR},",
          "func_action = #{funcAction,jdbcType=VARCHAR},",
          "func_parent_name = #{funcParentName,jdbcType=VARCHAR},",
          "func_action_name = #{funcActionName,jdbcType=VARCHAR},",
          "valid = #{valid,jdbcType=INTEGER},",
          "create_at = #{createAt,jdbcType=TIMESTAMP},",
          "update_at = #{updateAt,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Resource record);
}