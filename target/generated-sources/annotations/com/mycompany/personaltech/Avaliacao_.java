package com.mycompany.personaltech;

import com.mycompany.personaltech.Aluno;
import com.mycompany.personaltech.RespostasAvaliacao;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-02T10:28:59")
@StaticMetamodel(Avaliacao.class)
public class Avaliacao_ { 

    public static volatile SingularAttribute<Avaliacao, Aluno> aluno;
    public static volatile SingularAttribute<Avaliacao, Date> dataAvaliacao;
    public static volatile SingularAttribute<Avaliacao, String> nome_personal;
    public static volatile SingularAttribute<Avaliacao, Long> id;
    public static volatile ListAttribute<Avaliacao, RespostasAvaliacao> respostas;

}