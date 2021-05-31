package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DevGridPanel extends JPanel implements ActionListener {
    GridBagConstraints c;
    JButton devCard1;
    JButton devCard2;
    JButton devCard3;
    JButton devCard4;
    JButton devCard5;
    JButton devCard6;
    JButton devCard7;
    JButton devCard8;
    JButton devCard9;
    JButton devCard10;
    JButton devCard11;
    JButton devCard12;
    ClientModelView clientModelView;
    int id;

    public DevGridPanel(ClientModelView clientModelView) {
        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        devCard1 = new JButton("1");
        devCard2 = new JButton("2");
        devCard3 = new JButton("3");
        devCard4 = new JButton("4");
        devCard5 = new JButton("5");
        devCard6 = new JButton("6");
        devCard7 = new JButton("7");
        devCard8 = new JButton("8");
        devCard9 = new JButton("9");
        devCard10 = new JButton("10");
        devCard11 = new JButton("11");
        devCard12 = new JButton("12");
        try {
            setButtons();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.clientModelView = clientModelView;
    }

    public void setButtons() throws IOException {
        devCard1.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(0).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard1.addActionListener(this);
        c.gridx = 0;
        c.gridy = 0;
        this.add(devCard1,c);
        devCard2.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(1).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard2.addActionListener(this);
        c.gridx = 1;
        c.gridy = 0;
        this.add(devCard2,c);
        devCard3.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(2).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard3.addActionListener(this);
        c.gridx = 2;
        c.gridy = 0;
        this.add(devCard3,c);
        devCard4.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(3).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard4.addActionListener(this);
        c.gridx = 3;
        c.gridy = 0;
        this.add(devCard4,c);
        devCard5.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(4).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard5.addActionListener(this);
        c.gridx = 0;
        c.gridy = 1;
        this.add(devCard5,c);
        devCard6.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(5).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard6.addActionListener(this);
        c.gridx = 1;
        c.gridy = 1;
        this.add(devCard6,c);
        devCard7.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(6).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard7.addActionListener(this);
        c.gridx = 2;
        c.gridy = 1;
        this.add(devCard7,c);
        devCard8.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(7).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard8.addActionListener(this);
        c.gridx = 3;
        c.gridy = 1;
        this.add(devCard8,c);
        devCard9.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(8).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard9.addActionListener(this);
        c.gridx = 0;
        c.gridy = 2;
        this.add(devCard9,c);
        devCard10.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(9).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard10.addActionListener(this);
        c.gridx = 1;
        c.gridy = 2;
        this.add(devCard10,c);
        devCard11.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(10).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard11.addActionListener(this);
        c.gridx = 2;
        c.gridy = 2;
        this.add(devCard11,c);
        devCard12.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(11).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard12.addActionListener(this);
        c.gridx = 3;
        c.gridy = 2;
        this.add(devCard12,c);
    }

    public int getId() {
        return id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //ognuno di questi metodi verrà implementato con la selezione della carta prescelta e l'id di questa verrà inserito
        // dentro al pacchetto da inviare al server, dopodichè sarà impossibile cliccare tutti e 12 i pulsanti visto che la carta
        // da comprare è soltanto una
        if (e.getSource() == devCard1) {
            id = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(0).getId();
            disableAllButtons();
        }
        if (e.getSource() == devCard2) {
            id = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(1).getId();
            disableAllButtons();

        }
        if (e.getSource() == devCard3) {
            id = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(2).getId();
            disableAllButtons();
        }
        if (e.getSource() == devCard4) {
            id = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(3).getId();
            disableAllButtons();

        }
        if (e.getSource() == devCard5) {
            id = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(4).getId();
            disableAllButtons();

        }
        if (e.getSource() == devCard6) {
            id = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(5).getId();
            disableAllButtons();

        }
        if (e.getSource() == devCard7) {
            id = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(6).getId();
            disableAllButtons();

        }
        if (e.getSource() == devCard8) {
            id = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(7).getId();
            disableAllButtons();

        }
        if (e.getSource() == devCard9) {
            id = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(8).getId();
            disableAllButtons();

        }
        if (e.getSource() == devCard10) {
            id = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(9).getId();
            disableAllButtons();

        }
        if (e.getSource() == devCard11) {
            id = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(10).getId();
            disableAllButtons();

        }
        if (e.getSource() == devCard12) {
            id = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(11).getId();
            disableAllButtons();

        }

    }

    public void disableAllButtons(){
        devCard1.setEnabled(false);
        devCard2.setEnabled(false);
        devCard3.setEnabled(false);
        devCard4.setEnabled(false);
        devCard5.setEnabled(false);
        devCard6.setEnabled(false);
        devCard7.setEnabled(false);
        devCard8.setEnabled(false);
        devCard9.setEnabled(false);
        devCard10.setEnabled(false);
        devCard11.setEnabled(false);
        devCard12.setEnabled(false);
    }

    public static void main(String[] args) {
     //   DevGridPanel devGridPanel = new DevGridPanel(new ClientModelView());
        DevSpacesPanel devSpacesPanel = new DevSpacesPanel(new ClientModelView());
        JFrame frame = new JFrame();

        frame.getContentPane().setBackground(new Color(233, 226, 193)); //change color of background - si può anche mettere il colore in esadecimale

        frame.setTitle("Master of Renaissance");
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
        frame.setSize(800, 800);
        frame.add(devSpacesPanel);

    }


}
