package uce.edu.ec.SDG_Enterprise.Sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.SDG_Enterprise.Model.Product;
import uce.edu.ec.SDG_Enterprise.Model.Requested;
import uce.edu.ec.SDG_Enterprise.Model.User;
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

    public List<Requested> findByUserId(Long idUser) {
        return requestedRespository.findByUserId(idUser); // Asegúrate de que coincida con el método del repositorio
    }

    public Requested save(Requested requested) {
        return requestedRespository.save(requested);
    }

    public Requested create(User user, Product product, String estado) {
        Requested requested = new Requested();
        requested.setUser(user);
        requested.setProduct(product);
        requested.setEstado(estado);
        return requestedRespository.save(requested);
    }

    public List<Requested> findByEstado(String estado) {
        return requestedRespository.findByEstado(estado);
    }

    public void deleteById(Long id) {
        requestedRespository.deleteById(id);
    }

}
