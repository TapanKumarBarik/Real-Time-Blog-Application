package com.springboot.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(
        name = "post",
        uniqueConstraints ={@UniqueConstraint(columnNames = {"title"})}
)
public class post {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    @Column(name = "title",nullable = false,length = 255)
    private String title;
    @Column(name = "description",nullable = false,length = 6553)
    private String  description;
    @Column(name = "content",nullable = false,length = 6553)
    private  String content;

    private Date dateCreated;
    private Date dateModified;

}
