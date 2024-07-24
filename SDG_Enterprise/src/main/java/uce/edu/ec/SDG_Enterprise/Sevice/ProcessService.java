package uce.edu.ec.SDG_Enterprise.Sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uce.edu.ec.SDG_Enterprise.Sevice.Repository.ProcessRepository;
import uce.edu.ec.SDG_Enterprise.Model.Process;
import java.util.List;



@Service
public class ProcessService {

    @Autowired
    private final ProcessRepository processRepository;

    public ProcessService(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    public List<Process> getAllProcesses() {
        return processRepository.findAll();
    }

    public Process createProcess(Process process) {
        return processRepository.save(process);
    }

    public void deleteProcess(Long id) {
        processRepository.deleteById(id);
    }
}
