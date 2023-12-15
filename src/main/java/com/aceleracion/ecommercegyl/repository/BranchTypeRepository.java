package com.aceleracion.ecommercegyl.repository;

import com.aceleracion.ecommercegyl.model.BranchType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchTypeRepository extends JpaRepository<BranchType, Long> {

    BranchType findByBranchType(String branchType);
    BranchType findByBranchTypeAndStatusTrue(String branchType);
    List<BranchType> findAllByStatusTrue();

}
