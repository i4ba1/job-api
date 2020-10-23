package id.mni.job.repository;

import id.mni.job.models.JobDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface InterfaceJobDetailRepository extends JpaRepository<JobDetail, BigInteger> {
}
