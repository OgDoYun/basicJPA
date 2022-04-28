package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // update Member
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("YelloA");

            // getList
//            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();

            // pagination
            List<Member> result = em.createQuery("select m from Member as m", Member.class).setFirstResult(1).setMaxResults(2).getResultList();
            for (Member member: result) {
                System.out.println("member.name = " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
