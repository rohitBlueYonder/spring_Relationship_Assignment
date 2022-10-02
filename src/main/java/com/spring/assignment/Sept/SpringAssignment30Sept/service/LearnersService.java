package com.spring.assignment.Sept.SpringAssignment30Sept.service;

import com.spring.assignment.Sept.SpringAssignment30Sept.entity.Courses;
import com.spring.assignment.Sept.SpringAssignment30Sept.entity.Learners;
import com.spring.assignment.Sept.SpringAssignment30Sept.repository.LearnersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LearnersService {

    @Autowired
    LearnersRepository lrepo;

    // add the learner
    public Learners addLearner(Learners learner){
        return lrepo.save(learner);
    }

    // get learner by Id
    public Learners getLearnerById(int learner_id){
        Optional<Learners> tempLearner = lrepo.findById(learner_id);

        if(tempLearner.isEmpty()) throw new RuntimeException("Learner with id : "+learner_id+" NOT FOUND !!");

        return tempLearner.get();
    }

    // Get all Learners
    public List<Learners> getAllLearners(){
        return lrepo.findAll();
    }


    // Count all learners
    public int countLearners(){
        return (int) lrepo.count();
    }

    // delete learner by id
    public void deleteLearner(int learner_id){
        Optional<Learners> tempLearner = lrepo.findById(learner_id);

        if(tempLearner.isEmpty()) throw new RuntimeException("Learner with id : "+learner_id+" NOT FOUND !!");

        lrepo.delete(tempLearner.get());
    }

    // Learner with given id exists or not
    public boolean doesLearnerExists(int learner_id){

        Optional<Learners> tempLearner = lrepo.findById(learner_id);

        if(tempLearner.isEmpty()) return false;
        return true;
    }

    // Grabbing Course object into Learner
    public Learners assignCourse(int learner_id, Courses course){
        Learners learner = lrepo.findById(learner_id).get();

        learner.setCourse(course);

        return lrepo.save(learner);
    }



    //******************* Queries ********************//

    // 1
    @Transactional
    public List<Learners> findByEmailAddressAndLastname(String learner_email, String learner_last_name){
        return lrepo.findByEmailAddressAndLastname(learner_email, learner_last_name);
    }

    // 2
    @Transactional
    public List<Learners> findDistinctLearnerByLastnameOrFirstname(String learner_last_name, String learner_first_name){
        return lrepo.findDistinctLearnerByLastnameOrFirstname(learner_last_name, learner_first_name);
    }

    // 3
    @Transactional
    public List<Learners> findByLastnameIgnoreCase(String learner_last_name){
        return lrepo.findByLastnameIgnoreCase(learner_last_name);
    }

    // 4
    @Transactional
    public List<Learners> findByLastnameOrderByFirstnameAsc(String learner_last_name){
        return lrepo.findByLastnameOrderByFirstnameAsc(learner_last_name);
    }

}
