package si.fri.prpoVaje03.storitve;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.popoVaje03.mappers.EntityDTOMapper;
import si.fri.prpoVaje03.entitete.FileUpload;
import si.fri.prpoVaje03.entitete.Topic;
import si.fri.prpoVaje03.lib.ProfessorDTO;
import si.fri.prpoVaje03.lib.TopicDTO;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
public class TopicsManagerBean {

    private static final Logger LOGGER = Logger.getLogger(TopicsManagerBean.class.getName());

    private java.util.UUID UUID = java.util.UUID.randomUUID();

    @PostConstruct
    public void init(){
        LOGGER.log(Level.INFO, "initialized UUID: " + UUID);
    }

    @PreDestroy
    public void destory(){
        LOGGER.log(Level.INFO, "destroyed UUID: " + UUID);
    }

    @Inject
    private TopicsBean repo;

    @Inject
    private StorageBean storage;

    public List<TopicDTO> getAll(QueryParameters queryParams) {
        //return EntityDTOMapper.ProfessorListToDTO(repo.getProfessorsCriteriaAPI());
        return EntityDTOMapper.topicListToDTO(repo.getTopics(queryParams));
    }

    public long getAllCount(QueryParameters queryParams) {
        return repo.getTopicsCount(queryParams);
    }


    public TopicDTO addFile(int topicId, String filename, InputStream file) throws Exception {
        String key = java.util.UUID.randomUUID().toString();
        Response res = storage.SaveFile(file, key);
        LOGGER.log(Level.INFO, res.toString());
        if (res.getStatus() != 200) {
            throw new Exception("Failed to upload a file");
        }
        Topic topic = repo.addFile(topicId, filename, key);
        return EntityDTOMapper.topicToDTO(topic);
    }

}
