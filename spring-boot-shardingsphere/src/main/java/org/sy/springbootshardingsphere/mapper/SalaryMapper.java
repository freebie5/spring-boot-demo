package org.sy.springbootshardingsphere.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.sy.springbootshardingsphere.entity.Salary;

@DS("test")
public interface SalaryMapper extends BaseMapper<Salary> {
}
