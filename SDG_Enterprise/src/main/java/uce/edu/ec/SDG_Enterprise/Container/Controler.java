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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
public class Controler {

    private UserService usuarioService;
    private ProductService productService;
    private RequestedService requestedService;
    private ScheduledExecutorService executorService;
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
    }

    public void addProduct() {
        productService.saveProduct("Armario", "Madera", 150.0);
        productService.saveProduct("Mesa", "Metal", 85.5);
        productService.saveProduct("Cocina", "Carton", 90);
    }

    public List<Product> getProduct() {
        return productService.findAll();
    }

    public void realizarCompra(Set<Product> productosSeleccionados) {
        Long userId = user.getId();
        if (userId == null) {
            JOptionPane.showMessageDialog(null, "No se pudo encontrar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String estado = "Pendiente";

        productosSeleccionados.stream().forEach(producto -> {
            try {
                requestedService.create(user, producto, estado);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al realizar la compra.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JOptionPane.showMessageDialog(null, "Compra realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
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
        return requestedService.findByEstado("pendiente").stream()
                .map(requested -> {
                    String clientName = usuarioService.findById(requested.getUser().getId()).map(User::getName).orElse("Desconocido");
                    String productName = productService.findById(requested.getProduct().getId()).map(Product::getName).orElse("Desconocido");
                    Long requestId = requested.getId();
                    return "Solicitud: " + requestId + " - Cliente: " + clientName + " - Producto: " + productName;
                })
                .collect(Collectors.toList());
    }


    public List<String> getMakingRequestsDetails() {
        return requestedService.findByEstado("Fabricando...").stream()
                .map(requested -> {
                    String clientName = usuarioService.findById(requested.getUser().getId()).orElseThrow().getName();
                    String productName = productService.findById(requested.getProduct().getId()).orElseThrow().getName();
                    Long requestId = requested.getId();
                    return "Solicitud: " + requestId + " - Cliente: " + clientName + " - Producto: " + productName;
                })
                .collect(Collectors.toList());
    }

    public void fabricarProducto(String selectedProductName) {
        getPendingRequestsDetails().stream()
                .filter(details -> details.contains(selectedProductName))
                .findFirst()
                .ifPresentOrElse(details -> {
                    String[] parts = details.split(" - ");
                    String idPart = parts[0];
                    String[] idParts = idPart.split(": ");
                    if (idParts.length == 2) {
                        try {
                            Long requestId = Long.parseLong(idParts[1].trim());
                            Requested requestToFabricate = requestedService.findById(requestId).orElse(null);
                            if (requestToFabricate != null) {
                                requestToFabricate.setEstado("Fabricando...");
                                requestedService.save(requestToFabricate);
                                JOptionPane.showMessageDialog(null, "El producto seleccionado ha sido marcado como 'Fabricando...'.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("No se pudo convertir '" + idParts[1].trim() + "' a Long.");
                        }
                    } else {
                        System.err.println("Formato incorrecto para idPart: " + idPart);
                    }
                }, () -> JOptionPane.showMessageDialog(null, "No se encontraron solicitudes pendientes para el producto seleccionado.", "Error", JOptionPane.ERROR_MESSAGE));
    }

    public void eliminarSolicitudPorId(String selectedProductName) {
        getPendingRequestsDetails().stream()
                .filter(details -> details.contains(selectedProductName))
                .findFirst()
                .ifPresentOrElse(details -> {
                    String[] parts = details.split(" - ");
                    String idPart = parts[0];
                    String[] idParts = idPart.split(": ");
                    if (idParts.length == 2) {
                        try {
                            Long requestId = Long.parseLong(idParts[1].trim());
                            requestedService.deleteById(requestId);
                            JOptionPane.showMessageDialog(null, "La solicitud con ID " + requestId + " ha sido eliminada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } catch (NumberFormatException e) {
                            System.err.println("No se pudo convertir '" + idParts[1].trim() + "' a Long.");
                        }
                    } else {
                        System.err.println("Formato incorrecto para idPart: " + idPart);
                    }
                }, () -> JOptionPane.showMessageDialog(null, "No se encontraron solicitudes pendientes para el producto seleccionado.", "Error", JOptionPane.ERROR_MESSAGE));
    }
    public void startBackgroundUpdate(Runnable updateTask) {
        if (executorService == null) {
            executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleAtFixedRate(updateTask, 0, 5, TimeUnit.SECONDS);
        }
    }


    public String concatProcessToProduct(long id_1, long id_2){
        productService.addProcessToProduct(id_1,id_2);
        return "Se intento";
    }
}
