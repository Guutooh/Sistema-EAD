package com.ead.course.model;

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
// JsonInclude - Ao resgatar os dados do usuario por meio do GEt ele não ira apresentar os valores nulos
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_MODULOS")

public class ModuloModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID moduloId;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false,  length = 250)
    private String description;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-YYYY HH:mm:ss")
    private LocalDateTime creationDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)// campo vai ser ignorado para casos de consultas.
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CourseModel course; //chave estrangeira


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // campo vai ser ignorado para casos de consultas.
    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<LessonModel> lessons;


}
