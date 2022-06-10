package clientModule.forms.VisualizeForm;

import clientModule.App;
import clientModule.Client;
import common.utility.Request;
import common.utility.Response;
import common.utility.User;
import resources.LocaleBundle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class DrawSpace extends JPanel {
    private ArrayList<PointWithColor> points;
    private Client client;

    public DrawSpace(Client client) {
        points = new ArrayList<>();
        this.client = client;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                checkForClick(e);
            }
        });
    }

    public void setUser(User user) {
        this.client.setUser(user);
    }

    private void checkForClick(MouseEvent e) {
        ArrayList<PointWithColor> copy = new ArrayList<>(points);
        for (PointWithColor point : copy) {
            if (point.x - point.radius <= e.getX() && e.getX() <= point.x + point.radius && point.y - point.radius <= e.getY() && e.getY() <= point.y + point.radius) {
                int type = JOptionPane.showOptionDialog(null,
                        "key: " + point.getKey() + "\n" + point.getMarine() + "\n Что вы хотите сделать?",
                        "Info",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        new String[]{"Remove", "Cancel"},
                        "default");
                if (type == 0) {
                    try {
                        client.send(new Request("remove_key",
                                String.valueOf(point.getKey()),
                                client.getUser()));
                        Response fromServer = client.receive();
                        JOptionPane.showMessageDialog(null, fromServer.localize(false));
                    } catch (IOException exception) {
                        JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("ioPaneError"));
                    } catch (ClassNotFoundException classNotFoundException) {
                        JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("classNotFoundError"));
                    }
                }
            }
        }
    }

    public void addPointWithColor(PointWithColor point) {
        points.add(point);
    }

    public void removeAddPointWithColor(PointWithColor point) {
        points.remove(point);
    }

    public void clearPoints() {
        points.clear();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (PointWithColor point : points) {
            g2.setColor(point.getColor());
            g2.fillOval(point.x - point.radius / 2, point.y - point.radius / 2, point.radius, point.radius);
            g2.setColor(new Color(255 - point.getColor().getRed(), 255 - point.getColor().getGreen(), 255 - point.getColor().getBlue()));
            g2.setFont(new Font("Arial", Font.PLAIN, 10));
            g2.drawString(point.getText(), point.x - g2.getFont().getSize() / 2, point.y + g2.getFont().getSize() / 2);
        }
    }
}
