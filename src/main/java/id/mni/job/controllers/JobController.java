package id.mni.job.controllers;

import id.mni.job.models.Job;
import id.mni.job.models.dto.JobDto;
import id.mni.job.services.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("/api/job")
public class JobController {

    private final JobService jobService;
    private static final Logger log = LoggerFactory.getLogger(JobController.class);

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @GetMapping("/getJobs")
    public ResponseEntity<List<JobDto>> getJobs(){
        List<JobDto> jobList = jobService.getListOfJob();

        int rowAffected = 0;
        for (JobDto dto:jobList) {
            DateTimeFormatter formatterA = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy");
            ZonedDateTime zdt = ZonedDateTime.parse(dto.getCreated_at(), formatterA);
            log.info(zdt.toString());

            Job newJob = new Job();
            newJob.setId(UUID.fromString(dto.getId()));
            newJob.setType(dto.getType());
            newJob.setUrl(dto.getUrl());
            newJob.setCreated_at(zdt);
            newJob.setCompany(dto.getCompany());
            newJob.setCompany_url(dto.getCompany_url());
            newJob.setLocation(dto.getLocation());
            newJob.setTitle(dto.getTitle());
            newJob.setDescription(dto.getDescription());
            newJob.setHow_to_apply(dto.getHow_to_apply());
            newJob.setCompany_logo(dto.getCompany_logo());

            if (jobService.isJobExist(UUID.fromString(dto.getId())) <= 0) {
                if (jobService.createNewJob(newJob) != null) {
                    rowAffected++;
                }
            }else{
                return new ResponseEntity<>(jobList, HttpStatus.CONFLICT);
            }
        }

        if (rowAffected <= 0) {
            return new ResponseEntity<>(jobList, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(jobList, HttpStatus.OK);
    }
}
