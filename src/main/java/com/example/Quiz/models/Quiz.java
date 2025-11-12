package com.example.Quiz.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="quizzes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Quiz  implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(length=30 ,nullable=false)

    private String title;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="created_by",nullable=false)
    private User createdby;

    

    private String description;
    
    @Column(nullable=false)
    private boolean approved;

    


}
