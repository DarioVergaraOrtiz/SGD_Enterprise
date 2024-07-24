package uce.edu.ec.SDG_Enterprise.Container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import uce.edu.ec.SDG_Enterprise.Model.Product;
import uce.edu.ec.SDG_Enterprise.Model.Requested;
import uce.edu.ec.SDG_Enterprise.Model.User;
import uce.edu.ec.SDG_Enterprise.Sevice.ProductService;
import uce.edu.ec.SDG_Enterprise.Sevice.RequestedService;
import uce.edu.ec.SDG_Enterprise.Sevice.UserService;
import uce.edu.ec.SDG_Enterprise.View.UIRegister;
import uce.edu.ec.SDG_Enterprise.View.ViewAdmin;
import uce.edu.ec.SDG_Enterprise.View.ViewClient;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        Optional<User> optionalUsuario = usuarioService.findByUsernameAndPassword(username, password);

        if (optionalUsuario.isPresent()) {
            this.user = optionalUsuario.get();
            String rol = user.getRol();

            if ("Administrador".equalsIgnoreCase(rol)) {
                startViewAdministrator();
            } else if ("Cliente".equalsIgnoreCase(rol)) {
                startClientView();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String userName() {
        return user.getUsername();
    }

    public void startViewAdministrator() {
        ViewAdmin viewAdmin = new ViewAdmin(this);
        viewAdmin.setVisible(true);
    }

    public void startViewRegister() {
        UIRegister viewRegister = new UIRegister(this);
        viewRegister.setVisible(true);
    }

    public void startClientView() {
        ViewClient viewClient = new ViewClient(this);
        viewClient.setVisible(true);
    }

    public void registerClient(String rucCedula, String name, String username, String password, String rol, String email, String phone) {
        usuarioService.saveUser(rucCedula, name, username, password, rol, email, phone);
        JOptionPane.showMessageDialog(null, "Registro exitoso", "Registro", JOptionPane.INFORMATION_MESSAGE);
        //startClientView();
    }

    public void addProduct() {
        productService.saveProduct("Armario", "Madera", 150.0);
        productService.saveProduct("Mesa", "Metal", 85.5);
    }

    public List<Product> getProduct() {
        return productService.findAll();
    }

    public void realizarCompra(Set<Product> productosSeleccionados) {
        Long userId = user.getId(); // Suponiendo que user tiene un getId() para obtener el ID del usuario actual
        if (userId == null) {
            JOptionPane.showMessageDialog(null, "No se pudo encontrar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String estado = "Pendiente"; // Estado por defecto al realizar una compra

        try {
            for (Product producto : productosSeleccionados) {
                requestedService.create(user, producto, estado);
            }

            JOptionPane.showMessageDialog(null, "Compra realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la compra.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Requested> getestado(String estado) {
        return requestedService.findByEstado(estado);
    }

    public String getNameClient(long id) {
        return usuarioService.findById(id).get().getName();
    }

    public Optional<Product> getProductById(long id) {
        return productService.findById(id);
    }

    public List<String> getPendingRequestsDetails() {
        List<String> pendingRequestsDetails = new ArrayList<>();

        List<Requested> pendingRequests = requestedService.findByEstado("pendiente");
        for (Requested requested : pendingRequests) {
            String clientName = usuarioService.findById(requested.getUser().getId()).get().getName();
            String productName = productService.findById(requested.getProduct().getId()).get().getName();
            Long requestId = requested.getId();

            String details = "Solicitud: " + requestId + " - Cliente: " + clientName + " - Producto: " + productName;
            pendingRequestsDetails.add(details);
        }

        return pendingRequestsDetails;
    }

    public List<String> getMakingRequestsDetails() {
        List<String> pendingRequestsDetails = new ArrayList<>();

        List<Requested> pendingRequests = requestedService.findByEstado("Fabricando...");
        for (Requested requested : pendingRequests) {
            String clientName = usuarioService.findById(requested.getUser().getId()).get().getName();
            String productName = productService.findById(requested.getProduct().getId()).get().getName();
            Long requestId = requested.getId();

            String details = "Solicitud: " + requestId + " - Cliente: " + clientName + " - Producto: " + productName;
            pendingRequestsDetails.add(details);
        }

        return pendingRequestsDetails;
    }

    public void fabricarProducto(String selectedProductName) {
        // Obtener los detalles de todas las solicitudes pendientes
        List<String> pendingRequestsDetails = getPendingRequestsDetails();

        // Buscar la solicitud pendiente del producto seleccionado
        boolean found = false;
        for (String details : pendingRequestsDetails) {
            if (details.contains(selectedProductName)) {
                // Extraer el ID de la solicitud para actualizar su estado
                String[] parts = details.split(" - ");
                String idPart = parts[0]; // Esto asume el formato "Solicitud: ID"

                // Extraer el ID de la solicitud
                String[] idParts = idPart.split(": ");
                if (idParts.length != 2) {
                    System.err.println("Formato incorrecto para idPart: " + idPart);
                    continue;
                }
                String requestIdStr = idParts[1].trim();

                // Obtener el ID de la solicitud como Long
                Long requestedId;
                try {
                    requestedId = Long.parseLong(requestIdStr);
                } catch (NumberFormatException e) {
                    System.err.println("No se pudo convertir '" + requestIdStr + "' a Long.");
                    continue; // O manejar el error de alguna manera adecuada
                }

                // Obtener la solicitud por su ID
                Requested requestToFabricate = requestedService.findById(requestedId).orElse(null);
                if (requestToFabricate != null) {
                    requestToFabricate.setEstado("Fabricando...");
                    requestedService.save(requestToFabricate);

                    JOptionPane.showMessageDialog(null, "El producto seleccionado ha sido marcado como 'Fabricando...'.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "No se encontraron solicitudes pendientes para el producto seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarSolicitudPorId(String selectedProductName) {
        // Obtener los detalles de todas las solicitudes pendientes
        List<String> pendingRequestsDetails = getPendingRequestsDetails();

        // Buscar la solicitud pendiente del producto seleccionado
        boolean found = false;
        for (String details : pendingRequestsDetails) {
            if (details.contains(selectedProductName)) {
                // Extraer el ID de la solicitud para eliminarla
                String[] parts = details.split(" - ");
                String idPart = parts[0]; // Esto asume el formato "Solicitud: ID"

                // Extraer el ID de la solicitud
                String[] idParts = idPart.split(": ");
                if (idParts.length != 2) {
                    System.err.println("Formato incorrecto para idPart: " + idPart);
                    continue;
                }
                String requestIdStr = idParts[1].trim();

                // Obtener el ID de la solicitud como Long
                Long requestedId;
                try {
                    requestedId = Long.parseLong(requestIdStr);
                } catch (NumberFormatException e) {
                    System.err.println("No se pudo convertir '" + requestIdStr + "' a Long.");
                    continue; // O manejar el error de alguna manera adecuada
                }

                // Eliminar la solicitud por su ID
                requestedService.deleteById(requestedId);
                JOptionPane.showMessageDialog(null, "La solicitud con ID " + requestedId + " ha sido eliminada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "No se encontraron solicitudes pendientes para el producto seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String concatProcessToProduct(long id_1, long id_2){
        productService.addProcessToProduct(id_1,id_2);
        return "Se intento";
    }


}
