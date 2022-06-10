/*
 * Created by JFormDesigner on Mon May 03 23:01:13 MSK 2021
 */

package clientModule.forms.RemoveByWeaponForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.*;

import clientModule.App;
import clientModule.Client;
import clientModule.forms.MainMenuForm.MainMenu;
import common.exceptions.NotDeclaredValueException;
import common.utility.Request;
import common.utility.Response;
import common.utility.User;
import net.miginfocom.swing.*;
import resources.LocaleBundle;

/**
 * @author unknown
 */
public class RemoveByWeapon extends JPanel {
    public RemoveByWeapon(Client client) {
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
                try {
                    client.send(new Request("remove_all_by_weapon_type",
                            choice.getItemAt(choice.getSelectedIndex()),
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
            }
        });
    }

    public void setUser(User user) {
        this.client.setUser(user);
        this.currentUser.setText(user.getLogin());
    }

    public void localize() {
        backButton.setText(LocaleBundle.getCurrentBundle().getString("back_button"));
        label1.setText(LocaleBundle.getCurrentBundle().getString("removeByWeapon_label1"));
        removeButton.setText(LocaleBundle.getCurrentBundle().getString("remove_button"));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        removeByWeaponPanel = new JPanel();
        currentUser = new JLabel();
        name = new JLabel();
        backButton = new JButton();
        label1 = new JLabel();
        choice = new JComboBox<>();
        removeButton = new JButton();

        //======== removeByWeaponPanel ========
        {
            removeByWeaponPanel.setBackground(new Color(225, 183, 144));
            removeByWeaponPanel.setLayout(new MigLayout(
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
                "[25,grow,fill]" +
                "[35,grow,fill]" +
                "[25,grow,fill]" +
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
            removeByWeaponPanel.add(currentUser, "cell 1 0");

            //---- name ----
            name.setText("SpaceMarine");
            name.setHorizontalAlignment(SwingConstants.CENTER);
            name.setFont(new Font("Arial Black", Font.BOLD, 40));
            name.setBackground(new Color(255, 102, 102));
            name.setForeground(new Color(40, 61, 82));
            removeByWeaponPanel.add(name, "cell 2 0 4 1,align center center,grow 0 0");

            //---- backButton ----
            backButton.setText("\u041d\u0430\u0437\u0430\u0434");
            backButton.setBackground(Color.white);
            backButton.setForeground(new Color(40, 61, 82));
            backButton.setFont(new Font("Arial", Font.BOLD, 12));
            backButton.setBorder(new EtchedBorder());
            removeByWeaponPanel.add(backButton, "cell 6 0,align center center,grow 0 0,width 80:80:110,height 30:30:50");

            //---- label1 ----
            label1.setText("\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u043e\u0440\u0443\u0436\u0438\u0435");
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setFont(new Font("Arial", Font.BOLD, 16));
            label1.setForeground(new Color(40, 61, 82));
            removeByWeaponPanel.add(label1, "cell 3 2 2 1");

            //---- choice ----
            choice.setFont(new Font("Arial", Font.BOLD, 12));
            choice.setForeground(SystemColor.windowBorder);
            choice.setBackground(Color.white);
            choice.setBorder(new EtchedBorder());
            choice.setModel(new DefaultComboBoxModel<>(new String[] {
                "FLAMER",
                "BOLTGUN",
                "GRENADE"
            }));
            removeByWeaponPanel.add(choice, "cell 3 4 2 1,aligny center,grow 100 0,height 30:35:55");

            //---- removeButton ----
            removeButton.setText("\u0423\u0434\u0430\u043b\u0438\u0442\u044c");
            removeButton.setForeground(new Color(225, 183, 144));
            removeButton.setFont(new Font("Arial", Font.BOLD, 12));
            removeButton.setBackground(new Color(40, 61, 82));
            removeByWeaponPanel.add(removeButton, "cell 3 6 2 1,aligny center,grow 100 0,height 30:35:55");
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel removeByWeaponPanel;
    private JLabel currentUser;
    private JLabel name;
    private JButton backButton;
    private JLabel label1;
    private JComboBox<String> choice;
    private JButton removeButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private Client client;

    public JPanel getRemoveByWeaponPanel() {
        return removeByWeaponPanel;
    }
}
