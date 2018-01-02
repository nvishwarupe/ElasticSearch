package com.esplay.prop;
/*
 * Created by nvishwarupe on 01/12/2017
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Index {
    private String name;
    private String type;
    private int from;
    private int size;
    private int timeout;
}
