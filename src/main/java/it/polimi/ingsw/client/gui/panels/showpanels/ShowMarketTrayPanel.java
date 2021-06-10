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
    private JPanel button, market;
    private GUI gui;
    private Image background;
    private GridBagConstraints c;
    private JButton back;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getDimension().width, gui.getDimension().height, null);
    }

    public ShowMarketTrayPanel(GUI gui) {
        this.gui=gui;

        InputStream is = getClass().getResourceAsStream("/images/background/backgroundGame2.png");
        try {
            assert is != null;
            background = ImageIO.read(is);
        } catch (IOException ignored) {
        }
        c = new GridBagConstraints();

        this.setLayout(new GridBagLayout());

        JPanel space = new JPanel();
        space.setPreferredSize(new Dimension(50,50));
        space.setOpaque(false);

        createMarketTrayPanel();
        c.gridx=0;
        c.gridy=0;
        this.add(marketPanel, c);

        c.gridx=1;
        c.gridy=0;
        this.add(space, c);

        createButtonPanel();
        c.gridx=2;
        c.gridy=0;
        this.add(button, c);

        this.setOpaque(false);
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

        back = new JButton("BACK");
        back.setPreferredSize(new Dimension(100,50));
        back.addActionListener(this);
        c.gridx=1;
        c.gridy=0;
        button.add(back,c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            BoardPanel boardPanel = new BoardPanel(gui);
            gui.switchPanels(boardPanel);
        }
    }
}
