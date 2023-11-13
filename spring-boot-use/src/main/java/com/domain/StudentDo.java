package com.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @Entity : 表明这是一个实体类，要与数据库做orm映射，默认表的名字就是类名，表中的字段就是类中的属性。
 * 它的定义如下：其中name属性表示用JPQL语句时写的表的名字，如果没有在@Table注解中指定表名，
 * 这个名字也将作为表名映射到数据库
 * @Table : 实体类与其映射的数据库表名不同名时需要使用 @Table注解说明，
 * 该标注与 @Entity 注解并列使用，置于实体类声明语句之前，可写于单独语句行，也可与声明语句同行。
 *
 * @Column() : 注释定义了将成员属性映射到关系表中的哪一列和该列的结构信息
 *
 * @Id : 主键
 *
 * @GeneratedValue :
 *
 */
@Data
@Entity
@Table(name = "student")
public class StudentDo {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "sex")
    private String sex;


}
