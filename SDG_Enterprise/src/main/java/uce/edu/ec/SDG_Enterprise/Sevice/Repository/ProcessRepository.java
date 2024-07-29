package uce.edu.ec.SDG_Enterprise.Sevice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uce.edu.ec.SDG_Enterprise.Model.Process;

import java.util.List;

public interface ProcessRepository extends JpaRepository<Process, Long> {
    List<Process> findByNameProcess(String nameProcess);
    List<Process> findByNameMaterial(String idUser);
}
