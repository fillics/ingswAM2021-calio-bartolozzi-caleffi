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
    private String path1,path2,path3,path4,path5,path6,path7,path8,path9,path10,path11,path12;
    private int width;
    private int height;

    private final GUI gui;
    private int id;

    /**
     * Method used to set the panel background.
     * @param g is a Graphics object
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getDimension().width, gui.getDimension().height, null);

    }

    public ShowDevGridPanel(GUI gui) {
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/game.png");
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

        try{
            path1 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(8).getPath();
        }catch (NullPointerException e){
            path1 = "/images/back/DevCardBackY3.png";
        }
        c.gridx = 0;
        c.gridy = 0;
        setLabel(devCard1, c, panel, width, height, path1);

        try{
            path2 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(9).getPath();
        }catch (NullPointerException e){
            path2 = "/images/back/DevCardBackG3.png";
        }
        c.gridx = 1;
        c.gridy = 0;
        setLabel(devCard2, c, panel, width, height, path2);

        try{
            path3 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(10).getPath();
        }catch (NullPointerException e){
            path3 = "/images/back/DevCardBackP3.png";
        }
        c.gridx = 2;
        c.gridy = 0;
        setLabel(devCard3, c, panel, width, height, path3);

        try{
            path4 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(11).getPath();
        }catch (NullPointerException e){
            path4 = "/images/back/DevCardBackB3.png";
        }
        c.gridx = 3;
        c.gridy = 0;
        setLabel(devCard4, c, panel, width, height, path4);

        try{
            path5 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(4).getPath();
        }catch (NullPointerException e){
            path5 = "/images/back/DevCardBackY2.png";
        }
        c.gridx = 0;
        c.gridy = 1;
        setLabel(devCard5, c, panel, width, height, path5);



        try{
            path6 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(5).getPath();
        }catch (NullPointerException e){
            path6 = "/images/back/DevCardBackG2.png";
        }
        c.gridx = 1;
        c.gridy = 1;
        setLabel(devCard6, c, panel, width, height, path6);


        try{
            path7 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(6).getPath();
        }catch (NullPointerException e){
            path7 = "/images/back/DevCardBackP2.png";
        }
        c.gridx = 2;
        c.gridy = 1;
        setLabel(devCard7, c, panel, width, height, path7);

        try{
            path8 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(7).getPath();
        }catch(NullPointerException e){
            path8 = "/images/back/DevCardBackB2.png";
        }
        c.gridx = 3;
        c.gridy = 1;
        setLabel(devCard8, c, panel, width, height, path8);


        try{
            path9 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(0).getPath();
        }catch (NullPointerException e){
            path9 = "/images/back/DevCardBackY1.png";
        }
        c.gridx = 0;
        c.gridy = 2;
        setLabel(devCard9, c, panel, width, height, path9);


        try{
            path10 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(1).getPath();
        }catch(NullPointerException e){
            path10 = "/images/back/DevCardBackG1.png";
        }
        c.gridx = 1;
        c.gridy = 2;
        setLabel(devCard10, c, panel, width, height, path10);


        try{
            path11 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(2).getPath();
        }catch (NullPointerException e){
            path11 = "/images/back/DevCardBackP1.png";
        }
        c.gridx = 2;
        c.gridy = 2;
        setLabel(devCard11, c, panel, width, height, path11);

        try{
            path12 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(3).getPath();
        }catch (NullPointerException e){
            path12 = "/images/back/DevCardBackB1.png";
        }
        c.gridx = 3;
        c.gridy = 2;
        setLabel(devCard12, c, panel, width, height, path12);

    }

    public void setLabel(JLabel card, GridBagConstraints c, JPanel panel, int width, int height, String path){
        try {
            card.setIcon(new ImageIcon(new ImageIcon((Objects.requireNonNull(GUI.class.getResourceAsStream(path))).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException ignored) { }
        panel.add(card,c);
        card.setOpaque(false);
        card.setBackground(new Color(0,0,0,0));
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

    /**
     * Method that, based on the button clicked, perform a determined action.
     * @param e is a ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            gui.switchPanels(new BoardPanel(gui));
            gui.createMessageFromServer("Back to your board");

        }

    }



}
