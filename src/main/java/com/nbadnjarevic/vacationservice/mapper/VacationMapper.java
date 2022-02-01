package com.nbadnjarevic.vacationservice.mapper;

import com.nbadnjarevic.vacationservice.domain.Vacation;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface VacationMapper extends BaseMapper<Vacation> {

  @Select("select count(*) from vacations")
  public long count();

  @Select("select id, created_on createdOn, modified_on modifiedOn, starting_date startingDate, length, approved, user_id userId from vacations where id = #{id}")
  public Vacation getById(@Param("id") Long id);

  @Insert("insert into vacations(id, created_on, modified_on, starting_date, length, approved, user_id) values (#{id}, current_timestamp, current_timestamp, #{username}, #{startingDate}, #{role}, #{userId})")
  @SelectKey(before = true, statement = "select sq_vacations.nextval from dual", keyProperty = "id", resultType = Long.class)
  public int insert(Vacation entity);

  @Update("update vacations set modified_on=current_timestamp, starting_date=#{startingDate}, length=#{length}, approved=#{approved} where id = #{id}")
  public int update(Vacation entity);

  @Delete("delete from vacations where id = #{id}")
  public int deleteById(@Param("id") Long id);

  @Select("select id, created_on createdOn, modified_on modifiedOn, starting_date startingDate, length, approved, user_id userId from vacations where user_id = #{id}")
  public List<Vacation> getByUserId(@Param("id") Long id);

  @Select("select id, created_on createdOn, modified_on modifiedOn, starting_date startingDate, length, approved, user_id userId from vacations")
  public List<Vacation> getAllVacations();

}
