package id.mni.job.services.interfaces;

import id.mni.job.models.Job;
import id.mni.job.models.dto.JobDto;

import java.util.List;
import java.util.UUID;

public interface InterfaceJob {
    List<JobDto> getListOfJob();
    Job createNewJob(Job job);
    int isJobExist(UUID id);
    Job getJobById(UUID id);
}
