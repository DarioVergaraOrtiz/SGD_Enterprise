package uce.edu.ec.SDG_Enterprise.Sevice;

import uce.edu.ec.SDG_Enterprise.Container.Controler;

import javax.swing.*;
import java.util.List;

public class ActualizacionPendientesWorker extends SwingWorker<Void, String> {
    private DefaultListModel<String> modelo;

    public ActualizacionPendientesWorker(DefaultListModel<String> modelo) {
        this.modelo = modelo;
    }

    @Override
    protected Void doInBackground() throws Exception {
        // Perform background task and publish updates
        publish("Task started...");
        // Simulate some work with a sleep
        Thread.sleep(1000);
        publish("Task in progress...");
        Thread.sleep(1000);
        publish("Task completed.");
        return null;
    }

    @Override
    protected void process(List<String> chunks) {
        modelo.clear();
        for (String message : chunks) {
            modelo.addElement(message);
        }
    }
}