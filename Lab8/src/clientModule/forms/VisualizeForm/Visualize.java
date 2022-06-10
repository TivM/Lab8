/*
 * Created by JFormDesigner on Wed May 05 00:38:26 MSK 2021
 */

package clientModule.forms.VisualizeForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import clientModule.App;
import clientModule.Client;
import common.data.SpaceMarine;
import common.utility.Request;
import common.utility.Response;
import common.utility.User;
import net.miginfocom.swing.*;
import resources.LocaleBundle;

/**
 * @author unknown
 */
public class Visualize extends JPanel {
    public Visualize(Client client) {
        this.client = client;
        initComponents();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isActive = false;
                App.mainFrame.setContentPane(App.mainMenu.getMainMenuPanel());
                App.mainFrame.validate();
            }
        });
    }

    public void startThread() {
        isActive = true;
        draw = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isActive) {
                    drawSpace.clearPoints();
                    setObjects();
                    drawSpace.repaint();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignored) {}
                }
            }
        });
        draw.start();
    }

    public void setObjects() {
        try {
            client.send(new Request("show", "", client.getUser()));
            Response fromServer = client.receive();
            TreeMap<Integer, SpaceMarine> collection = fromServer.getCollection();
            myCopy.removeIf(point -> !collection.containsValue(point.getMarine()));
            int maxX = -1000;
            int minX = Integer.MAX_VALUE;
            int maxY = -1000;
            int minY = Integer.MAX_VALUE;
            int weight = (int) (drawSpace.getWidth() * 0.9);
            int height = (int) (drawSpace.getHeight() * 0.9);
            if (collection.size() == 1) {
                PointWithColor point = new PointWithColor(
                        weight / 2,
                        height / 2,
                        Color.decode(collection.get(collection.firstKey())
                                .getOwner()
                                .getColor()),
                        height / 10,
                        String.valueOf(collection.get(collection.firstKey()).getId()),
                        collection.get(collection.firstKey()),
                        collection.firstKey());
                /*if (!myCopy.contains(point)) {
                    point.radius = 1;
                    myCopy.add(point);
                } else {
                    int index = 0;
                    for (PointWithColor fromList : myCopy) {
                        if (fromList.equals(point)) break;
                        index++;
                    }
                    myCopy.get(index).radius += 1;
                    if (myCopy.get(index).radius > height / 10)
                        myCopy.get(index).radius = height / 10;
                    point.radius = myCopy.get(index).radius;
                }*/
                drawSpace.addPointWithColor(point);
                return;
            }
            for (Map.Entry<Integer, SpaceMarine> e : collection.entrySet()) {
                if (e.getValue().getCoordinates().getX() > maxX) maxX = (int) e.getValue().getCoordinates().getX();
                if (e.getValue().getCoordinates().getX() < minX) minX = (int) e.getValue().getCoordinates().getX();
                if (e.getValue().getCoordinates().getY() > maxY)
                    maxY = (int) (double) e.getValue().getCoordinates().getY();
                if (e.getValue().getCoordinates().getY() < minY)
                    minY = (int) (double) e.getValue().getCoordinates().getY();
            }
            for (Map.Entry<Integer, SpaceMarine> e : collection.entrySet()) {
                int oldX = (int) e.getValue().getCoordinates().getX();
                int oldY = (int) (double) e.getValue().getCoordinates().getY();
                int x = (int) ((oldX - minX) * (weight / (maxX - minX)) + (drawSpace.getWidth() * 0.05));
                int y = (int) ((oldY - minY) * (height / (maxY - minY)) + (drawSpace.getHeight() * 0.05));
                PointWithColor point = new PointWithColor(
                        x,
                        y,
                        Color.decode(e.getValue().getOwner().getColor()),
                        height / 10,
                        String.valueOf(e.getValue().getId()),
                        e.getValue(),
                        e.getKey());
                /*if (!myCopy.contains(point)) {
                    point.radius = 1;
                    myCopy.add(point);
                } else {
                    int index = 0;
                    for (PointWithColor fromList : myCopy) {
                        if (fromList.equals(point)) break;
                        index++;
                    }
                    myCopy.get(index).radius += 1;
                    if (myCopy.get(index).radius > height / 10)
                        myCopy.get(index).radius = height / 10;
                    point.radius = myCopy.get(index).radius;
                }*/
                drawSpace.addPointWithColor(point);
            }
        } catch (IOException | ClassNotFoundException ignored) {}
    }

    public void localize() {
        backButton.setText(LocaleBundle.getCurrentBundle().getString("back_button"));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        visualizePanel = new JPanel();
        currentUser = new JLabel();
        name = new JLabel();
        backButton = new JButton();
        drawSpace = new DrawSpace(this.client);

        //======== visualizePanel ========
        {
            visualizePanel.setBackground(new Color(225, 183, 144));
            visualizePanel.setLayout(new MigLayout(
                "insets 0,hidemode 3",
                // columns
                "[70,grow,fill]" +
                "[70,grow,fill]" +
                "[grow,fill]" +
                "[70,grow,fill]" +
                "[70,grow,fill]",
                // rows
                "[80,grow,fill]" +
                "[35,grow,fill]" +
                "[35,grow,fill]" +
                "[35,grow,fill]" +
                "[35,grow,fill]" +
                "[35,fill]" +
                "[35,grow,fill]" +
                "[35,grow,fill]" +
                "[50,grow,fill]"));

            //---- currentUser ----
            currentUser.setText("test");
            currentUser.setHorizontalAlignment(SwingConstants.CENTER);
            currentUser.setFont(new Font("Arial", Font.BOLD, 14));
            currentUser.setForeground(new Color(40, 61, 82));
            visualizePanel.add(currentUser, "cell 0 0");

            //---- name ----
            name.setText("SpaceMarine");
            name.setHorizontalAlignment(SwingConstants.CENTER);
            name.setFont(new Font("Arial Black", Font.BOLD, 36));
            name.setBackground(new Color(255, 102, 102));
            name.setForeground(new Color(40, 61, 82));
            visualizePanel.add(name, "cell 2 0,align center center,grow 0 0");

            //---- backButton ----
            backButton.setText("\u041d\u0430\u0437\u0430\u0434");
            backButton.setBackground(new Color(40, 61, 82));
            backButton.setFont(new Font("Arial", Font.BOLD, 12));
            backButton.setForeground(Color.white);
            backButton.setBorder(new EtchedBorder());
            visualizePanel.add(backButton, "cell 4 0,align center center,grow 0 0,width 70:70:100");

            //======== drawSpace ========
            {
                drawSpace.setBackground(new Color(225, 183, 144));
                drawSpace.setLayout(null);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < drawSpace.getComponentCount(); i++) {
                        Rectangle bounds = drawSpace.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = drawSpace.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    drawSpace.setMinimumSize(preferredSize);
                    drawSpace.setPreferredSize(preferredSize);
                }
            }
            visualizePanel.add(drawSpace, "cell 0 1 5 8");
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public void setUser(User user) {
        this.client.setUser(user);
        this.currentUser.setText(user.getLogin());
        this.drawSpace.setUser(user);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel visualizePanel;
    private JLabel currentUser;
    private JLabel name;
    private JButton backButton;
    private DrawSpace drawSpace;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private Client client;
    private boolean isActive;
    private Thread draw;
    private ArrayList<PointWithColor> myCopy = new ArrayList<>();

    public JPanel getVisualizePanel() {
        return visualizePanel;
    }
}
