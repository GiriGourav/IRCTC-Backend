package com.substring.irctc;

import com.substring.irctc.entity.Role;
import com.substring.irctc.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class IrctcBackendApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(IrctcBackendApplication.class, args);
    }

    @Autowired
    RoleRepo roleRepo;

        @Override
        public void run(String... args) throws Exception{

            if(!roleRepo.existsByName("ROLE_ADMIN")){
                Role adminRole=new Role();
               adminRole.setId(UUID.randomUUID().toString());
               adminRole.setName("ROLE_ADMIN");
                roleRepo.save(adminRole);
            }



            if(!roleRepo.existsByName("ROLE_NORMAL")){
                Role normalRole=new Role();
                normalRole.setId(UUID.randomUUID().toString());
                normalRole.setName("ROLE_NORMAL");
                roleRepo.save(normalRole);
            }
            System.out.println("Roles Initialized Successfully");

}


}
