package com.cain.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import com.cain.model.Person;
import com.cain.model.Samurai;
import com.google.gson.Gson;

public class mainTask {

	private static Gson gson = new Gson();

	public static void main (String...args) throws InterruptedException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit");
        EntityManager em = emf.createEntityManager();
        em.setFlushMode(FlushModeType.COMMIT);

        Person p = null;
        int batchCount = 0;
        int batchSize = 100;
        Date today = new Date();

        List<Samurai> samuraiList = new ArrayList<Samurai>();
        Long convertDataCount = em.createQuery("select count(1) from creation c", Long.class).getSingleResult();
        long maxSize = (convertDataCount != null) ? convertDataCount.longValue() : 0;

        em.getTransaction().begin();
        for (int i = 0; i < maxSize; i += batchSize){
        	samuraiList = em.createQuery("from creation c", Samurai.class)
        			        .setFirstResult(i)
        			        .setMaxResults(batchSize)
        			        .getResultList();

        	batchCount++;
        	System.out.println("batch = " + batchCount + ", process : " + i + " ~ " + (batchCount * batchSize - 1));
        	for(Samurai s : samuraiList) {
        		p = new Person();
        		p.setId(s.getId());
        		p.setName(s.getName());
        		p.setSex(s.getSex());
        		p.setModifyDate(today);
        		em.merge(p);
        		System.out.println(gson.toJson(p));
        	}

        	if (i % batchSize == 0 && i > 0) {
                em.flush();
                em.clear();
            }
        }

        em.getTransaction().commit();
        emf.close();
	}
}