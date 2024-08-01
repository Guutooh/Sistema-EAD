package br.com.ead.course.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // JsonInclude - Ao resgatar os dados do usuario por meio do GEt ele não ira apresentar os valores nulos
@Entity
@Table(name = "TB_COURSES_USERS")
public class CourseUserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) //Definir como banco de dados é carregado no BD
    private  CourseModel course;

    @Column(nullable = false)
    private UUID userId;


}
