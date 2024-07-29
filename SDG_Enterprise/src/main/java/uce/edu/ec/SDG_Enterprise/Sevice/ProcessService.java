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

    public Process saveProcess(Process process) {
        return processRepository.save(process);
    }

    public Process createProcess(Process process) {
        return processRepository.save(process);
    }

    public void deleteProcess(Long id) {
        processRepository.deleteById(id);
    }

    public void updateProcessTime(String name, Double newTime) {
        List<Process> processes = processRepository.findByNameProcess(name);

        if (!processes.isEmpty()) {
            Process process = processes.get(0);
            process.setTime(newTime);
            processRepository.save(process);
            System.out.println("Time updated successfully.");
        } else {
            System.out.println("No process found with the specified name and time.");
        }
    }
}
