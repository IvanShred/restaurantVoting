package ru.project.restaurantVoting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import ru.project.restaurantVoting.repository.vote.VoteRepositoryImpl;

public class TestClass {
    private static final Logger log = LoggerFactory.getLogger(TestClass.class);

    public static void main(String[] args) {
        log.info("test log");
        System.out.println("test");

        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.load("spring/spring-app.xml", "spring/spring-db.xml");
            appCtx.refresh();

            VoteRepositoryImpl voteRepository = appCtx.getBean(VoteRepositoryImpl.class);
            voteRepository.getById(100012, 100000);
        }

//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
//        applicationContext.getBean("entityManagerFactory");
    }
}
