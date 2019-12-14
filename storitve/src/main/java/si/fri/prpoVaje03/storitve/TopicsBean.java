package si.fri.prpoVaje03.storitve;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpoVaje03.entitete.FileUpload;
import si.fri.prpoVaje03.entitete.Professor;
import si.fri.prpoVaje03.entitete.Topic;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;

@ApplicationScoped
public class TopicsBean {

    private static final Logger LOGGER = Logger.getLogger(TopicsBean.class.getName());

    private static String storageUrl;
    private static String storageBucket;

    @PostConstruct
    public void init(){
        LOGGER.log(Level.INFO, "initialized");
        storageUrl = ConfigurationUtil.getInstance().get("kumuluzee.storage.base-url").orElse("");
        storageBucket = ConfigurationUtil.getInstance().get("kumuluzee.storage.bucket").orElse("");
    }

    @PreDestroy
    public void destory(){
        LOGGER.log(Level.INFO, "destroyed");
    }

    @PersistenceContext(unitName = "prpoVaje03-JPA")
    private EntityManager em;

    public List<Topic> getTopics(QueryParameters queryParams) {
        List<Topic> topics = JPAUtils.queryEntities(em, Topic.class, queryParams);
        topics.forEach(x -> System.out.println(x.getFiles().toString()));
        return topics;
    }

    public long getTopicsCount(QueryParameters queryParams) {
        long topicsNum = JPAUtils.queryEntitiesCount(em, Topic.class, queryParams);
        return topicsNum;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Topic addFile(int id, String filename, String key) {
        Topic topic = em.find(Topic.class, id);

        FileUpload file = new FileUpload();
        file.setKey(key);
        file.setName(filename);
        file.setTopic(topic);
        file.setUrl(storageUrl + storageBucket + "/" + key);

        topic.getFiles().add(file);

        em.persist(topic);
        em.flush();

        return topic;
    }

    public List<Topic> getTopics() {
        List<Topic> topics = em.createNamedQuery("Topic.getAll").getResultList();
        topics.forEach(x -> System.out.println(x.getFiles().toString()));
        return topics;
    }

    public Topic getTopic(int id) {
        return em.find(Topic.class, id);
    }

    public List<Topic> getTopics(String name, String surname) {
        return em.createNamedQuery("Topic.getByAuthor").setParameter(1, name).setParameter(2, surname).getResultList();
    }

    public void createTopic(Topic t) {
        em.persist(t);
    }

    public void updateTopic(Topic t) {
        em.merge(t);
    }

    public void deleteTopic(int id) {
        em.remove(em.find(Topic.class, id));
    }

}