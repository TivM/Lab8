/*
 * Created by JFormDesigner on Mon May 03 22:42:07 MSK 2021
 */

package clientModule.forms.RemoveKeyForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.*;

import clientModule.App;
import clientModule.Client;
import clientModule.forms.MainMenuForm.MainMenu;
import common.data.Chapter;
import common.data.Coordinates;
import common.data.Weapon;
import common.exceptions.NotDeclaredValueException;
import common.utility.Request;
import common.utility.Response;
import common.utility.SpaceMarineLite;
import common.utility.User;
import net.miginfocom.swing.*;
import resources.LocaleBundle;

/**
 * @author unknown
 */
public class RemoveKey extends JPanel {
    public RemoveKey(Client client) {
        initComponents();
        this.client = client;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.mainFrame.setContentPane(App.mainMenu.getMainMenuPanel());
                App.mainFrame.validate();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder errors = new StringBuilder();
                int key = 0;
                try {
                    key = Integer.parseInt(argumentField.getText());
                    if (key <= 0) throw new NotDeclaredValueException();
                } catch (NumberFormatException exception) {
                    errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError13"));
                } catch (NotDeclaredValueException notDeclaredValueException) {
                    errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError14"));
                }
                if (errors.toString().equals("")) {
                    try {
                        client.send(new Request("remove_key",
                                String.valueOf(key),
                                client.getUser()));
                        Runnable receive = new Runnable() {
                            @Override
                            public void run() {
                                Response fromServer = null;
                                try {
                                    fromServer = client.receive();
                                    JOptionPane.showMessageDialog(null, fromServer.localize(false));
                                } catch (IOException exception) {
                                    JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("ioPaneError"));
                                } catch (ClassNotFoundException classNotFoundException) {
                                    JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("classNotFoundError"));
                                }
                            }
                        };
                        Thread thread = new Thread(receive);
                        thread.start();
                    } catch (IOException exception) {
                        JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("ioPaneError"));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, errors.toString());
                }
            }
        });
    }

    public void setUser(User user) {
        this.client.setUser(user);
        this.currentUser.setText(user.getLogin());
    }

    public void localize() {
        backButton.setText(LocaleBundle.getCurrentBundle().getString("back_button"));
        label1.setText(LocaleBundle.getCurrentBundle().getString("removeKey_label1"));
        removeButton.setText(LocaleBundle.getCurrentBundle().getString("remove_button"));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        removeKeyPanel = new JPanel();
        currentUser = new JLabel();
        name = new JLabel();
        backButton = new JButton();
        label1 = new JLabel();
        argumentField = new JTextField();
        removeButton = new JButton();

        //======== removeKeyPanel ========
        {
            removeKeyPanel.setBackground(new Color(225, 183, 144));
            removeKeyPanel.setLayout(new MigLayout(
                "insets 0,hidemode 3,align center center",
                // columns
                "[grow,fill]" +
                "[110,grow,fill]" +
                "[110,grow,fill]" +
                "[110,grow,fill]" +
                "[110,grow,fill]" +
                "[110,grow,fill]" +
                "[110,grow,fill]" +
                "[grow,fill]",
                // rows
                "[80,grow,fill]" +
                "[25,grow,fill]" +
                "[35,grow,fill]" +
                "[35,grow,fill]" +
                "[35,grow,fill]" +
                "[35,grow,fill]" +
                "[35,grow,fill]" +
                "[35,grow,fill]" +
                "[25,grow,fill]" +
                "[25,grow,fill]" +
                "[35,grow,fill]"));

            //---- currentUser ----
            currentUser.setText("test");
            currentUser.setForeground(Color.white);
            currentUser.setHorizontalAlignment(SwingConstants.CENTER);
            currentUser.setFont(new Font("Arial", Font.BOLD, 20));
            removeKeyPanel.add(currentUser, "cell 1 0");

            //---- name ----
            name.setText("SpaceMarine");
            name.setHorizontalAlignment(SwingConstants.CENTER);
            name.setFont(new Font("Arial Black", Font.BOLD, 40));
            name.setBackground(new Color(255, 102, 102));
            name.setForeground(new Color(40, 61, 82));
            removeKeyPanel.add(name, "cell 2 0 4 1,align center center,grow 0 0");

            //---- backButton ----
            backButton.setText("\u041d\u0430\u0437\u0430\u0434");
            backButton.setBackground(Color.white);
            backButton.setForeground(new Color(40, 61, 82));
            backButton.setFont(new Font("Arial", Font.BOLD, 12));
            backButton.setBorder(new EtchedBorder());
            removeKeyPanel.add(backButton, "cell 6 0,align center center,grow 0 0,width 80:80:110,height 30:30:50");

            //---- label1 ----
            label1.setText("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043a\u043b\u044e\u0447");
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setFont(new Font("Arial", Font.BOLD, 16));
            label1.setForeground(new Color(40, 61, 82));
            removeKeyPanel.add(label1, "cell 3 2 2 1");

            //---- argumentField ----
            argumentField.setBackground(Color.white);
            argumentField.setForeground(Color.black);
            removeKeyPanel.add(argumentField, "cell 2 3 4 2,aligny center,grow 100 0,height 40:40:60");

            //---- removeButton ----
            removeButton.setText("\u0423\u0434\u0430\u043b\u0438\u0442\u044c");
            removeButton.setForeground(new Color(225, 183, 144));
            removeButton.setFont(new Font("Arial", Font.BOLD, 12));
            removeButton.setBackground(new Color(40, 61, 82));
            removeKeyPanel.add(removeButton, "cell 3 5 2 1,aligny center,grow 100 0,height 30:35:55");
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel removeKeyPanel;
    private JLabel currentUser;
    private JLabel name;
    private JButton backButton;
    private JLabel label1;
    private JTextField argumentField;
    private JButton removeButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private Client client;

    public JPanel getRemoveKeyPanel() {
        return removeKeyPanel;
    }
}
