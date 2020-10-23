package id.mni.job.services;

import id.mni.job.models.Job;
import id.mni.job.models.dto.JobDto;
import id.mni.job.repository.InterfaceJobRepository;
import id.mni.job.services.interfaces.InterfaceJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class JobService implements InterfaceJob {
    private static final Logger log = LoggerFactory.getLogger(JobService.class);

    private final InterfaceJobRepository jobRepository;

    public JobService(InterfaceJobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobDto> getListOfJob() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JobDto[]> forObject = restTemplate.getForEntity("https://jobs.github.com/positions.json", JobDto[].class);

        JobDto[] jobs = forObject.getBody();
        for (JobDto dto:jobs) {
            log.info(dto.getId());
        }

        return Arrays.asList(jobs);
    }

    @Override
    public Job createNewJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public int isJobExist(UUID id) {
        return jobRepository.findById(id).isPresent()?1:0;
    }
}
