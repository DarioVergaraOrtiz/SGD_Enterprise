package uce.edu.ec.SDG_Enterprise.Sevice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uce.edu.ec.SDG_Enterprise.Model.Requested;

import java.util.List;

public interface IRequestedRespository extends JpaRepository<Requested, Long> {

    List<Requested> findByEstado(String estado);

    List<Requested> findByUserId(Long idUser);

}
