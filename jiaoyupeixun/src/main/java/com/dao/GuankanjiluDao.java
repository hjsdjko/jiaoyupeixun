package com.dao;

import com.entity.GuankanjiluEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.GuankanjiluView;

/**
 * 观看进度 Dao 接口
 *
 * @author 
 */
public interface GuankanjiluDao extends BaseMapper<GuankanjiluEntity> {

   List<GuankanjiluView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
