package com.ead.course.model;

import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // JsonInclude - Ao resgatar os dados do usuario por meio do GEt ele não ira apresentar os valores nulos
@Entity
@Table(name = "TB_COURSES")
public class CourseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID courseId;

    @Column(nullable = false,  length = 150)
    private String name;

    @Column(nullable = false,  length = 250)
    private String description;

    @Column()
    private String imageUrl;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-YYYY HH:mm:ss")
    private LocalDateTime creationDate;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-YYYY HH:mm:ss")
    private LocalDateTime lastUpdateDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseLevel courseLevel;

    @Column(nullable = false)
    private UUID userInstrutor;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // definir o tipo de acesso para esse tipo especifico, vai mostrar apenas se estiver uma escrita
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY) //, cascade = CascadeType.ALL, orphanRemoval = true) // atributo que vai ligar com a chave estrangeira do outro lado
    @Fetch(FetchMode.SUBSELECT)//
    //@OnDelete(action = OnDeleteAction.CASCADE)O BD que fica responsavel pelas deleções, Mas não temos controle do que realmente está sendo deletado
    private Set<ModuloModel> modulos;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private Set<CourseUserModel> coursesUsers;


}
