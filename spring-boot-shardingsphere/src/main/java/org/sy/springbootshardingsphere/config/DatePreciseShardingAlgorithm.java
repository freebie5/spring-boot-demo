package org.sy.springbootshardingsphere.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.joda.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

@Slf4j
public class DatePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Date> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {
        String loginTableName = shardingValue.getLogicTableName();
        Date createTime = shardingValue.getValue();
        String yyyyMM= "201909";
        try{
            yyyyMM = LocalDate.fromDateFields(createTime).toString("yyyyMM", Locale.CHINA);
        }catch(Exception e){
            log.error("解析创建时间异常，分表失败，进入默认表");
        }
        return loginTableName+yyyyMM;
    }

}
