/*
 * Created by JFormDesigner on Sat May 01 16:56:41 MSK 2021
 */

package clientModule.forms.StartMenuForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.border.*;

import clientModule.App;
import clientModule.Client;
import clientModule.forms.LoginForm.Login;
import clientModule.forms.RegisterForm.Register;
import net.miginfocom.swing.*;
import resources.LocaleBundle;

/**
 * @author unknown
 */
public class StartMenu extends JPanel {
    public StartMenu(Client client) {
        initComponents();
        this.client = client;
        chooseLang.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                LocaleBundle.setBundle(String.valueOf(e.getItem()));
                localize();
                App.filter.localize();
                App.insert.localize();
                App.login.localize();
                App.mainMenu.localize();
                App.register.localize();
                App.removeByWeapon.localize();
                App.removeGreater.localize();
                App.removeKey.localize();
                App.script.localize();
                App.show.localize();
                App.update.localize();
                App.visualize.localize();
            }
        });
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.mainFrame.setContentPane(App.login.getLoginPanel());
                App.mainFrame.validate();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.mainFrame.setContentPane(App.register.getRegisterPanel());
                App.mainFrame.validate();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        startMenuPanel = new JPanel();
        name = new JLabel();
        signInButton = new JButton();
        registerButton = new JButton();
        exitButton = new JButton();
        chooseLang = new JComboBox<>();

        //======== startMenuPanel ========
        {
            startMenuPanel.setBackground(new Color(40, 61, 82));
            startMenuPanel.setLayout(new MigLayout(
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
                "[214,grow,fill]" +
                "[]" +
                "[48]" +
                "[]" +
                "[]" +
                "[214,grow,fill]" +
                "[]" +
                "[]"));

            //---- name ----
            name.setText("SpaceMarine");
            name.setHorizontalAlignment(SwingConstants.CENTER);
            name.setFont(new Font("Arial Black", Font.BOLD, 40));
            name.setBackground(new Color(255, 102, 102));
            name.setForeground(new Color(225, 183, 144));
            startMenuPanel.add(name, "cell 4 0,align center center,grow 0 0");

            //---- signInButton ----
            signInButton.setText("\u0412\u043e\u0439\u0442\u0438");
            signInButton.setFont(new Font("Arial", Font.PLAIN, 12));
            signInButton.setBackground(new Color(225, 183, 144));
            signInButton.setForeground(new Color(40, 61, 82));
            signInButton.setBorder(new EtchedBorder());
            startMenuPanel.add(signInButton, "cell 4 1,align center center,grow 0 0,width 100:150:250,height 30:35:45");

            //---- registerButton ----
            registerButton.setText("\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044f");
            registerButton.setFont(new Font("Arial", Font.PLAIN, 12));
            registerButton.setBackground(new Color(225, 183, 144));
            registerButton.setForeground(new Color(40, 61, 82));
            registerButton.setBorder(new EtchedBorder());
            startMenuPanel.add(registerButton, "cell 4 2,align center center,grow 0 0,width 100:150:250,height 30:35:45");

            //---- exitButton ----
            exitButton.setText("\u0417\u0430\u043a\u0440\u044b\u0442\u044c");
            exitButton.setFont(new Font("Arial", Font.PLAIN, 12));
            exitButton.setBackground(new Color(225, 183, 144));
            exitButton.setForeground(new Color(40, 61, 82));
            exitButton.setBorder(new EtchedBorder());
            startMenuPanel.add(exitButton, "cell 4 3,align center center,grow 0 0,width 100:150:250,height 30:35:45");

            //---- chooseLang ----
            chooseLang.setModel(new DefaultComboBoxModel<>(new String[] {
                "Russian",
                "English(USA)",
                "Slovenian",
                "Ukranian"
            }));
            chooseLang.setBackground(new Color(225, 183, 144));
            chooseLang.setForeground(new Color(40, 61, 82));
            chooseLang.setFont(new Font("Arial", Font.PLAIN, 12));
            chooseLang.setBorder(new EtchedBorder());
            startMenuPanel.add(chooseLang, "cell 4 4,align center center,grow 0 0,width 100:150:250,height 30:35:45");
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public void localize() {
        signInButton.setText(LocaleBundle.getCurrentBundle().getString("login_button"));
        registerButton.setText(LocaleBundle.getCurrentBundle().getString("registration"));
        exitButton.setText(LocaleBundle.getCurrentBundle().getString("startMenu_closeButton"));
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel startMenuPanel;
    private JLabel name;
    private JButton signInButton;
    private JButton registerButton;
    private JButton exitButton;
    private JComboBox<String> chooseLang;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private Client client;


    public JPanel getStartMenuPanel() {
        return startMenuPanel;
    }
}
