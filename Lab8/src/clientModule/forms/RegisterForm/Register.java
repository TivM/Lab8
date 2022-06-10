/*
 * Created by JFormDesigner on Sat May 01 17:02:45 MSK 2021
 */

package clientModule.forms.RegisterForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;

import clientModule.App;
import clientModule.Client;
import clientModule.forms.MainMenuForm.MainMenu;
import clientModule.forms.StartMenuForm.StartMenu;
import common.utility.Request;
import common.utility.Response;
import common.utility.ResponseCode;
import common.utility.User;
import net.miginfocom.swing.*;
import resources.LocaleBundle;

/**
 * @author unknown
 */
public class Register extends JPanel {
    public Register(Client client) {
        initComponents();
        this.client = client;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.mainFrame.setContentPane(App.startMenu.getStartMenuPanel());
                App.mainFrame.validate();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (String.valueOf(passwordField.getPassword()).equals(String.valueOf(passwordField2.getPassword()))) {
                        Random rand = new Random();
                        int redValue = rand.nextInt(255);
                        int greenValue = rand.nextInt(255);
                        int blueValue = rand.nextInt(255);
                        Color clr = new Color(redValue, greenValue, blueValue);
                        String hex = "0x" + Integer.toHexString(clr.getRGB()).substring(2).toUpperCase(Locale.ROOT);
                        User user = new User(loginField.getText(), String.valueOf(passwordField.getPassword()), hex);
                        client.send(new Request("sign_up", "", user));
                        Response fromServer = client.receive();
                        if (fromServer.getResponseCode().equals(ResponseCode.OK)) {
                            App.userColor = hex;
                            client.setUser(user);
                            App.mainMenu.setUser(user);
                            App.insert.setUser(user);
                            App.removeByWeapon.setUser(user);
                            App.removeGreater.setUser(user);
                            App.removeKey.setUser(user);
                            App.show.setUser(user);
                            App.update.setUser(user);
                            App.script.setUser(user);
                            App.filter.setUser(user);
                            App.visualize.setUser(user);
                            App.mainFrame.setContentPane(App.mainMenu.getMainMenuPanel());
                            App.mainFrame.validate();
                        } else {
                            JOptionPane.showMessageDialog(null, fromServer.localize(false));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("registerOptionPaneError"));
                    }
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("ioPaneError"));
                } catch (ClassNotFoundException classNotFoundException) {
                    JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("classNotFoundError"));
                }
            }
        });
    }

    public void localize() {
        backButton.setText(LocaleBundle.getCurrentBundle().getString("back_button"));
        registerButton.setText(LocaleBundle.getCurrentBundle().getString("registration"));
        passwordName.setText(LocaleBundle.getCurrentBundle().getString("register_passwordName"));
        confirmPassword.setText(LocaleBundle.getCurrentBundle().getString("register_confirmPassword"));
        loginName.setText(LocaleBundle.getCurrentBundle().getString("register_loginName"));
        name.setText(LocaleBundle.getCurrentBundle().getString("registration"));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        registerPanel = new JPanel();
        name = new JLabel();
        loginName = new JLabel();
        loginField = new JTextField();
        passwordName = new JLabel();
        passwordField = new JPasswordField();
        confirmPassword = new JLabel();
        passwordField2 = new JPasswordField();
        registerButton = new JButton();
        backButton = new JButton();

        //======== registerPanel ========
        {
            registerPanel.setBackground(new Color(225, 183, 144));
            registerPanel.setLayout(new MigLayout(
                "insets 0,hidemode 3",
                // columns
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[grow,fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]",
                // rows
                "[80,grow,fill]" +
                "[25,fill]" +
                "[35,fill]" +
                "[25,fill]" +
                "[35,fill]" +
                "[25,fill]" +
                "[35,fill]" +
                "[40]" +
                "[40]" +
                "[50,grow,fill]"));

            //---- name ----
            name.setText("\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044f");
            name.setHorizontalAlignment(SwingConstants.CENTER);
            name.setFont(new Font("Arial Black", Font.BOLD, 40));
            name.setBackground(new Color(255, 102, 102));
            name.setForeground(new Color(40, 61, 82));
            registerPanel.add(name, "cell 4 0,align center center,grow 0 0");

            //---- loginName ----
            loginName.setText("\u041b\u043e\u0433\u0438\u043d");
            loginName.setHorizontalAlignment(SwingConstants.CENTER);
            loginName.setForeground(Color.black);
            loginName.setFont(new Font("Arial", Font.BOLD, 16));
            registerPanel.add(loginName, "cell 4 1");

            //---- loginField ----
            loginField.setBackground(Color.white);
            registerPanel.add(loginField, "cell 4 2,alignx center,growx 0,width 100:200:250");

            //---- passwordName ----
            passwordName.setText("\u041f\u0430\u0440\u043e\u043b\u044c");
            passwordName.setHorizontalAlignment(SwingConstants.CENTER);
            passwordName.setForeground(Color.black);
            passwordName.setFont(new Font("Arial", Font.BOLD, 16));
            registerPanel.add(passwordName, "cell 4 3");

            //---- passwordField ----
            passwordField.setBackground(Color.white);
            registerPanel.add(passwordField, "cell 4 4,alignx center,growx 0,width 100:200:250");

            //---- confirmPassword ----
            confirmPassword.setText("\u041f\u043e\u0434\u0442\u0432\u0435\u0440\u0436\u0434\u0435\u043d\u0438\u0435 \u043f\u0430\u0440\u043e\u043b\u044f");
            confirmPassword.setHorizontalAlignment(SwingConstants.CENTER);
            confirmPassword.setForeground(Color.black);
            confirmPassword.setFont(new Font("Arial", Font.BOLD, 16));
            registerPanel.add(confirmPassword, "cell 4 5");

            //---- passwordField2 ----
            passwordField2.setBackground(Color.white);
            registerPanel.add(passwordField2, "cell 4 6,alignx center,growx 0,width 100:200:250");

            //---- registerButton ----
            registerButton.setText("\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044f");
            registerButton.setFont(new Font("Arial", Font.PLAIN, 12));
            registerButton.setBorder(new EtchedBorder());
            registerButton.setBackground(new Color(40, 61, 82));
            registerButton.setForeground(Color.white);
            registerPanel.add(registerButton, "cell 4 7,align center center,grow 0 0,width 100:150:250,height 30:35:45");

            //---- backButton ----
            backButton.setText("\u041d\u0430\u0437\u0430\u0434");
            backButton.setFont(new Font("Arial", Font.PLAIN, 12));
            backButton.setBorder(new EtchedBorder());
            backButton.setBackground(new Color(40, 61, 82));
            backButton.setForeground(Color.white);
            registerPanel.add(backButton, "cell 4 8,align center center,grow 0 0,width 100:150:250,height 30:35:45");
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel registerPanel;
    private JLabel name;
    private JLabel loginName;
    private JTextField loginField;
    private JLabel passwordName;
    private JPasswordField passwordField;
    private JLabel confirmPassword;
    private JPasswordField passwordField2;
    private JButton registerButton;
    private JButton backButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private Client client;


    public JPanel getRegisterPanel() {
        return registerPanel;
    }
}
