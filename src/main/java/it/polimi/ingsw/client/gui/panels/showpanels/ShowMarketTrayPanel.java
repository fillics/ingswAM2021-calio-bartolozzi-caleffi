package it.polimi.ingsw.client.gui.panels.showpanels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.client.gui.panels.BoardPanel;
import it.polimi.ingsw.client.gui.panels.MarketPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class ShowMarketTrayPanel extends JPanel implements ActionListener {
    private MarketPanel marketPanel;
    private JPanel button, market, mainPanel;
    private GUI gui;
    private Image background;
    private GridBagConstraints c;
    private JButton back;

    /**
     * Method used to set the panel background.
     * @param g is a Graphics object
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getDimension().width, gui.getDimension().height, null);
    }

    public ShowMarketTrayPanel(GUI gui) {
        this.gui=gui;

        InputStream is = getClass().getResourceAsStream("/images/background/game.png");
        try {
            assert is != null;
            background = ImageIO.read(is);
        } catch (IOException ignored) {
        }
        c = new GridBagConstraints();

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        JPanel space = new JPanel();
        space.setPreferredSize(new Dimension(50,50));
        space.setOpaque(false);

        createMarketTrayPanel();
        c.gridx=0;
        c.gridy=1;
        mainPanel.add(marketPanel, c);

        c.gridx=0;
        c.gridy=0;
        mainPanel.add(space, c);

        createButtonPanel();
        c.gridx=0;
        c.gridy=2;
        c.insets = new Insets(25, 0,0,0);
        mainPanel.add(button, c);

        mainPanel.setOpaque(false);
        this.add(mainPanel);
    }

    public void createMarketTrayPanel(){
        market = new JPanel();
        market.setLayout(new BoxLayout(market, BoxLayout.X_AXIS));
        market.setPreferredSize(new Dimension(550,700));

        marketPanel= new MarketPanel(gui, true);
        marketPanel.setPreferredSize(new Dimension(550,700));

        market.add(marketPanel);
    }

    public void createButtonPanel(){
        button= new JPanel();
        button.setLayout(new GridBagLayout());
        button.setOpaque(false);

        back = new JButton("BACK TO THE BOARD");
        back.setPreferredSize(new Dimension(200,50));
        back.addActionListener(this);
        c.gridx=1;
        c.gridy=0;
        button.add(back,c);
    }

    /**
     * Method that, based on the button clicked, perform a determined action.
     * @param e is a ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            gui.switchPanels(new BoardPanel(gui));
            gui.createMessageFromServer("Back to your board");

        }
    }
}
