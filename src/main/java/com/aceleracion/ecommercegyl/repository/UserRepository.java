package com.aceleracion.ecommercegyl.repository;

import com.aceleracion.ecommercegyl.dto.request.RoleRequestDTO;
import com.aceleracion.ecommercegyl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByDni(String dni);
    Optional<User> findUserByName(String name);
    Optional<User> findUserByUserName(String userName);
    @Modifying
    @Query("update User set role=:role where userName = :username")
    void updateUserRole(@Param("username") String username, @Param("role") RoleRequestDTO role);
    User findUserByNameAndLastName(String name,String lastname);
    List<User> findAllByStatusTrue();

    @Query("select u from User u where u.branch.branchId = :branchId and u.status = true")
    List<User> findAllUserByBranchAndStatusTrue(Long branchId);
    @Query("select u from User u where u.branch.branchId = :branchId")
    List<User> findAllUserByBranch(Long branchId);


}
