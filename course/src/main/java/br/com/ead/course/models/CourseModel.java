package br.com.ead.course.models;

import br.com.ead.course.enums.CourseLevel;
import br.com.ead.course.enums.CourseStatus;
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

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 250)
    private String description;

    @Column()
    private String imageUrl;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-YYYY HH:mm:ss")
    private LocalDateTime creationDate;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-YYYY HH:mm:ss")
    private LocalDateTime lastUpdateDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseLevel courseLevel;

    @Column(nullable = false)
    private UUID userInstructor;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Definir o tipo de acesso para esse atributo.
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<ModuleModel> modules;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Definir o tipo de acesso para esse atributo.
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private Set<CourseUserModel> courseUsers;


}