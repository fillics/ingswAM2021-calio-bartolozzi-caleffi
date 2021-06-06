package it.polimi.ingsw.client.gui.panels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.client_packets.PacketTakeResourceFromMarket;
import it.polimi.ingsw.model.cards.leadercards.ConcreteStrategyMarble;
import it.polimi.ingsw.model.marbles.MarketTray;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class TakeResourceFromMarketPanel extends JPanel implements ActionListener {
    private final GUI gui;
    private Image background;
    private GridBagConstraints c;
    private JPanel leaderCards, buttonPanel, marketPanel;
    private MarketPanel marketTrayPanel;
    private ArrayList<LeaderCardPanel> leaderCardPanels;
    private JButton confirm,  back;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getDimension().width, gui.getDimension().height, null);
    }

    public TakeResourceFromMarketPanel(GUI gui) {
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/game.png");
        try {
            background = ImageIO.read(is);
        } catch (IOException ignored) {
        }
        c = new GridBagConstraints();

        this.setLayout(new GridBagLayout());

        createLeaderCardsPanel();
        c.gridx=0;
        c.gridy=0;
        this.add(leaderCards, c);

        createMarketTrayPanel();
        c.gridx=1;
        c.gridy=0;
        this.add(marketTrayPanel, c);

        createButtonPanel();
        c.gridx=2;
        c.gridy=0;
        this.add(buttonPanel, c);

        this.setOpaque(false);
    }

    private void createLeaderCardsPanel(){
        leaderCards = new JPanel();
        leaderCards.setLayout(new GridBagLayout());
        c.insets = new Insets(10, 10, 10, 10);

        leaderCardPanels = new ArrayList<>();
        for(int i = 0; i < gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().size(); i++){
            if(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getStrategy() instanceof ConcreteStrategyMarble){
                LeaderCardPanel leaderCardPanel1 = new LeaderCardPanel(gui, gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getId());
                leaderCardPanels.add(leaderCardPanel1);
                leaderCardPanel1.setOpaque(false);
                c.gridx=0;
                c.gridy=i;
                leaderCards.add(leaderCardPanels.get(i), c);
            }
        }
        leaderCards.setOpaque(false);
    }

    public void createMarketTrayPanel(){
        marketPanel = new JPanel();
        marketPanel.setLayout(new BoxLayout(marketPanel, BoxLayout.X_AXIS));
        marketPanel.setPreferredSize(new Dimension(550,700));

        marketTrayPanel= new MarketPanel(gui);
        marketTrayPanel.setPreferredSize(new Dimension(550,700));

        marketPanel.add(marketTrayPanel);
    }

    public void createButtonPanel(){
        buttonPanel= new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setOpaque(false);

        confirm = new JButton("CONFIRM");
        back = new JButton("BACK");
        confirm.setPreferredSize(new Dimension(100,50));
        confirm.addActionListener(this);
        back.setPreferredSize(new Dimension(100,50));
        back.addActionListener(this);
        c.gridx=0;
        c.gridy=0;
        buttonPanel.add(confirm,c);
        c.gridx=0;
        c.gridy=1;
        buttonPanel.add(back,c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirm){
            if(!marketTrayPanel.getLine().equals("") && marketTrayPanel.getNumline()!=0){
                PacketTakeResourceFromMarket takeResourceFromMarket = new PacketTakeResourceFromMarket(marketTrayPanel.getLine(), marketTrayPanel.getNumline(), null);
                ObjectMapper mapper = new ObjectMapper();

                String jsonResult = null;
                try {
                    jsonResult = mapper.writeValueAsString(takeResourceFromMarket);
                } catch (JsonProcessingException jsonProcessingException) {
                    jsonProcessingException.printStackTrace();
                }
                gui.getClient().getSocketClientConnection().sendToServer(jsonResult);
                BoardPanel boardPanel = new BoardPanel(gui);
                gui.switchPanels(boardPanel);
            }
        }
        if(e.getSource() == back){
            BoardPanel boardPanel = new BoardPanel(gui);
            gui.switchPanels(boardPanel);
        }
    }
}
