/*
 * Created by JFormDesigner on Sun May 02 01:01:23 MSK 2021
 */

package clientModule.forms.InsertForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.*;

import clientModule.App;
import clientModule.Client;
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
public class Insert extends JPanel {
    public Insert(Client client) {
        initComponents();
        this.client = client;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.mainFrame.setContentPane(App.mainMenu.getMainMenuPanel());
                App.mainFrame.validate();
            }
        });
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder errors = new StringBuilder();
                int key = 0;
                try {
                    key = Integer.parseInt(keyField.getText());
                    if (key <= 0) throw new NotDeclaredValueException();
                } catch (NumberFormatException exception) {
                    errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError13"));
                } catch (NotDeclaredValueException notDeclaredValueException) {
                    errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError14"));
                }
                String name = null;
                try {
                    name = nameField.getText();
                    if (name.isEmpty()) throw new NotDeclaredValueException();
                } catch (NotDeclaredValueException notDeclaredValueException) {
                    errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError1"));
                }
                int health = 0;
                try {
                    health = Integer.parseInt(healthField.getText());
                    if (health <= 0) throw new NotDeclaredValueException();
                } catch (NumberFormatException exception) {
                    errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError2"));
                } catch (NotDeclaredValueException notDeclaredValueException) {
                    errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError3"));
                }
                int heartCount = 0;
                try {
                    heartCount = Integer.parseInt(heartCountField.getText());
                    if (heartCount<0||heartCount>3) throw new NotDeclaredValueException();
                    if (heartCount <= 0) throw new NotDeclaredValueException();
                } catch (NumberFormatException exception) {
                    errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError4"));
                } catch (NotDeclaredValueException notDeclaredValueException) {
                    errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError5"));
                }
                String achieve = null;
                try {
                    achieve = achieveField.getText();
                    if (achieve.isEmpty()) throw new NotDeclaredValueException();
                } catch (NotDeclaredValueException notDeclaredValueException) {
                    errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError6"));
                }
                double x = 0;
                try {
                    x = Double.parseDouble(coorX.getText());
                    if (x <= -666) throw new NotDeclaredValueException();
                } catch (NumberFormatException exception) {
                    errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError7"));
                } catch (NotDeclaredValueException notDeclaredValueException) {
                    errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError8"));
                }
                float y = 0;
                try {
                    y = Float.parseFloat(coorY.getText());
                    if (y <= -603) throw new NotDeclaredValueException();
                } catch (NumberFormatException exception) {
                    errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError9"));
                } catch (NotDeclaredValueException notDeclaredValueException) {
                    errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError10"));
                }
                String chapterN = null;
                try {
                    chapterN = chapterName.getText();
                    if (chapterN.isEmpty()) throw new NotDeclaredValueException();
                } catch (NotDeclaredValueException notDeclaredValueException) {
                    errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError11"));
                }
                String chapterL = null;
                try {
                    chapterL = chapterLegion.getText();
                    if (chapterL.isEmpty()) throw new NotDeclaredValueException();
                } catch (NotDeclaredValueException notDeclaredValueException) {
                    errors.append(LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError12"));
                }
                if (errors.toString().equals("")) {
                    try {
                        client.send(new Request("insert",
                                String.valueOf(key),
                                new SpaceMarineLite(
                                        name, new Coordinates(x, y), health, heartCount, achieve, Weapon.valueOf(weaponTypeField.getItemAt(weaponTypeField.getSelectedIndex())), new Chapter(chapterN, chapterL)
                                ),
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

    public void setUser(User user) {
        this.client.setUser(user);
        this.currentUser.setText(user.getLogin());
    }

    public void localize() {
        backButton.setText(LocaleBundle.getCurrentBundle().getString("back_button"));
        keyName.setText(LocaleBundle.getCurrentBundle().getString("insert_keyName"));
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
        insertButton.setText(LocaleBundle.getCurrentBundle().getString("insert_insertButton"));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        insertPanel = new JPanel();
        currentUser = new JLabel();
        name = new JLabel();
        backButton = new JButton();
        keyName = new JLabel();
        keyField = new JTextField();
        marineData = new JLabel();
        coordinatesData = new JLabel();
        nameTitle = new JLabel();
        nameField = new JTextField();
        xTitle = new JLabel();
        coorX = new JTextField();
        healthTitle = new JLabel();
        healthField = new JTextField();
        yTitle = new JLabel();
        coorY = new JTextField();
        heartTitle = new JLabel();
        heartCountField = new JTextField();
        chapterData = new JLabel();
        achieveTitle = new JLabel();
        achieveField = new JTextField();
        chapterNameTitle = new JLabel();
        chapterName = new JTextField();
        weaponTitle = new JLabel();
        weaponTypeField = new JComboBox<>();
        chapterLegionTitle = new JLabel();
        chapterLegion = new JTextField();
        insertButton = new JButton();

        //======== insertPanel ========
        {
            insertPanel.setBackground(new Color(225, 183, 144));
            insertPanel.setLayout(new MigLayout(
                "insets 0,hidemode 3,align center center",
                // columns
                "[fill]" +
                "[110,grow,fill]" +
                "[110,grow,fill]" +
                "[110,grow,fill]" +
                "[110,grow,fill]" +
                "[110,grow,fill]" +
                "[110,grow,fill]" +
                "[fill]",
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
            insertPanel.add(currentUser, "cell 1 0");

            //---- name ----
            name.setText("SpaceMarine");
            name.setHorizontalAlignment(SwingConstants.CENTER);
            name.setFont(new Font("Arial Black", Font.BOLD, 40));
            name.setBackground(new Color(255, 102, 102));
            name.setForeground(new Color(40, 61, 82));
            insertPanel.add(name, "cell 2 0 4 1,align center center,grow 0 0");

            //---- backButton ----
            backButton.setText("\u041d\u0430\u0437\u0430\u0434");
            backButton.setBackground(Color.white);
            backButton.setForeground(new Color(40, 61, 82));
            backButton.setFont(new Font("Arial", Font.BOLD, 12));
            backButton.setBorder(new EtchedBorder());
            insertPanel.add(backButton, "cell 6 0,align center center,grow 0 0,width 80:80:110,height 30:30:50");

            //---- keyName ----
            keyName.setText("\u041a\u043b\u044e\u0447");
            keyName.setHorizontalAlignment(SwingConstants.CENTER);
            keyName.setFont(new Font("Arial", Font.BOLD, 14));
            keyName.setForeground(new Color(40, 61, 82));
            insertPanel.add(keyName, "cell 1 1");

            //---- keyField ----
            keyField.setHorizontalAlignment(SwingConstants.CENTER);
            keyField.setBackground(Color.white);
            keyField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            keyField.setBorder(new EtchedBorder());
            keyField.setToolTipText("key");
            insertPanel.add(keyField, "cell 2 1,aligny center,grow 100 0,height 25:30:50");

            //---- marineData ----
            marineData.setText("\u0414\u0430\u043d\u043d\u044b\u0435 \u043e SpaceMarine");
            marineData.setHorizontalAlignment(SwingConstants.CENTER);
            marineData.setFont(new Font("Arial", Font.BOLD, 14));
            marineData.setForeground(new Color(40, 61, 82));
            insertPanel.add(marineData, "cell 4 1 4 1");

            //---- coordinatesData ----
            coordinatesData.setText("\u041a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b");
            coordinatesData.setHorizontalAlignment(SwingConstants.CENTER);
            coordinatesData.setFont(new Font("Arial", Font.BOLD, 14));
            coordinatesData.setForeground(new Color(40, 61, 82));
            insertPanel.add(coordinatesData, "cell 1 2 3 1");

            //---- nameTitle ----
            nameTitle.setText("\u0418\u043c\u044f");
            nameTitle.setForeground(new Color(40, 61, 82));
            nameTitle.setHorizontalAlignment(SwingConstants.CENTER);
            nameTitle.setFont(new Font("Arial", Font.BOLD, 14));
            insertPanel.add(nameTitle, "cell 4 2");

            //---- nameField ----
            nameField.setBackground(Color.white);
            nameField.setHorizontalAlignment(SwingConstants.CENTER);
            nameField.setBorder(new EtchedBorder());
            nameField.setToolTipText("name");
            insertPanel.add(nameField, "cell 5 2,aligny center,grow 100 0,height 25:30:50");

            //---- xTitle ----
            xTitle.setText("X");
            xTitle.setForeground(new Color(40, 61, 82));
            xTitle.setHorizontalAlignment(SwingConstants.CENTER);
            xTitle.setFont(new Font("Arial", Font.BOLD, 14));
            insertPanel.add(xTitle, "cell 1 3");

            //---- coorX ----
            coorX.setHorizontalAlignment(SwingConstants.CENTER);
            coorX.setBackground(Color.white);
            coorX.setBorder(new EtchedBorder());
            coorX.setToolTipText("x");
            insertPanel.add(coorX, "cell 2 3,aligny center,grow 100 0,height 25:30:50");

            //---- healthTitle ----
            healthTitle.setText("\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435");
            healthTitle.setForeground(new Color(40, 61, 82));
            healthTitle.setHorizontalAlignment(SwingConstants.CENTER);
            healthTitle.setFont(new Font("Arial", Font.BOLD, 14));
            insertPanel.add(healthTitle, "cell 4 3");

            //---- healthField ----
            healthField.setHorizontalAlignment(SwingConstants.CENTER);
            healthField.setBackground(Color.white);
            healthField.setBorder(new EtchedBorder());
            healthField.setToolTipText("health");
            insertPanel.add(healthField, "cell 5 3,aligny center,grow 100 0,height 25:30:50");

            //---- yTitle ----
            yTitle.setText("Y");
            yTitle.setForeground(new Color(40, 61, 82));
            yTitle.setHorizontalAlignment(SwingConstants.CENTER);
            yTitle.setFont(new Font("Arial", Font.BOLD, 14));
            insertPanel.add(yTitle, "cell 1 4");

            //---- coorY ----
            coorY.setHorizontalAlignment(SwingConstants.CENTER);
            coorY.setBackground(Color.white);
            coorY.setBorder(new EtchedBorder());
            coorY.setToolTipText("y");
            insertPanel.add(coorY, "cell 2 4,aligny center,grow 100 0,height 25:30:50");

            //---- heartTitle ----
            heartTitle.setText("\u0421\u0435\u0440\u0434\u0446\u0430");
            heartTitle.setForeground(new Color(40, 61, 82));
            heartTitle.setHorizontalAlignment(SwingConstants.CENTER);
            heartTitle.setFont(new Font("Arial", Font.BOLD, 14));
            insertPanel.add(heartTitle, "cell 4 4");

            //---- heartCountField ----
            heartCountField.setHorizontalAlignment(SwingConstants.CENTER);
            heartCountField.setBackground(Color.white);
            heartCountField.setBorder(new EtchedBorder());
            heartCountField.setToolTipText("heartCount");
            insertPanel.add(heartCountField, "cell 5 4,aligny center,grow 100 0,height 25:30:50");

            //---- chapterData ----
            chapterData.setText("\u0427\u0430\u0441\u0442\u044c");
            chapterData.setHorizontalAlignment(SwingConstants.CENTER);
            chapterData.setFont(new Font("Arial", Font.BOLD, 14));
            chapterData.setForeground(new Color(40, 61, 82));
            insertPanel.add(chapterData, "cell 1 5 3 1");

            //---- achieveTitle ----
            achieveTitle.setText("\u0414\u043e\u0441\u0442\u0438\u0436\u0435\u043d\u0438\u044f");
            achieveTitle.setForeground(new Color(40, 61, 82));
            achieveTitle.setHorizontalAlignment(SwingConstants.CENTER);
            achieveTitle.setFont(new Font("Arial", Font.BOLD, 14));
            insertPanel.add(achieveTitle, "cell 4 5");

            //---- achieveField ----
            achieveField.setHorizontalAlignment(SwingConstants.CENTER);
            achieveField.setBackground(Color.white);
            achieveField.setBorder(new EtchedBorder());
            achieveField.setToolTipText("achievements");
            insertPanel.add(achieveField, "cell 5 5,aligny center,grow 100 0,height 25:30:50");

            //---- chapterNameTitle ----
            chapterNameTitle.setText("\u0418\u043c\u044f");
            chapterNameTitle.setForeground(new Color(40, 61, 82));
            chapterNameTitle.setHorizontalAlignment(SwingConstants.CENTER);
            chapterNameTitle.setFont(new Font("Arial", Font.BOLD, 14));
            insertPanel.add(chapterNameTitle, "cell 1 6");

            //---- chapterName ----
            chapterName.setHorizontalAlignment(SwingConstants.CENTER);
            chapterName.setBackground(Color.white);
            chapterName.setBorder(new EtchedBorder());
            chapterName.setToolTipText("ChapterName");
            insertPanel.add(chapterName, "cell 2 6,aligny center,grow 100 0,height 25:30:50");

            //---- weaponTitle ----
            weaponTitle.setText("\u041e\u0440\u0443\u0436\u0438\u0435");
            weaponTitle.setForeground(new Color(40, 61, 82));
            weaponTitle.setHorizontalAlignment(SwingConstants.CENTER);
            weaponTitle.setFont(new Font("Arial", Font.BOLD, 14));
            insertPanel.add(weaponTitle, "cell 4 6");

            //---- weaponTypeField ----
            weaponTypeField.setBackground(Color.white);
            weaponTypeField.setModel(new DefaultComboBoxModel<>(new String[] {
                "FLAMER",
                "BOLTGUN",
                "GRENADE"
            }));
            weaponTypeField.setBorder(new EtchedBorder());
            weaponTypeField.setSelectedIndex(1);
            insertPanel.add(weaponTypeField, "cell 5 6,aligny center,grow 100 0,height 25:30:50");

            //---- chapterLegionTitle ----
            chapterLegionTitle.setText("\u041b\u0435\u0433\u0438\u043e\u043d");
            chapterLegionTitle.setForeground(new Color(40, 61, 82));
            chapterLegionTitle.setHorizontalAlignment(SwingConstants.CENTER);
            chapterLegionTitle.setFont(new Font("Arial", Font.BOLD, 14));
            insertPanel.add(chapterLegionTitle, "cell 1 7");

            //---- chapterLegion ----
            chapterLegion.setHorizontalAlignment(SwingConstants.CENTER);
            chapterLegion.setBackground(Color.white);
            chapterLegion.setBorder(new EtchedBorder());
            chapterLegion.setToolTipText("ChapterLegion");
            insertPanel.add(chapterLegion, "cell 2 7,aligny center,grow 100 0,height 25:30:50");

            //---- insertButton ----
            insertButton.setText("Insert");
            insertButton.setForeground(new Color(225, 183, 144));
            insertButton.setFont(new Font("Arial", Font.BOLD, 12));
            insertButton.setBackground(new Color(40, 61, 82));
            insertButton.setBorder(new EtchedBorder());
            insertPanel.add(insertButton, "cell 3 8 2 1,aligny center,grow 100 0,height 30:30:50");
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel insertPanel;
    private JLabel currentUser;
    private JLabel name;
    private JButton backButton;
    private JLabel keyName;
    private JTextField keyField;
    private JLabel marineData;
    private JLabel coordinatesData;
    private JLabel nameTitle;
    private JTextField nameField;
    private JLabel xTitle;
    private JTextField coorX;
    private JLabel healthTitle;
    private JTextField healthField;
    private JLabel yTitle;
    private JTextField coorY;
    private JLabel heartTitle;
    private JTextField heartCountField;
    private JLabel chapterData;
    private JLabel achieveTitle;
    private JTextField achieveField;
    private JLabel chapterNameTitle;
    private JTextField chapterName;
    private JLabel weaponTitle;
    private JComboBox<String> weaponTypeField;
    private JLabel chapterLegionTitle;
    private JTextField chapterLegion;
    private JButton insertButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private Client client;

    public JTextField getKeyField() {
        return keyField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getCoorX() {
        return coorX;
    }

    public JTextField getHealthField() {
        return healthField;
    }

    public JTextField getCoorY() {
        return coorY;
    }

    public JTextField getHeartCountField() {
        return heartCountField;
    }

    public JTextField getAchieveField() {
        return achieveField;
    }

    public JTextField getChapterName() {
        return chapterName;
    }

    public JTextField getChapterLegion() {
        return chapterLegion;
    }

    public JPanel getInsertPanel() {
        return insertPanel;
    }
}
