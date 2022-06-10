/*
 * Created by JFormDesigner on Sun May 02 21:36:33 MSK 2021
 */

package clientModule.forms.UpdateForm;

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
public class Update extends JPanel {
    public Update(Client client) {
        initComponents();
        this.client = client;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.mainFrame.setContentPane(App.mainMenu.getMainMenuPanel());
                App.mainFrame.validate();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder errors = new StringBuilder();
                int id = -1;
                try {
                    id = Integer.parseInt(idField.getText());
                    if (id <= 0) throw new NotDeclaredValueException();
                } catch (NumberFormatException exception) {
                    errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError13"));
                } catch (NotDeclaredValueException notDeclaredValueException) {
                    errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError14"));
                }
                String name = null;
                if (nameCheck.isSelected()) {
                    try {
                        name = nameField.getText();
                        if (name.isEmpty()) throw new NotDeclaredValueException();
                    } catch (NotDeclaredValueException notDeclaredValueException) {
                        errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError1"));
                    }
                }
                int health = -1;
                if (healthCheck.isSelected()) {
                    try {
                        health = Integer.parseInt(healthField.getText());
                        if (health <= 0) throw new NotDeclaredValueException();
                    } catch (NumberFormatException exception) {
                        errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError2"));
                    } catch (NotDeclaredValueException notDeclaredValueException) {
                        errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError3"));
                    }
                }
                int heartCount = -1;
                if (heartCheck.isSelected()) {
                    try {
                        heartCount = Integer.parseInt(heartField.getText());
                        if (heartCount<0||heartCount>3) throw new NotDeclaredValueException();
                    } catch (NumberFormatException exception) {
                        errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError4"));
                    } catch (NotDeclaredValueException notDeclaredValueException) {
                        errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError5"));
                    }
                }
                String achieve = null;
                if (achieveCheck.isSelected()) {
                    try {
                        achieve = achieveField.getText();
                        if (achieve.isEmpty()) throw new NotDeclaredValueException();
                    } catch (NotDeclaredValueException notDeclaredValueException) {
                        errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError6"));
                    }
                }
                Coordinates newCoor = null;
                if (coorCheck.isSelected()) {
                    double x = -667;
                    try {
                        double x1 = Double.parseDouble(xField.getText());
                        if (x1 <= -666) throw new NotDeclaredValueException();
                        x = x1;
                    } catch (NumberFormatException exception) {
                        errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError7"));
                    } catch (NotDeclaredValueException notDeclaredValueException) {
                        errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError8"));
                    }
                    float y = -604;
                    try {
                        float y1 = Float.parseFloat(yField.getText());
                        if (y1 <= -603) throw new NotDeclaredValueException();
                        y = y1;
                    } catch (NumberFormatException exception) {
                        errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError9"));
                    } catch (NotDeclaredValueException notDeclaredValueException) {
                        errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError10"));
                    }
                    if (x != -667 && y != -604) newCoor = new Coordinates(x, y);
                }
                Chapter newChapter = null;
                if (chapterCheck.isSelected()) {
                    String chapterN = null;
                    try {
                        chapterN = chapterNameField.getText();
                        if (chapterN.isEmpty()) throw new NotDeclaredValueException();
                    } catch (NotDeclaredValueException notDeclaredValueException) {
                        errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError11"));
                    }
                    String chapterL = null;
                    try {
                        chapterL = chapterLegionField.getText();
                        if (chapterL.isEmpty()) throw new NotDeclaredValueException();
                    } catch (NotDeclaredValueException notDeclaredValueException) {
                        errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError12"));
                    }
                    if (!chapterN.isEmpty() && !chapterL.isEmpty()) newChapter = new Chapter(chapterN, chapterL);
                }
                Weapon newWeapon = null;
                if (weaponCheck.isSelected()) {
                    newWeapon = Weapon.valueOf(weaponBox.getItemAt(weaponBox.getSelectedIndex()));
                }
                if (errors.toString().equals("")) {
                    try {
                        client.send(new Request("update",
                                String.valueOf(id),
                                new SpaceMarineLite(
                                        name, newCoor, health, heartCount, achieve, newWeapon, newChapter),
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
                App.mainFrame.setContentPane(App.mainMenu.getMainMenuPanel());
                App.mainFrame.validate();
            }
        });
    }

    public void localize() {
        backButton.setText(LocaleBundle.getCurrentBundle().getString("back_button"));
        marineData.setText(LocaleBundle.getCurrentBundle().getString("marineData_Title"));
        coordinatesData.setText(LocaleBundle.getCurrentBundle().getString("coordinatesData_Title"));
        chapterData.setText(LocaleBundle.getCurrentBundle().getString("chapterData_Title"));
        nameTitle.setText(LocaleBundle.getCurrentBundle().getString("nameTitle"));
        healthTitle.setText(LocaleBundle.getCurrentBundle().getString("healthTitle"));
        heartTitle.setText(LocaleBundle.getCurrentBundle().getString("heartTitle"));
        achieveTitle.setText(LocaleBundle.getCurrentBundle().getString("achieveTitle"));
        weaponTitle.setText(LocaleBundle.getCurrentBundle().getString("weaponTitle"));
        chapterNameTitle.setText(LocaleBundle.getCurrentBundle().getString("nameTitle"));
        chapterLegionTitle.setText(LocaleBundle.getCurrentBundle().getString("chapterLegionTitle"));
        updateButton.setText(LocaleBundle.getCurrentBundle().getString("show_updateButton"));
    }

    public void setUser(User user) {
        this.client.setUser(user);
        this.currentUser.setText(user.getLogin());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        updatePanel = new JPanel();
        currentUser = new JLabel();
        name = new JLabel();
        backButton = new JButton();
        idTitle = new JLabel();
        idField = new JTextField();
        marineData = new JLabel();
        coordinatesData = new JLabel();
        nameTitle = new JLabel();
        nameField = new JTextField();
        nameCheck = new JCheckBox();
        xTitle = new JLabel();
        xField = new JTextField();
        coorCheck = new JCheckBox();
        healthTitle = new JLabel();
        healthField = new JTextField();
        healthCheck = new JCheckBox();
        yTitle = new JLabel();
        yField = new JTextField();
        heartTitle = new JLabel();
        heartField = new JTextField();
        heartCheck = new JCheckBox();
        chapterData = new JLabel();
        achieveTitle = new JLabel();
        achieveField = new JTextField();
        achieveCheck = new JCheckBox();
        chapterNameTitle = new JLabel();
        chapterNameField = new JTextField();
        chapterCheck = new JCheckBox();
        weaponTitle = new JLabel();
        weaponBox = new JComboBox<>();
        weaponCheck = new JCheckBox();
        chapterLegionTitle = new JLabel();
        chapterLegionField = new JTextField();
        updateButton = new JButton();

        //======== updatePanel ========
        {
            updatePanel.setBackground(new Color(225, 183, 144));
            updatePanel.setLayout(new MigLayout(
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
                "[35,grow,fill]" +
                "[35,grow,fill]" +
                "[35,grow,fill]" +
                "[35,grow,fill]" +
                "[35,grow,fill]" +
                "[35,grow,fill]" +
                "[35,grow,fill]" +
                "[35,grow,fill]" +
                "[35,grow,fill]"));

            //---- currentUser ----
            currentUser.setText("test");
            currentUser.setForeground(Color.white);
            currentUser.setHorizontalAlignment(SwingConstants.CENTER);
            currentUser.setFont(new Font("Arial", Font.BOLD, 20));
            updatePanel.add(currentUser, "cell 1 0");

            //---- name ----
            name.setText("SpaceMarine");
            name.setHorizontalAlignment(SwingConstants.CENTER);
            name.setFont(new Font("Arial Black", Font.BOLD, 40));
            name.setBackground(new Color(255, 102, 102));
            name.setForeground(new Color(40, 61, 82));
            updatePanel.add(name, "cell 2 0 4 1,align center center,grow 0 0");

            //---- backButton ----
            backButton.setText("\u041d\u0430\u0437\u0430\u0434");
            backButton.setBackground(Color.white);
            backButton.setForeground(new Color(40, 61, 82));
            backButton.setFont(new Font("Arial", Font.BOLD, 12));
            backButton.setBorder(new EtchedBorder());
            updatePanel.add(backButton, "cell 6 0,align center center,grow 0 0,width 80:80:110,height 30:30:50");

            //---- idTitle ----
            idTitle.setText("id");
            idTitle.setForeground(new Color(40, 61, 82));
            idTitle.setHorizontalAlignment(SwingConstants.CENTER);
            idTitle.setFont(new Font("Arial", Font.BOLD, 14));
            updatePanel.add(idTitle, "cell 1 1");

            //---- idField ----
            idField.setBackground(Color.white);
            idField.setHorizontalAlignment(SwingConstants.CENTER);
            updatePanel.add(idField, "cell 2 1,aligny center,grow 100 0,height 30:30:60");

            //---- marineData ----
            marineData.setText("\u0414\u0430\u043d\u043d\u044b\u0435 \u043e Spacemarine");
            marineData.setForeground(new Color(40, 61, 82));
            marineData.setHorizontalAlignment(SwingConstants.CENTER);
            marineData.setFont(new Font("Arial", Font.BOLD, 14));
            updatePanel.add(marineData, "cell 4 1 3 1");

            //---- coordinatesData ----
            coordinatesData.setText("\u041a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b");
            coordinatesData.setForeground(new Color(40, 61, 82));
            coordinatesData.setHorizontalAlignment(SwingConstants.CENTER);
            coordinatesData.setFont(new Font("Arial", Font.BOLD, 14));
            updatePanel.add(coordinatesData, "cell 1 2 3 1");

            //---- nameTitle ----
            nameTitle.setText("\u0418\u043c\u044f");
            nameTitle.setForeground(new Color(40, 61, 82));
            nameTitle.setHorizontalAlignment(SwingConstants.CENTER);
            nameTitle.setFont(new Font("Arial", Font.BOLD, 14));
            updatePanel.add(nameTitle, "cell 4 2");

            //---- nameField ----
            nameField.setBackground(Color.white);
            nameField.setHorizontalAlignment(SwingConstants.CENTER);
            updatePanel.add(nameField, "cell 5 2,aligny center,grow 100 0,height 30:30:60");

            //---- nameCheck ----
            nameCheck.setBackground(new Color(225, 183, 144));
            updatePanel.add(nameCheck, "cell 6 2");

            //---- xTitle ----
            xTitle.setText("X");
            xTitle.setForeground(new Color(40, 61, 82));
            xTitle.setHorizontalAlignment(SwingConstants.CENTER);
            xTitle.setFont(new Font("Arial", Font.BOLD, 14));
            updatePanel.add(xTitle, "cell 1 3");

            //---- xField ----
            xField.setBackground(Color.white);
            xField.setHorizontalAlignment(SwingConstants.CENTER);
            updatePanel.add(xField, "cell 2 3,aligny center,grow 100 0,height 30:30:60");

            //---- coorCheck ----
            coorCheck.setBackground(new Color(225, 183, 144));
            updatePanel.add(coorCheck, "cell 3 3 1 2");

            //---- healthTitle ----
            healthTitle.setText("\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435");
            healthTitle.setForeground(new Color(40, 61, 82));
            healthTitle.setHorizontalAlignment(SwingConstants.CENTER);
            healthTitle.setFont(new Font("Arial", Font.BOLD, 14));
            updatePanel.add(healthTitle, "cell 4 3");

            //---- healthField ----
            healthField.setBackground(Color.white);
            healthField.setHorizontalAlignment(SwingConstants.CENTER);
            updatePanel.add(healthField, "cell 5 3,aligny center,grow 100 0,height 30:30:60");

            //---- healthCheck ----
            healthCheck.setBackground(new Color(225, 183, 144));
            updatePanel.add(healthCheck, "cell 6 3");

            //---- yTitle ----
            yTitle.setText("Y");
            yTitle.setForeground(new Color(40, 61, 82));
            yTitle.setHorizontalAlignment(SwingConstants.CENTER);
            yTitle.setFont(new Font("Arial", Font.BOLD, 14));
            updatePanel.add(yTitle, "cell 1 4");

            //---- yField ----
            yField.setBackground(Color.white);
            yField.setHorizontalAlignment(SwingConstants.CENTER);
            updatePanel.add(yField, "cell 2 4,aligny center,grow 100 0,height 30:30:60");

            //---- heartTitle ----
            heartTitle.setText("\u0421\u0435\u0440\u0434\u0446\u0430");
            heartTitle.setForeground(new Color(40, 61, 82));
            heartTitle.setHorizontalAlignment(SwingConstants.CENTER);
            heartTitle.setFont(new Font("Arial", Font.BOLD, 14));
            updatePanel.add(heartTitle, "cell 4 4");

            //---- heartField ----
            heartField.setBackground(Color.white);
            heartField.setHorizontalAlignment(SwingConstants.CENTER);
            updatePanel.add(heartField, "cell 5 4,aligny center,grow 100 0,height 30:30:60");

            //---- heartCheck ----
            heartCheck.setBackground(new Color(225, 183, 144));
            updatePanel.add(heartCheck, "cell 6 4");

            //---- chapterData ----
            chapterData.setText("\u0427\u0430\u0441\u0442\u044c");
            chapterData.setForeground(new Color(40, 61, 82));
            chapterData.setHorizontalAlignment(SwingConstants.CENTER);
            chapterData.setFont(new Font("Arial", Font.BOLD, 14));
            updatePanel.add(chapterData, "cell 1 5 3 1");

            //---- achieveTitle ----
            achieveTitle.setText("\u0414\u043e\u0441\u0442\u0438\u0436\u0435\u043d\u0438\u044f");
            achieveTitle.setForeground(new Color(40, 61, 82));
            achieveTitle.setHorizontalAlignment(SwingConstants.CENTER);
            achieveTitle.setFont(new Font("Arial", Font.BOLD, 14));
            updatePanel.add(achieveTitle, "cell 4 5");

            //---- achieveField ----
            achieveField.setBackground(Color.white);
            achieveField.setHorizontalAlignment(SwingConstants.CENTER);
            updatePanel.add(achieveField, "cell 5 5,aligny center,grow 100 0,height 30:30:60");

            //---- achieveCheck ----
            achieveCheck.setBackground(new Color(225, 183, 144));
            updatePanel.add(achieveCheck, "cell 6 5");

            //---- chapterNameTitle ----
            chapterNameTitle.setText("\u0418\u043c\u044f");
            chapterNameTitle.setForeground(new Color(40, 61, 82));
            chapterNameTitle.setHorizontalAlignment(SwingConstants.CENTER);
            chapterNameTitle.setFont(new Font("Arial", Font.BOLD, 14));
            updatePanel.add(chapterNameTitle, "cell 1 6");

            //---- chapterNameField ----
            chapterNameField.setBackground(Color.white);
            chapterNameField.setHorizontalAlignment(SwingConstants.CENTER);
            updatePanel.add(chapterNameField, "cell 2 6,aligny center,grow 100 0,height 30:30:60");

            //---- chapterCheck ----
            chapterCheck.setBackground(new Color(225, 183, 144));
            updatePanel.add(chapterCheck, "cell 3 6 1 2");

            //---- weaponTitle ----
            weaponTitle.setText("\u041e\u0440\u0443\u0436\u0438\u0435");
            weaponTitle.setForeground(new Color(40, 61, 82));
            weaponTitle.setHorizontalAlignment(SwingConstants.CENTER);
            weaponTitle.setFont(new Font("Arial", Font.BOLD, 14));
            updatePanel.add(weaponTitle, "cell 4 6");

            //---- weaponBox ----
            weaponBox.setBackground(Color.white);
            weaponBox.setModel(new DefaultComboBoxModel<>(new String[] {
                "FLAMER",
                "BOLTGUN",
                "GRENADE"
            }));
            weaponBox.setSelectedIndex(0);
            updatePanel.add(weaponBox, "cell 5 6,aligny center,grow 100 0,height 30:30:60");

            //---- weaponCheck ----
            weaponCheck.setBackground(new Color(225, 183, 144));
            updatePanel.add(weaponCheck, "cell 6 6");

            //---- chapterLegionTitle ----
            chapterLegionTitle.setText("\u041b\u0435\u0433\u0438\u043e\u043d");
            chapterLegionTitle.setForeground(new Color(40, 61, 82));
            chapterLegionTitle.setHorizontalAlignment(SwingConstants.CENTER);
            chapterLegionTitle.setFont(new Font("Arial", Font.BOLD, 14));
            updatePanel.add(chapterLegionTitle, "cell 1 7");

            //---- chapterLegionField ----
            chapterLegionField.setBackground(Color.white);
            chapterLegionField.setHorizontalAlignment(SwingConstants.CENTER);
            updatePanel.add(chapterLegionField, "cell 2 7,aligny center,grow 100 0,height 30:30:60");

            //---- updateButton ----
            updateButton.setText("Update");
            updateButton.setBackground(new Color(40, 61, 82));
            updateButton.setFont(new Font("Arial", Font.BOLD, 14));
            updateButton.setForeground(Color.white);
            updatePanel.add(updateButton, "cell 3 8 2 1,aligny center,grow 100 0,height 30:30:60");
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel updatePanel;
    private JLabel currentUser;
    private JLabel name;
    private JButton backButton;
    private JLabel idTitle;
    private JTextField idField;
    private JLabel marineData;
    private JLabel coordinatesData;
    private JLabel nameTitle;
    private JTextField nameField;
    private JCheckBox nameCheck;
    private JLabel xTitle;
    private JTextField xField;
    private JCheckBox coorCheck;
    private JLabel healthTitle;
    private JTextField healthField;
    private JCheckBox healthCheck;
    private JLabel yTitle;
    private JTextField yField;
    private JLabel heartTitle;
    private JTextField heartField;
    private JCheckBox heartCheck;
    private JLabel chapterData;
    private JLabel achieveTitle;
    private JTextField achieveField;
    private JCheckBox achieveCheck;
    private JLabel chapterNameTitle;
    private JTextField chapterNameField;
    private JCheckBox chapterCheck;
    private JLabel weaponTitle;
    private JComboBox<String> weaponBox;
    private JCheckBox weaponCheck;
    private JLabel chapterLegionTitle;
    private JTextField chapterLegionField;
    private JButton updateButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private Client client;

    public JTextField getIdField() {
        return idField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getxField() {
        return xField;
    }

    public JTextField getHealthField() {
        return healthField;
    }

    public JTextField getyField() {
        return yField;
    }

    public JTextField getHeartField() {
        return heartField;
    }

    public JTextField getAchieveField() {
        return achieveField;
    }

    public JTextField getChapterNameField() {
        return chapterNameField;
    }

    public JTextField getChapterLegionField() {
        return chapterLegionField;
    }

    public JPanel getUpdatePanel() {
        return updatePanel;
    }
}
