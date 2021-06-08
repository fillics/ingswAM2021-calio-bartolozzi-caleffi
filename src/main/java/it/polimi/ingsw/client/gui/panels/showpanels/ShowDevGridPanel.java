package it.polimi.ingsw.client.gui.panels.showpanels;

import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.client.gui.panels.BoardPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ShowDevGridPanel extends JPanel implements ActionListener {
    private Image background;
    private GridBagConstraints c;
    private JLabel devCard1, devCard2, devCard3, devCard4, devCard5, devCard6, devCard7, devCard8, devCard9, devCard10, devCard11, devCard12;
    private JButton backButton;
    private JPanel gridPanel;

    private final GUI gui;
    private int id;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getDimension().width, gui.getDimension().height, null);

    }

    public ShowDevGridPanel(GUI gui) {
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/backgroundGame2.png");
        try {
            assert is != null;
            background = ImageIO.read(is);
        } catch (IOException ignored) {}

        JPanel mainPanel = new JPanel();
        c = new GridBagConstraints();

        mainPanel.setLayout(new GridBagLayout());

        createDevelopmentGrid();
        c.gridx=0;
        c.gridy=0;
        c.insets = new Insets(50,0,0,0);
        mainPanel.add(gridPanel, c);

        createBackButtons();
        c.gridx=0;
        c.gridy=1;
        mainPanel.add(backButton, c);

        mainPanel.setOpaque(false);
        this.add(mainPanel);

    }

    public void createDevelopmentGrid(){
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        devCard1 = new JLabel();
        devCard2 = new JLabel();
        devCard3 = new JLabel();
        devCard4 = new JLabel();
        devCard5 = new JLabel();
        devCard6 = new JLabel();
        devCard7 = new JLabel();
        devCard8 = new JLabel();
        devCard9 = new JLabel();
        devCard10 = new JLabel();
        devCard11 = new JLabel();
        devCard12 = new JLabel();
        try {
            setButtons(gridPanel, 150, 226, 5,5,5,5);
        } catch (IOException ignored) {}

        gridPanel.setOpaque(false);
        gridPanel.setBackground(new Color(0,0,0,0));
    }

    public void setButtons(JPanel panel, int width, int height, int top, int left, int bottom, int right) throws IOException {
        ClientModelView clientModelView = gui.getClient().getClientModelView();
        c.insets = new Insets(top,left,bottom,right);
        devCard1.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(8).getPath())).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 0;
        c.gridy = 0;
        panel.add(devCard1,c);
        devCard2.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(9).getPath())).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 1;
        c.gridy = 0;
        panel.add(devCard2,c);
        devCard3.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(10).getPath())).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 2;
        c.gridy = 0;
        panel.add(devCard3,c);
        devCard4.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(11).getPath())).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 3;
        c.gridy = 0;
        panel.add(devCard4,c);
        devCard5.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(4).getPath())).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 0;
        c.gridy = 1;
        panel.add(devCard5,c);
        devCard6.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(5).getPath())).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));c.gridx = 1;
        c.gridy = 1;
        panel.add(devCard6,c);
        devCard7.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(6).getPath())).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 2;
        c.gridy = 1;
        panel.add(devCard7,c);
        devCard8.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(7).getPath())).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 3;
        c.gridy = 1;
        panel.add(devCard8,c);
        devCard9.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(0).getPath())).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 0;
        c.gridy = 2;
        panel.add(devCard9,c);
        devCard10.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(1).getPath())).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 1;
        c.gridy = 2;
        panel.add(devCard10,c);
        devCard11.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(2).getPath())).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 2;
        c.gridy = 2;
        panel.add(devCard11,c);
        devCard12.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(3).getPath())).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 3;
        c.gridy = 2;
        panel.add(devCard12,c);

    }

    public void createBackButtons(){
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new GridBagLayout());

        c.gridx=0;
        c.gridy=0;
        backButton = new JButton("BACK");
        backButton.addActionListener(this);
        backButton.setPreferredSize(new Dimension(250, 50));

        backButtonPanel.add(backButton, c);

        backButtonPanel.setOpaque(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            gui.switchPanels(new BoardPanel(gui));
        }

    }



}
