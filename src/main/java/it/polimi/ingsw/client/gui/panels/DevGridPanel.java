package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.gui.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class DevGridPanel extends JPanel implements ActionListener {
    private Image background;
    private GridBagConstraints c;
    private JButton devCard1, devCard2, devCard3, devCard4, devCard5, devCard6, devCard7, devCard8, devCard9, devCard10, devCard11, devCard12;
    private JButton back;

    ClientModelView clientModelView;

    GUI gui;
    int id;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getDimension().width, gui.getDimension().height, null);

    }

    public DevGridPanel(GUI gui) {

        InputStream is = getClass().getResourceAsStream("/images/background/game.png");
        try {
            background = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.gui = gui;
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        devCard1 = new JButton();
        devCard2 = new JButton();
        devCard3 = new JButton();
        devCard4 = new JButton();
        devCard5 = new JButton();
        devCard6 = new JButton();
        devCard7 = new JButton();
        devCard8 = new JButton();
        devCard9 = new JButton();
        devCard10 = new JButton();
        devCard11 = new JButton();
        devCard12 = new JButton();
        back = new JButton("BACK");
        try {
            setButtons(panel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.add(panel);
        this.setOpaque(false);

    }

    public void setButtons(JPanel panel) throws IOException {
        clientModelView = gui.getClient().getClientModelView();
        devCard1.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(8).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard1.addActionListener(this);
        c.gridx = 0;
        c.gridy = 0;
        panel.add(devCard1,c);
        devCard2.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(9).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard2.addActionListener(this);
        c.gridx = 1;
        c.gridy = 0;
        panel.add(devCard2,c);
        devCard3.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(10).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard3.addActionListener(this);
        c.gridx = 2;
        c.gridy = 0;
        panel.add(devCard3,c);
        devCard4.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(11).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard4.addActionListener(this);
        c.gridx = 3;
        c.gridy = 0;
        panel.add(devCard4,c);
        devCard5.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(4).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard5.addActionListener(this);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(devCard5,c);
        devCard6.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(5).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard6.addActionListener(this);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(devCard6,c);
        devCard7.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(6).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard7.addActionListener(this);
        c.gridx = 2;
        c.gridy = 1;
        panel.add(devCard7,c);
        devCard8.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(7).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard8.addActionListener(this);
        c.gridx = 3;
        c.gridy = 1;
        panel.add(devCard8,c);
        devCard9.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(0).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard9.addActionListener(this);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(devCard9,c);
        devCard10.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(1).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard10.addActionListener(this);
        c.gridx = 1;
        c.gridy = 2;
        panel.add(devCard10,c);
        devCard11.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(2).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard11.addActionListener(this);
        c.gridx = 2;
        c.gridy = 2;
        panel.add(devCard11,c);
        devCard12.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(3).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        devCard12.addActionListener(this);
        c.gridx = 3;
        c.gridy = 2;
        panel.add(devCard12,c);
        c.insets = new Insets(50,0,0,0);
        back.setPreferredSize(new Dimension(100, 50));
        back.addActionListener(this);
        c.gridx = 1;
        c.gridy = 3;
        panel.add(back, c);

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
        if(e.getSource() == back){
            BoardPanel boardPanel = new BoardPanel(gui);
            gui.switchPanels(boardPanel);
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


}
