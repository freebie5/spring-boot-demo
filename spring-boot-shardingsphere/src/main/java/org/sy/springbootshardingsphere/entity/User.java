package org.sy.springbootshardingsphere.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class User {

    private long userId;
    private String name;
    private Date registerDate;
    private long id;

}
