package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.model.board.resources.ResourceType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class StrongboxPanel extends JPanel implements ActionListener {
    private GUI gui;
    private Image background;
    private final JButton strongboxButton;
    private DepositsPanel depositsPanel;


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, 250, 180, null);
    }

    public StrongboxPanel(GUI gui, DepositsPanel depositsPanel) {
        this.depositsPanel = depositsPanel;
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/board/strongbox.jpg");
        try {
            background = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setPreferredSize(new Dimension(250, 180));
        strongboxButton = new JButton();
        strongboxButton.addActionListener(this);
        strongboxButton.setBackground(new Color(151, 74, 74));
        JLabel coin = new JLabel();
        JLabel stone = new JLabel();
        JLabel servant = new JLabel();
        JLabel shield = new JLabel();


        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(250, 180));
        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(250, 80));
        JPanel panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(250, 80));
        strongboxButton.setPreferredSize(new Dimension(250, 20));

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

        try {
            coin.setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream("/images/punchboard/coin.png").readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
            stone.setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream("/images/punchboard/stone.png").readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
            servant.setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream("/images/punchboard/servant.png").readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
            shield.setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream("/images/punchboard/shield.png").readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        coin.setText(String.valueOf(gui.getClient().getClientModelView().getLiteBoard().getStrongbox().getStrongbox().get(ResourceType.COIN)));
        coin.setForeground(Color.WHITE);
        coin.setFont(new Font(coin.getFont().getName(), Font.PLAIN, 20));
        stone.setText(String.valueOf(gui.getClient().getClientModelView().getLiteBoard().getStrongbox().getStrongbox().get(ResourceType.STONE)));
        stone.setForeground(Color.WHITE);
        stone.setFont(new Font(stone.getFont().getName(), Font.PLAIN, 20));
        servant.setText(String.valueOf(gui.getClient().getClientModelView().getLiteBoard().getStrongbox().getStrongbox().get(ResourceType.SERVANT)));
        servant.setForeground(Color.WHITE);
        servant.setFont(new Font(servant.getFont().getName(), Font.PLAIN, 20));
        shield.setText(String.valueOf(gui.getClient().getClientModelView().getLiteBoard().getStrongbox().getStrongbox().get(ResourceType.SHIELD)));
        shield.setForeground(Color.WHITE);
        shield.setFont(new Font(shield.getFont().getName(), Font.PLAIN, 20));

        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);

        panel2.add(coin);
        panel2.add(Box.createRigidArea(new Dimension(50, 80)));
        panel2.add(stone);
        panel2.add(Box.createRigidArea(new Dimension(50, 80)));

        panel3.add(servant);
        panel3.add(Box.createRigidArea(new Dimension(40, 0)));
        panel3.add(shield);
        panel3.add(Box.createRigidArea(new Dimension(40, 0)));


        panel1.add(strongboxButton);
        panel1.add(panel2);
        panel1.add(panel3);


        this.add(panel1);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == strongboxButton){
            depositsPanel.getIdDepot().add(gui.getClient().getClientModelView().getLiteBoard().getDeposits().size() + 1);
        }
    }


    public JButton getStrongboxButton() {
        return strongboxButton;
    }
}
