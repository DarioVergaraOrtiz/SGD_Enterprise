package uce.edu.ec.SDG_Enterprise.Container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import uce.edu.ec.SDG_Enterprise.Model.Requested;
import uce.edu.ec.SDG_Enterprise.Model.User;
import uce.edu.ec.SDG_Enterprise.Sevice.ProductService;
import uce.edu.ec.SDG_Enterprise.Sevice.RequestedService;
import uce.edu.ec.SDG_Enterprise.Sevice.UserService;

import java.util.Set;

@Controller
public class Controler {

    private UserService usuarioService;
    private ProductService productService;
    private RequestedService requestedService;
    User user;
    @Autowired
    public Controler(ProductService productService, RequestedService requestedService, UserService usuarioService) {
        this.productService = productService;
        this.requestedService = requestedService;
        this.usuarioService = usuarioService;
        user = new User();
    }
    public void iniciarSegundaVista(String username, String password) {
        String optionalUsuario = usuarioService.getRolForLogin(username, password);
            String rol =  optionalUsuario;
            if ("Administrador".equalsIgnoreCase(rol)) {

            } else if ("Cliente".equalsIgnoreCase(rol)) {

            }
    }


    public void registro(String email, String name, String password, String phone, String rol, String rucCedula, String username){
        User user = new User(email, name, password, phone, rol, rucCedula, username);
           user.setName(name);
           user.setRucCedula(rucCedula);
           user.setUsername(username);
           user.setPassword(password);
           user.setPhone(phone);
           user.setRol(rol);
           usuarioService.save(user);
    }
}
