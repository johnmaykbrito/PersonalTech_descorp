package com.mycompany.personaltech;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AlunoTest {

    private static EntityManagerFactory emf;
    private static Logger logger;
    private EntityManager em;
    private EntityTransaction et;

    public AlunoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        logger = Logger.getGlobal();
        logger.setLevel(Level.INFO);
        emf = Persistence.createEntityManagerFactory("PersonalTech_PU");
        DbUnitUtil.inserirDados();
    }

    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    @Before
    public void setUp() {
        em = emf.createEntityManager();
        beginTransaction();
    }

    @After
    public void tearDown() {
        commitTransaction();
        em.close();
    }

    private void beginTransaction() {
        et = em.getTransaction();
        et.begin();
    }

    private void commitTransaction() {
        try {
            et.commit();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            if (et.isActive()) {
                et.rollback();
            }
            fail(ex.getMessage());
        }
    }

    // 1ª apresentação
    @Test
    public void inserirAluno_01() {
        Aluno aluno = new Aluno();
        aluno.setNome("KELLY");
        aluno.setSobrenome("GÜIÇA");
        aluno.setCpf("567.031.524-38");
        aluno.setLogin("kelly1");
        aluno.setSenha("aA1-personal");
        aluno.setEmail("KELLY@gmail");
        aluno.setSexo("F");

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2000);
        c.set(Calendar.MONTH, Calendar.AUGUST);
        c.set(Calendar.DAY_OF_MONTH, 27);
        aluno.setDataNascimento(c.getTime());

        Endereco end = new Endereco();
        end.setLogradouro("RUA DO CORDEIRO");
        end.setBairro("CORDEIRO");
        end.setNumero(666);
        end.setCep("123456-88");
        end.setCidade("RECIFE");
        end.setEstado("PE");

        aluno.setEndereco(end);

        em.persist(aluno);

        em.flush();
        assertNotNull(aluno.getId());

    }

    @Test
    public void inserirAluno_02() {
        Aluno aluno = new Aluno();
        aluno.setNome("KEN");
        aluno.setSobrenome("GALINDA");
        aluno.setCpf("999.331.824-80");
        aluno.setLogin("kennygg");
        aluno.setSenha("aA1-personal");
        aluno.setEmail("KENNYH@gmail");
        aluno.setSexo("F");
        aluno.addTelefone("111 222 333");

        Exercicio ex = new Exercicio();
        ex.setExercicio(NomeExercicio.COXAS_45_STIFF_BARRA);
        ex.setTipo(TipoExercicio.COXAS);
        aluno.addExercicio(ex);

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2000);
        c.set(Calendar.MONTH, Calendar.AUGUST);
        c.set(Calendar.DAY_OF_MONTH, 27);
        aluno.setDataNascimento(c.getTime());

        Endereco end = new Endereco();
        end.setLogradouro("RUA DO CORDEIRO");
        end.setBairro("CORDEIRO");
        end.setNumero(666);
        end.setCep("123456-88");
        end.setCidade("RECIFE");
        end.setEstado("PE");

        aluno.setEndereco(end);

        em.persist(aluno);

        em.flush();
        assertNotNull(em.find(Aluno.class, (long) 38));
    }

    @Test
    public void atualizarAluno_01() {
        Aluno aluno = em.find(Aluno.class, (long) 24);
        assertNotNull(aluno);
        aluno.setEmail("email@hotmail.com");
        em.flush();
        em.clear();
        logger.log(Level.INFO, "selecionarAlunoPorId: Aluno {0}", aluno.getNome());
        aluno = em.find(Aluno.class, (long) 24);
        assertEquals("email@hotmail.com", aluno.getEmail());
    }

    @Test
    public void atualizarAluno_02() {
        Aluno aluno = em.find(Aluno.class, (long) 27);
        assertNotNull(aluno);
        aluno.setEmail("email@hotmail.com");
        em.flush();
        em.clear();
        logger.log(Level.INFO, "selecionarAlunoPorId: Aluno {0}", aluno.getNome());
        aluno = em.find(Aluno.class, (long) 24);
        assertEquals("email@hotmail.com", aluno.getEmail());
    }

    @Test
    public void selecionarAlunoPorCPF_01() {
        String jpql = "SELECT a FROM Aluno a where a.cpf = ?1";
        Query query = em.createQuery(jpql);
        query.setParameter(1, "456.636.524-77");

        Aluno aluno = (Aluno) query.getSingleResult();

        assertEquals("456.636.524-77", aluno.getCpf());
        logger.log(Level.INFO, "selecionarAlunoPorId: Aluno {0}", aluno.toString());
    }

    @Test
    public void selecionarAlunoPorId() {
        Aluno aluno = em.find(Aluno.class, (long) 29);
        assertNotNull(aluno);
        assertEquals("MICHEL", aluno.getNome());
        String nome = aluno.getNome();
        assertEquals("111.688.604-90", aluno.getCpf());
        logger.log(Level.INFO, "selecionarAlunoPorId: Aluno {0}", aluno.toString());
    }

    @Test
    public void deletarAluno_01() {
        Aluno aluno = em.find(Aluno.class, (long) 4);
        assertNotNull(aluno);
        em.remove(aluno);
        em.flush();
        em.clear();
        aluno = em.find(Aluno.class, (long) 4);
        assertNull(aluno);
    }

    @Test
    public void deletarAluno_02() {
        Aluno aluno = em.find(Aluno.class, (long) 36);
        em.remove(aluno);
        em.flush();
        em.clear();
        assertEquals(null, em.find(Aluno.class, (long) 36));
    }

    @Test
    public void selecionarAvaliacoes() {
        String jpql = "SELECT COUNT(a) FROM Aluno a";
        Query query = em.createQuery(jpql);

        Long alunos = (Long) query.getSingleResult();

        assertTrue(alunos == 30);
    }
}
