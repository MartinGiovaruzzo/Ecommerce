package com.aceleracion.ecommercegyl.repository;

import com.aceleracion.ecommercegyl.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    Branch findByBranchIdAndStatus(Long id ,Boolean status);

    List<Branch> findAllByStatus(boolean status);

    @Query("SELECT b FROM Branch b join fetch b.address a WHERE a.street LIKE :street AND a.number LIKE :number AND a.city.cityId = :cityId")
    Branch findByAddress(@Param("street") String street, @Param("number") String number, @Param("cityId") Long cityId);
    List<Branch> findAllBranchByStatusTrue();

    List<Branch> findBranchByBranchName(String branchName);

}
