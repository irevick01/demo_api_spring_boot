package com.project.app.entities.repositories;


import com.project.app.entities.models.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel, Long> {

    UserModel findFirstByEmailOrPhone(String email, String phone);


}
