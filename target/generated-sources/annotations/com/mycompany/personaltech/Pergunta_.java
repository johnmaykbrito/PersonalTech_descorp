package com.mycompany.personaltech;

import com.mycompany.personaltech.RespostasAvaliacao;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-13T18:10:07")
@StaticMetamodel(Pergunta.class)
public class Pergunta_ { 

    public static volatile SingularAttribute<Pergunta, String> pergunta;
    public static volatile SingularAttribute<Pergunta, Long> id;
    public static volatile ListAttribute<Pergunta, RespostasAvaliacao> respostas;

}