import Controllers.SMSController;
import Views.LeftPanelView;
import Views.MiddlePanelView;
import Views.RightPanelView;

import javax.swing.*;
import java.awt.*;

public class SMSApplication {
    public static void main(String[] args) {
        LeftPanelView lp = new LeftPanelView();
        RightPanelView rp = new RightPanelView();
        MiddlePanelView mp = new MiddlePanelView();
        SMSController controller = new SMSController(lp, mp, rp);


        lp.setAddNewVbdButtonListener(controller);
        mp.setAddNewBTSButtonListener(controller);
        mp.setAddNewBCSButtonListener(controller);
        mp.setNewBCSLButtonListener(controller);
        rp.setAddNewVrdButtonListener(controller);


        JFrame frame = new JFrame("SMS Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.setSize(1640, 600);
        frame.getContentPane().add(lp);
        frame.getContentPane().add(mp);
        frame.getContentPane().add(rp);

        lp.setPreferredSize(new Dimension(400, 550));
        lp.setMaximumSize(new Dimension(400, 550));

        mp.setPreferredSize(new Dimension(800, 550));
        mp.setMaximumSize(new Dimension(800, 550));

        rp.setPreferredSize(new Dimension(400, 550));
        rp.setMaximumSize(new Dimension(400, 550));

        frame.setVisible(true);
    }
}