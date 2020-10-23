package id.mni.job.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "tbl_job_detail")
public class JobDetail {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    private BigInteger id;

    @Column(name = "job_description", columnDefinition = "text", nullable = false)
    private String jobDescription;

    @Column(name = "job_responsibility", columnDefinition = "text", nullable = false)
    private String jobResponsibility;

    @Column(name = "job_requirement", columnDefinition = "text", nullable = false)
    private String jobRequirement;

    @Column(name="created_at", columnDefinition = "timestamp", nullable = false)
    private Timestamp createdAt;

    @Column(name="updated_at", columnDefinition = "timestamp", nullable = false)
    private Timestamp updatedAt;

    /*@OneToOne
    @MapsId
    @JoinColumn(name = "job_id")
    private Job job;*/
}
