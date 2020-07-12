package com.ning.springcloud.entities.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Author: nicholas
 * @Date: 2020/7/12 16:30
 * @Descreption:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class Payment {
    private Long id;
    private String serial;
}
