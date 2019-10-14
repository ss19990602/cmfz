package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class picture {
    @Excel(name = "id")
    private String id;
    @Excel(name = "编号")
    private String number;
    @Excel(name = "状态")
    private String state;
    @Excel(name = "简介")
    private String describe;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加日期", format = "yyyy-MM-dd")
    private Date date;
    @Excel(name = "图片", type = 2, width = 30, height = 20)
    private String src;
    @Excel(name = "图片名字")
    private String pictureName;
}
