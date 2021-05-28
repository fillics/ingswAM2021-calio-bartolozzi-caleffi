package it.polimi.ingsw.client.ClientGUI;
import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.ViewInterface;
import it.polimi.ingsw.model.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUI implements ViewInterface {

   private final ClientModelView clientModelView;

    public GUI(ClientModelView clientModelView) {
        this.clientModelView = clientModelView;
    }


    @Override
    public ClientModelView getClientModelView() {
        return null;
    }

    @Override
    public void printLeaderCards() {

    }

    @Override
    public void printDeposits() {

    }

    @Override
    public void printStrongbox() {

    }

    @Override
    public void printDevGrid() throws IOException {
        JPanel gridpanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JButton dev1 = new JButton();
        dev1.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(0).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 0;
        c.gridy = 0;
        gridpanel.add(dev1,c);

        JButton dev2 = new JButton();
        dev2.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(1).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 1;
        c.gridy = 0;
        gridpanel.add(dev2,c);

        JButton dev3 = new JButton();
        dev3.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(2).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 2;
        c.gridy = 0;
        gridpanel.add(dev3,c);

        JButton dev4 = new JButton();
        dev4.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(3).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 3;
        c.gridy = 0;
        gridpanel.add(dev4,c);

        JButton dev5 = new JButton();
        dev5.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(4).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 0;
        c.gridy = 1;
        gridpanel.add(dev5,c);

        JButton dev6 = new JButton();
        dev6.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(5).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 1;
        c.gridy = 1;
        gridpanel.add(dev6,c);

        JButton dev7 = new JButton();
        dev7.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(6).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 2;
        c.gridy = 1;
        gridpanel.add(dev7,c);

        JButton dev8 = new JButton();
        dev8.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(7).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 3;
        c.gridy = 1;
        gridpanel.add(dev8,c);

        JButton dev9 = new JButton();
        dev9.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(8).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 0;
        c.gridy = 2;
        gridpanel.add(dev9,c);

        JButton dev10 = new JButton();
        dev10.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(9).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 1;
        c.gridy = 2;
        gridpanel.add(dev10,c);

        JButton dev11 = new JButton();
        dev11.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(10).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 2;
        c.gridy = 2;
        gridpanel.add(dev11,c);

        JButton dev12 = new JButton();
        dev12.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(11).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 3;
        c.gridy = 2;
        gridpanel.add(dev12,c);
    }

    @Override
    public void printResourceBuffer() {

    }

    @Override
    public void printMarketTray() {

    }

    @Override
    public void printFaithTrack() {

    }

    @Override
    public void printDevSpaces() {

    }

    @Override
    public void printBaseProdPower() {

    }

    @Override
    public void printResourcesLegend() {

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(()-> {
            try {
                GUI.createAndShowGUI();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    private static void createAndShowGUI() throws IOException {

        String url = "src/main/resources/images/punchboard/calamaio.png";
        Game game = new Game();
        game.setup();

        ImageIcon image = new ImageIcon(url);
        CustomFrame frame1 = new CustomFrame();
        JPanel gridpanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JButton dev1 = new JButton();
        dev1.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(game.getDevelopmentGrid().get(0).getLast().getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 0;
        c.gridy = 0;
        gridpanel.add(dev1,c);

        JButton dev2 = new JButton();
        dev2.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(game.getDevelopmentGrid().get(1).getLast().getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 1;
        c.gridy = 0;
        gridpanel.add(dev2,c);

        JButton dev3 = new JButton();
        dev3.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(game.getDevelopmentGrid().get(2).getLast().getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 2;
        c.gridy = 0;
        gridpanel.add(dev3,c);

        JButton dev4 = new JButton();
        dev4.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(game.getDevelopmentGrid().get(3).getLast().getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 3;
        c.gridy = 0;
        gridpanel.add(dev4,c);

        JButton dev5 = new JButton();
        dev5.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(game.getDevelopmentGrid().get(4).getLast().getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 0;
        c.gridy = 1;
        gridpanel.add(dev5,c);

        JButton dev6 = new JButton();
        dev6.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(game.getDevelopmentGrid().get(5).getLast().getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 1;
        c.gridy = 1;
        gridpanel.add(dev6,c);

        JButton dev7 = new JButton();
        dev7.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(game.getDevelopmentGrid().get(6).getLast().getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 2;
        c.gridy = 1;
        gridpanel.add(dev7,c);

        JButton dev8 = new JButton();
        dev8.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(game.getDevelopmentGrid().get(7).getLast().getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 3;
        c.gridy = 1;
        gridpanel.add(dev8,c);

        JButton dev9 = new JButton();
        dev9.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(game.getDevelopmentGrid().get(8).getLast().getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 0;
        c.gridy = 2;
        gridpanel.add(dev9,c);

        JButton dev10 = new JButton();
        dev10.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(game.getDevelopmentGrid().get(9).getLast().getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 1;
        c.gridy = 2;
        gridpanel.add(dev10,c);

        JButton dev11 = new JButton();
        dev11.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(game.getDevelopmentGrid().get(10).getLast().getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 2;
        c.gridy = 2;
        gridpanel.add(dev11,c);

        JButton dev12 = new JButton();
        dev12.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(game.getDevelopmentGrid().get(11).getLast().getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 3;
        c.gridy = 2;
        gridpanel.add(dev12,c);




        //frame1.setLayout(null);
        frame1.add(gridpanel);
        frame1.pack();

     /*   JLabel label = new JLabel();
        label.setText("Welcome to Master of Renaissance");

        label.setIcon(image);

        label.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT, CENTER, RIGHT of imageicon
        label.setVerticalTextPosition(JLabel.TOP); // set text TOP, CENTER, BOTTOM of imageicon
        label.setForeground(new Color(0, 0, 0)); //color text
        label.setFont(new Font("Ringbearer", Font.PLAIN, 50));

        String url2 = "src/main/resources/images/board/Masters of Renaissance_PlayerBoard (11_2020)-1.png";

        JLabel label2 = new JLabel();
        label2.setIcon(new ImageIcon(new ImageIcon(url2).getImage().getScaledInstance(1080, 720, Image.SCALE_AREA_AVERAGING)));


        JPanel panel1 = new JPanel();
        JButton button1 = new JButton();
        button1.addActionListener(e-> System.out.println("lalala"));
        button1.setBounds(0,0,100,100);
        panel1.setBounds(0,0,1080,720);

        panel1.add(label2);

        JPanel panel2 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JButton buttonLeader = new JButton();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;

        panel2.add(buttonLeader, c);
        JButton buttonLeader2 = new JButton();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        panel2.add(buttonLeader2, c);


        String urlLeader2 = "src/main/resources/images/front/Masters of Renaissance_Cards_FRONT_3mmBleed_1-51-1.png";

        buttonLeader.setIcon(new ImageIcon(new ImageIcon(urlLeader1).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        buttonLeader2.setIcon(new ImageIcon(new ImageIcon(urlLeader2).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));


        panel2.setBounds(1080, 0, 175,720);


        CustomFrame frame1 = new CustomFrame();
        frame1.setLayout(null);
        frame1.pack();
        frame1.add(panel1);
        frame1.add(panel2);*/

    }
}

