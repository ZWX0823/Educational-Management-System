package com.springmvc.controller.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换器
 * @author ZWX
 */
public class DateConverter implements Converter<String,Date> {

    @Override
    public Date convert(String source) {
        //实现将日期串转成日期类型(格式是yyyy-MM-dd HH:mm:ss)
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            //转成功直接返回
            return simpleDateFormat.parse(source);
        } catch (ParseException e) {
            // TODO: handle exception
            e.printStackTrace();

        }
        //参数绑定失败返回null
        return null;
    }
}
