/*
 * Created by JFormDesigner on Sat May 01 16:59:15 MSK 2021
 */

package clientModule.forms.LoginForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
public class Login extends JPanel {
    public Login(Client client) {
        initComponents();
        this.client = client;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.mainFrame.setContentPane(App.startMenu.getStartMenuPanel());
                App.mainFrame.validate();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    User user = new User(loginField.getText(), String.valueOf(passwordField.getPassword()), null);
                    client.send(new Request("sign_in", "", user));
                    Response fromServer = client.receive();
                    if (fromServer.getResponseCode().equals(ResponseCode.OK)) {
                        client.send(new Request("get_user_color", "", user));
                        Response response = client.receive();
                        if (response.getResponseBody() != null) user.setColor(response.getResponseBody().replaceAll("[\\n]",""));
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
        loginButton.setText(LocaleBundle.getCurrentBundle().getString("login_button"));
        passwordName.setText(LocaleBundle.getCurrentBundle().getString("login_passwordName"));
        loginName.setText(LocaleBundle.getCurrentBundle().getString("login_loginName"));
        name.setText(LocaleBundle.getCurrentBundle().getString("login_name"));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        loginPanel = new JPanel();
        name = new JLabel();
        loginName = new JLabel();
        loginField = new JTextField();
        passwordName = new JLabel();
        passwordField = new JPasswordField();
        loginButton = new JButton();
        backButton = new JButton();

        //======== loginPanel ========
        {
            loginPanel.setBackground(new Color(225, 183, 144));
            loginPanel.setLayout(new MigLayout(
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
                            "[40]" +
                            "[40]" +
                            "[110,grow,fill]"));

            //---- name ----
            name.setText("\u0412\u0445\u043e\u0434");
            name.setHorizontalAlignment(SwingConstants.CENTER);
            name.setFont(new Font("Arial Black", Font.BOLD, 40));
            name.setBackground(new Color(255, 102, 102));
            name.setForeground(new Color(40, 61, 82));
            loginPanel.add(name, "cell 4 0,align center center,grow 0 0");

            //---- loginName ----
            loginName.setText("\u041b\u043e\u0433\u0438\u043d");
            loginName.setHorizontalAlignment(SwingConstants.CENTER);
            loginName.setForeground(Color.black);
            loginName.setFont(new Font("Arial", Font.BOLD, 16));
            loginPanel.add(loginName, "cell 4 1");

            //---- loginField ----
            loginField.setBackground(Color.white);
            loginPanel.add(loginField, "cell 4 2,alignx center,growx 0,width 100:200:250");

            //---- passwordName ----
            passwordName.setText("\u041f\u0430\u0440\u043e\u043b\u044c");
            passwordName.setHorizontalAlignment(SwingConstants.CENTER);
            passwordName.setForeground(Color.black);
            passwordName.setFont(new Font("Arial", Font.BOLD, 16));
            loginPanel.add(passwordName, "cell 4 3");

            //---- passwordField ----
            passwordField.setBackground(Color.white);
            loginPanel.add(passwordField, "cell 4 4,alignx center,growx 0,width 100:200:250");

            //---- loginButton ----
            loginButton.setText("\u0412\u043e\u0439\u0442\u0438");
            loginButton.setFont(new Font("Arial", Font.PLAIN, 12));
            loginButton.setBorder(new EtchedBorder());
            loginButton.setBackground(new Color(40, 61, 82));
            loginButton.setForeground(Color.white);
            loginPanel.add(loginButton, "cell 4 5,align center center,grow 0 0,width 100:150:250,height 30:35:45");

            //---- backButton ----
            backButton.setText("\u041d\u0430\u0437\u0430\u0434");
            backButton.setFont(new Font("Arial", Font.PLAIN, 12));
            backButton.setBorder(new EtchedBorder());
            backButton.setBackground(new Color(40, 61, 82));
            backButton.setForeground(Color.white);
            loginPanel.add(backButton, "cell 4 6,align center center,grow 0 0,width 100:150:250,height 30:35:45");
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel loginPanel;
    private JLabel name;
    private JLabel loginName;
    private JTextField loginField;
    private JLabel passwordName;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private Client client;


    public JPanel getLoginPanel() {
        return loginPanel;
    }
}
