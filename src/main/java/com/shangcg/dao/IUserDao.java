package com.shangcg.dao;

import com.shangcg.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserDao {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}