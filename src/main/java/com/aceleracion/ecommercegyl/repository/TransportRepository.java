package com.aceleracion.ecommercegyl.repository;
import com.aceleracion.ecommercegyl.model.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransportRepository extends JpaRepository <Transport, Long> {


    Transport findTransportByTransportId(Long id);
    Transport findTransportByDate(Date date);

    List<Transport> findTransportByTargetBranchBranchId(Long branchId);

    List<Transport> findTransportByTargetBranchBranchIdAndDateBetween(Long branch, Date startDate, Date endDate);

    List<Transport> findTransportByOriginBranchBranchId(Long branchId);

    List<Transport> findTransportByOriginBranchBranchIdAndDateBetween(Long branch, Date startDate, Date endDate);

    List<Transport> findByDateBetween(Date objStartDate, Date objEndDate);
}
