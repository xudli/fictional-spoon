package com.andlinks.demo4j.mapper;

import com.andlinks.demo4j.model.RoleDO;
import com.andlinks.demo4j.model.UserDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 王凯斌 on 2017/3/1.
 * 用户mapper类
 */
@Mapper
public interface UserMapper {

    @Select("select user.id, user.user_name, user.password from demo4j.user where user.id = #{id}")
    @Results(value = {
            @Result(property="userName", column="user_name"),
            @Result(property="password", column="password"),
            @Result(property="roles", javaType=List.class, column="id",
                    many=@Many(select="listRoles"))
    })
    UserDO getById(@Param("id")Long id);

    @Select("select user.id, user.user_name, user.password from demo4j.user")
    @Results(value = {
            @Result(property="userName", column="user_name"),
            @Result(property="password", column="password"),
            @Result(property="roles", javaType=List.class, column="id",
                    many=@Many(select="listRoles"))
    })
    List<UserDO> list();

    @Select("select r.id from role r left join user_role u on r.id = u.role_id where u.user_id = #{id}")
    List<RoleDO> listRoles(Long id);
}


