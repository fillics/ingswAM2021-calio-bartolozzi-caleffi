package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.model.board.resources.ResourceType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Class that creates the panel used to show the strongbox of a player
 */
public class StrongboxPanel extends JPanel implements ActionListener {
    private GUI gui;
    private Image background;
    private final JButton strongboxButton;
    private final DepositsPanel depositsPanel;
    private JLabel coin;
    private JLabel stone;
    private JLabel servant;
    private JLabel shield;

    /**
     * Method used to set the panel background.
     * @param g is a Graphics object
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, 250, 180, null);
    }

    /**
     * Class' constructor
     * @param gui gui is the GUI object linked to this panel
     * @param depositsPanel is the deposits panel
     */
    public StrongboxPanel(GUI gui, DepositsPanel depositsPanel) {
        this.depositsPanel = depositsPanel;
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/board/strongbox.jpg");
        try {
            background = ImageIO.read(Objects.requireNonNull(is));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setPreferredSize(new Dimension(250, 180));
        strongboxButton = new JButton();
        strongboxButton.addActionListener(this);
        strongboxButton.setBackground(new Color(151, 74, 74));


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

        setValues(gui.getClient().getClientModelView());

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

    /**
     * Class' constructor used in the composition of the other player board panel
     * @param clientModelView is the client model view in which are contained the information
     * @param depositsPanel is the deposits panel
     */
    public StrongboxPanel(ClientModelView clientModelView, DepositsPanel depositsPanel) {
        this.depositsPanel = depositsPanel;
        InputStream is = getClass().getResourceAsStream("/images/board/strongbox.jpg");
        try {
            background = ImageIO.read(Objects.requireNonNull(is));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setPreferredSize(new Dimension(250, 180));
        strongboxButton = new JButton();
        strongboxButton.addActionListener(this);
        strongboxButton.setBackground(new Color(151, 74, 74));


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

        setValues(clientModelView);

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

    /**
     * Method that sets the number of resources for every resource
     * @param clientModelView is the client model view in which are contained the information
     */
    public void setValues(ClientModelView clientModelView){
        coin = new JLabel();
        stone = new JLabel();
        servant = new JLabel();
        shield = new JLabel();

        try {
            coin.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/coin.png")).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
            stone.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/stone.png")).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
            servant.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/servant.png")).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
            shield.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/shield.png")).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        coin.setText(String.valueOf(clientModelView.getLiteBoard().getStrongbox().getStrongbox().get(ResourceType.COIN)));
        coin.setForeground(Color.WHITE);
        coin.setFont(new Font(coin.getFont().getName(), Font.PLAIN, 20));
        stone.setText(String.valueOf(clientModelView.getLiteBoard().getStrongbox().getStrongbox().get(ResourceType.STONE)));
        stone.setForeground(Color.WHITE);
        stone.setFont(new Font(stone.getFont().getName(), Font.PLAIN, 20));
        servant.setText(String.valueOf(clientModelView.getLiteBoard().getStrongbox().getStrongbox().get(ResourceType.SERVANT)));
        servant.setForeground(Color.WHITE);
        servant.setFont(new Font(servant.getFont().getName(), Font.PLAIN, 20));
        shield.setText(String.valueOf(clientModelView.getLiteBoard().getStrongbox().getStrongbox().get(ResourceType.SHIELD)));
        shield.setForeground(Color.WHITE);
        shield.setFont(new Font(shield.getFont().getName(), Font.PLAIN, 20));
    }

    /**
     * Method that, based on the button clicked, perform a determined action.
     * @param e is a ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == strongboxButton){
            depositsPanel.getIdDepot().add(gui.getClient().getClientModelView().getLiteBoard().getDeposits().size() + 1);
        }
    }

    /**
     * Class' getter
     * @return the strongbox button
     */
    public JButton getStrongboxButton() {
        return strongboxButton;
    }
}
