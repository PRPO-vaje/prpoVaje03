package si.fri.popoVaje03.mappers;

import si.fri.prpoVaje03.entitete.Professor;
import si.fri.prpoVaje03.entitete.Student;
import si.fri.prpoVaje03.entitete.Topic;
import si.fri.prpoVaje03.lib.ProfessorDTO;
import si.fri.prpoVaje03.lib.StudentDTO;
import si.fri.prpoVaje03.lib.TopicDTO;

import java.util.ArrayList;
import java.util.List;

public class EntityDTOMapper {

    public static ProfessorDTO ProfessorToProfessoDTO(Professor professor) {
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setProfID(professor.getId().toString());
        professorDTO.setProfFirstName(professor.getProfessorFirstName());
        professorDTO.setProfLastName(professor.getProfessorLastName());
        professorDTO.setProfEmail(professor.getProfessorMail());
        professorDTO.setProfTopics(topicListToDTO(professor.getProfessorTopics()));
        return professorDTO;
    }

    public static ArrayList<ProfessorDTO> ProfessorListToDTO(List<Professor> professors){
        ArrayList<ProfessorDTO> ret = new ArrayList<ProfessorDTO>();
        professors.forEach((p)->ret.add(ProfessorToProfessoDTO(p)));
        return ret;
    }

    public static StudentDTO StudentToStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentID(student.getStudentId().toString());
        studentDTO.setStudentFirstName(student.getStudentFirstName());
        studentDTO.setStudentLastName(student.getStudentLastName());
        studentDTO.setStudentEmail(student.getStudentMail());
        //studentDTO.setStudentTopics(student.get());
        return studentDTO;
    }

    public static TopicDTO topicToDTO(Topic topic) {
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setTopicID(topic.getTopicId().toString());
        topicDTO.setTopicTitle(topic.getTopicTitle());
        topicDTO.setPTopicDescription(topic.getDescription());
        //topicDTO.setTopicAuthor(ProfessorToProfessoDTO(topic.getAuthor()));
        return topicDTO;
    }


    public static ArrayList<TopicDTO> topicListToDTO(List<Topic> topics){
        ArrayList<TopicDTO> ret = new ArrayList<TopicDTO>();
        topics.forEach((t)->ret.add(topicToDTO(t)));
        return ret;
    }


}
