package com.dudv.student_management.repository.impl;

import com.dudv.student_management.customException.DateException;
import com.dudv.student_management.entity.StudentEntity;
import com.dudv.student_management.repository.StudentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
@Log4j2
public class StudentRepositoryImpl implements StudentRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public String save(StudentEntity studentEntity) {
        try {
            entityManager.persist(studentEntity);
            return "Add student successfully";
        }
        catch (Exception e){
            log.info(e);
            return "You have an exception while add a new student";
        }

    }
    @Override
    public String deleteById(Long id) {
        try {
            StudentEntity studentEntity = entityManager.find(StudentEntity.class, id);
            if(studentEntity != null){
                entityManager.remove(studentEntity);
                return "Delete student successfully";
            }
            else return "The student does not exist";
        }
        catch (Exception e){
            log.info(e);
            return "You have an exception while delete student";
        }
    }

    @Override
    public String update(Long id, StudentEntity studentEntity) {
        try {
            StudentEntity studentEntityDB = entityManager.find(StudentEntity.class, id);
            if(studentEntityDB != null){
                studentEntityDB.setFullName(studentEntity.getFullName());
                studentEntityDB.setGender(studentEntity.getGender());
                studentEntityDB.setHomeTown(studentEntity.getHomeTown());
                studentEntityDB.setBirthday(studentEntity.getBirthday());
                studentEntityDB.setAverageMark(studentEntity.getAverageMark());
                studentEntityDB.setClassName(studentEntity.getClassName());
                studentEntityDB.setFaculty(studentEntity.getFaculty());
                entityManager.merge(studentEntityDB);
                return "Update student successfully";
            }
            else return "The student does not exist";
        }catch (Exception e){
            log.info(e);
            return "You have an exception while update student";
        }
    }

    @Override
    public List<StudentEntity> find(Map<String, Object> params){
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM student s ");
            StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
            query(params, where);
            sql.append(where);
            return executeQuery(sql.toString());
        }
        catch (Exception e){
            log.info(e);
            return null;
        }
    }
    private void query(Map<String, Object> params, StringBuilder where){
        for(Map.Entry<String, Object> entry : params.entrySet()){
            String key = entry.getKey();
            Object value = entry.getValue();
            if(key.equals("gender") || key.equals("hometown") || key.equals("faculty") || key.equals("fullName") || key.equals("className")){
                where.append(" AND s." + key + " LIKE"   + "'%" + value +"%' ");
            }
        }

        if(params.containsKey("averageMarkFrom") || params.containsKey("averageMarkTo")){
            if(params.containsKey("averageMarkFrom")){
                where.append(" AND s.averageMark >= " + params.get("averageMarkFrom"));
            }
            if(params.containsKey("averageMarkTo")){
                where.append(" AND s.averageMark <= " + params.get("averageMarkTo"));
            }
        }

        if(params.containsKey("birthdayFrom") || params.containsKey("birthdayTo")){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            if(params.containsKey("birthdayFrom")){
                try {
                    date = sdf.parse(params.get("birthdayFrom").toString());
                } catch (ParseException e) {
                    throw new DateException("Date exception");
                }
                where.append(" AND s.birthday >= '" + sdf1.format(date) + "'");
            }
            if(params.containsKey("birthdayTo")){
                try {
                    date = sdf.parse(params.get("birthdayTo").toString());
                } catch (ParseException e) {
                    throw new DateException("Date exception");
                }
                where.append(" AND s.birthday <= '" + sdf1.format(date) + "'");
            }
        }
    }
    public List<StudentEntity> executeQuery(String sql){
        Query query = entityManager.createNativeQuery(sql, StudentEntity.class);
        return query.getResultList();
    }

    @Override
    public List<StudentEntity> getStudentBirthdayToday(String day, String month) {
        try {
            String sql = "SELECT * FROM student s WHERE DAY(s.birthday) = " + day
                    +" AND MONTH(s.birthday) = " + month;
            Query query = entityManager.createNativeQuery(sql, StudentEntity.class);
            return query.getResultList();
        }catch (Exception e){
            log.info(e);
            return null;
        }
    }
}
