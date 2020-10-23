package id.mni.job.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_job")
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(columnDefinition = "varchar(100)", name = "type")
    private String type;

    @Column(columnDefinition = "varchar(500)", name = "url")
    private String url;

    @Column(name="created_at",  nullable = false)
    private ZonedDateTime created_at;

    @Column(columnDefinition = "varchar(100)", name = "company")
    private String company;

    @Column(columnDefinition = "varchar(500)", name = "company_url")
    private String company_url;

    @Column(columnDefinition = "varchar(250)", name = "location")
    private String location;

    @Column(columnDefinition = "varchar(250)", name = "title")
    private String title;

    @Column(columnDefinition = "text", name = "description")
    private String description;

    @Column(columnDefinition = "text", name = "how_to_apply")
    private String how_to_apply;

    @Column(columnDefinition = "text", name = "company_logo")
    private String company_logo;
}
