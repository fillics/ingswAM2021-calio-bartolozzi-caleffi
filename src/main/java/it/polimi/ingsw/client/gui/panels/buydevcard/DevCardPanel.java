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

public class DevCardPanel extends JPanel implements ActionListener {
    private int id;

    private DevGridPanel devGridPanel;
    private Image background;
    private JPanel mainPanel, buttonsPanel, cardPanel;
    private GridBagConstraints c;
    private JButton backBtn, confirmBtn;
    private JLabel card;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, devGridPanel.getBuyDevCardPanel().getGui().getDimension().width, devGridPanel.getBuyDevCardPanel().getGui().getDimension().height, null);

    }

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
        mainPanel = new JPanel();
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

    public void createCard(String path, int width, int height) {

        cardPanel = new JPanel();
        cardPanel.setOpaque(false);
        cardPanel.setBackground(new Color(0, 0, 0, 0));
        cardPanel.setLayout(new GridBagLayout());

        card = new JLabel();

        try {
            card.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(path)).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        cardPanel.add(card);

    }

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

    public void setButton(JButton button){
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(250, 50));
        button.setFont(new Font(button.getFont().getName(), button.getFont().getStyle(), 25));

    }


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
