package uce.edu.ec.SDG_Enterprise.Sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.SDG_Enterprise.Model.Requested;
import uce.edu.ec.SDG_Enterprise.Sevice.Repository.IRequestedRespository;

import java.util.List;
import java.util.Optional;

@Service
public class RequestedService {
    @Autowired
    IRequestedRespository requestedRespository;

    public List<Requested> findAll() {
        return requestedRespository.findAll();
    }
    public Optional<Requested> findById(Long id) {
        return requestedRespository.findById(id);
    }
}
