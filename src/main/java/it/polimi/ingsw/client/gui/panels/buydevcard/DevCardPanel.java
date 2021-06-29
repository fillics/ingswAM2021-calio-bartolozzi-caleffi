package it.polimi.ingsw.client.gui.panels.buydevcard;

import it.polimi.ingsw.client.gui.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Class that creates the panel that contains a specific development card
 */
public class DevCardPanel extends JPanel implements ActionListener {
    private final int id;
    private final DevGridPanel devGridPanel;
    private Image background;
    private JPanel buttonsPanel;
    private JPanel cardPanel;
    private final GridBagConstraints c;
    private JButton backBtn, confirmBtn;

    /**
     * Method used to set the panel background.
     * @param g is a Graphics object
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, devGridPanel.getBuyDevCardPanel().getGui().getDimension().width, devGridPanel.getBuyDevCardPanel().getGui().getDimension().height, null);

    }

    /**
     * Class' constructor
     * @param devGridPanel is the development grid panel
     * @param path is the path used to reach the image of a specific development card
     * @param width is the width of the panel
     * @param height is the height of the panel
     * @param id is the id of the development card
     */
    public DevCardPanel(DevGridPanel devGridPanel, String path, int width, int height, int id) {
        this.devGridPanel = devGridPanel;
        this.id = id;
        InputStream is = getClass().getResourceAsStream("/images/background/game.png");
        try {
            assert is != null;
            background = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();


        c.gridy = 0;
        c.gridx = 0;
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(new Color(0, 0, 0, 0));
        mainPanel.add(emptyPanel, c);

        createCard(path, width, height);
        c.insets = new Insets(150, 0,0,0);
        c.gridx = 0;
        c.gridy = 1;
        mainPanel.add(cardPanel, c);

        createButtons();
        c.gridx = 0;
        c.gridy = 2;
        mainPanel.add(buttonsPanel, c);
        mainPanel.setOpaque(false);

        this.add(mainPanel);
    }

    /**
     * Method that creates the development card
     * @param path is the path used to reach the image of a specific development card
     * @param width is the width of the panel
     * @param height is the height of the panel
     */
    public void createCard(String path, int width, int height) {

        cardPanel = new JPanel();
        cardPanel.setOpaque(false);
        cardPanel.setBackground(new Color(0, 0, 0, 0));
        cardPanel.setLayout(new GridBagLayout());

        JLabel card = new JLabel();

        try {
            card.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(path)).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        cardPanel.add(card);

    }

    /**
     * Method that creates the buttons used to go back or confirm the operation.
     */
    public void createButtons() {
        buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        confirmBtn = new JButton("CONFIRM DEV.CARD");
        backBtn = new JButton("BACK TO THE GRID");

        buttonsPanel.setLayout(new GridBagLayout());

        c.insets = new Insets(25, 20, 20, 20);
        c.gridx = 0;
        c.gridy = 0;
        buttonsPanel.add(backBtn, c);

        c.gridx = 1;
        c.gridy = 0;
        buttonsPanel.add(confirmBtn, c);

        setButton(confirmBtn);
        setButton(backBtn);

    }

    /**
     * Method that sets the size and font of a button
     * @param button is the button chosen
     */
    public void setButton(JButton button){
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(250, 50));
        button.setFont(new Font(button.getFont().getName(), button.getFont().getStyle(), 25));

    }


    /**
     * Method that, based on the button clicked, perform a determined action.
     * @param e is a ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmBtn) {
            devGridPanel.setIdCard(id);
            devGridPanel.getBuyDevCardPanel().getGui().switchPanels(devGridPanel.getBuyDevCardPanel());

        }
        if (e.getSource() == backBtn) {
            devGridPanel.getBuyDevCardPanel().getGui().switchPanels(devGridPanel.getBuyDevCardPanel());
        }
    }
}
