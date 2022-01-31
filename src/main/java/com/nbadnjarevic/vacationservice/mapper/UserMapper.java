package com.nbadnjarevic.vacationservice.mapper;

import com.nbadnjarevic.vacationservice.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends BaseMapper<User> {

  @Select("select count(*) from users")
  public long count();

  @Select("select id, created_on createdOn, modified_on modifiedOn, username, password, role from users where id = #{id}")
  public User getById(@Param("id") Long id);

  @Insert("insert into users(id, created_on, modified_on, username, password, role) values (#{id}, current_timestamp, current_timestamp, #{username}, #{password}, #{role})")
  @SelectKey(before = true, statement = "select sq_users.nextval from dual", keyProperty = "id", resultType = Long.class)
  public int insert(User entity);

  @Update("update users set modified_on=current_timestamp, username=#{username}, password=#{password}, role=#{role} where id = #{id}")
  public int update(User entity);

  @Delete("delete from users where id = #{id}")
  public int deleteById(@Param("id") Long id);

}
