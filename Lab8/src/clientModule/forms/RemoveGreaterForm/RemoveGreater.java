/*
 * Created by JFormDesigner on Mon May 03 22:08:03 MSK 2021
 */

package clientModule.forms.RemoveGreaterForm;

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
public class RemoveGreater extends JPanel {
    public RemoveGreater(Client client) {
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
                        client.send(new Request("remove_greater",
                                "",
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
        removeButton.setText(LocaleBundle.getCurrentBundle().getString("remove_button"));
    }

    public void setUser(User user) {
        this.client.setUser(user);
        this.currentUser.setText(user.getLogin());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        removeGreaterPanel = new JPanel();
        currentUser = new JLabel();
        name = new JLabel();
        backButton = new JButton();
        marineData = new JLabel();
        nameTitle = new JLabel();
        healthTitle = new JLabel();
        heartTitle = new JLabel();
        achieveTitle = new JLabel();
        weaponTitle = new JLabel();
        nameField = new JTextField();
        healthField = new JTextField();
        heartCountField = new JTextField();
        achieveField = new JTextField();
        weaponTypeField = new JComboBox<>();
        coordinatesData = new JLabel();
        xTitle = new JLabel();
        coorX = new JTextField();
        coorY = new JTextField();
        yTitle = new JLabel();
        chapterData = new JLabel();
        chapterNameTitle = new JLabel();
        chapterName = new JTextField();
        chapterLegion = new JTextField();
        chapterLegionTitle = new JLabel();
        removeButton = new JButton();

        //======== removeGreaterPanel ========
        {
            removeGreaterPanel.setBackground(new Color(225, 183, 144));
            removeGreaterPanel.setLayout(new MigLayout(
                "insets 0,hidemode 3,align center center",
                // columns
                "[grow,fill]" +
                "[110,grow,fill]" +
                "[110,grow,fill]" +
                "[110,grow,fill]" +
                "[110,grow,fill]" +
                "[110,grow,fill]" +
                "[grow,fill]",
                // rows
                "[80,grow,fill]" +
                "[35,grow,fill]" +
                "[25,grow,fill]" +
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
            removeGreaterPanel.add(currentUser, "cell 1 0");

            //---- name ----
            name.setText("SpaceMarine");
            name.setHorizontalAlignment(SwingConstants.CENTER);
            name.setFont(new Font("Arial Black", Font.BOLD, 40));
            name.setBackground(new Color(255, 102, 102));
            name.setForeground(new Color(40, 61, 82));
            removeGreaterPanel.add(name, "cell 2 0 3 1,align center center,grow 0 0");

            //---- backButton ----
            backButton.setText("\u041d\u0430\u0437\u0430\u0434");
            backButton.setBackground(Color.white);
            backButton.setForeground(new Color(40, 61, 82));
            backButton.setFont(new Font("Arial", Font.BOLD, 12));
            backButton.setBorder(new EtchedBorder());
            removeGreaterPanel.add(backButton, "cell 5 0,align center center,grow 0 0,width 80:80:110,height 30:30:50");

            //---- marineData ----
            marineData.setText("\u0414\u0430\u043d\u043d\u044b\u0435 \u043e SpaceMarine");
            marineData.setHorizontalAlignment(SwingConstants.CENTER);
            marineData.setFont(new Font("Arial", Font.BOLD, 14));
            marineData.setForeground(new Color(40, 61, 82));
            removeGreaterPanel.add(marineData, "cell 2 1 3 1");

            //---- nameTitle ----
            nameTitle.setText("\u0418\u043c\u044f");
            nameTitle.setForeground(Color.white);
            nameTitle.setHorizontalAlignment(SwingConstants.CENTER);
            nameTitle.setFont(new Font("Arial", Font.BOLD, 12));
            nameTitle.setVerticalAlignment(SwingConstants.BOTTOM);
            removeGreaterPanel.add(nameTitle, "cell 1 2");

            //---- healthTitle ----
            healthTitle.setText("\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435");
            healthTitle.setForeground(Color.white);
            healthTitle.setHorizontalAlignment(SwingConstants.CENTER);
            healthTitle.setFont(new Font("Arial", Font.BOLD, 12));
            healthTitle.setVerticalAlignment(SwingConstants.BOTTOM);
            removeGreaterPanel.add(healthTitle, "cell 2 2");

            //---- heartTitle ----
            heartTitle.setText("\u0421\u0435\u0440\u0434\u0446\u0430");
            heartTitle.setForeground(Color.white);
            heartTitle.setHorizontalAlignment(SwingConstants.CENTER);
            heartTitle.setFont(new Font("Arial", Font.BOLD, 12));
            heartTitle.setVerticalAlignment(SwingConstants.BOTTOM);
            removeGreaterPanel.add(heartTitle, "cell 3 2");

            //---- achieveTitle ----
            achieveTitle.setText("\u0414\u043e\u0441\u0442\u0438\u0436\u0435\u043d\u0438\u044f");
            achieveTitle.setForeground(Color.white);
            achieveTitle.setHorizontalAlignment(SwingConstants.CENTER);
            achieveTitle.setFont(new Font("Arial", Font.BOLD, 12));
            achieveTitle.setVerticalAlignment(SwingConstants.BOTTOM);
            removeGreaterPanel.add(achieveTitle, "cell 4 2");

            //---- weaponTitle ----
            weaponTitle.setText("\u041e\u0440\u0443\u0436\u0438\u0435");
            weaponTitle.setForeground(Color.white);
            weaponTitle.setHorizontalAlignment(SwingConstants.CENTER);
            weaponTitle.setFont(new Font("Arial", Font.BOLD, 12));
            weaponTitle.setVerticalAlignment(SwingConstants.BOTTOM);
            removeGreaterPanel.add(weaponTitle, "cell 5 2");

            //---- nameField ----
            nameField.setBackground(Color.white);
            nameField.setHorizontalAlignment(SwingConstants.CENTER);
            nameField.setBorder(new EtchedBorder());
            nameField.setToolTipText("name");
            removeGreaterPanel.add(nameField, "cell 1 3,aligny center,grow 100 0,height 25:30:50");

            //---- healthField ----
            healthField.setHorizontalAlignment(SwingConstants.CENTER);
            healthField.setBackground(Color.white);
            healthField.setBorder(new EtchedBorder());
            healthField.setToolTipText("health");
            removeGreaterPanel.add(healthField, "cell 2 3,aligny center,grow 100 0,height 25:30:50");

            //---- heartCountField ----
            heartCountField.setHorizontalAlignment(SwingConstants.CENTER);
            heartCountField.setBackground(Color.white);
            heartCountField.setBorder(new EtchedBorder());
            heartCountField.setToolTipText("heartCount");
            removeGreaterPanel.add(heartCountField, "cell 3 3,aligny center,grow 100 0,height 25:30:50");

            //---- achieveField ----
            achieveField.setHorizontalAlignment(SwingConstants.CENTER);
            achieveField.setBackground(Color.white);
            achieveField.setBorder(new EtchedBorder());
            achieveField.setToolTipText("achievements");
            removeGreaterPanel.add(achieveField, "cell 4 3,aligny center,grow 100 0,height 25:30:50");

            //---- weaponTypeField ----
            weaponTypeField.setBackground(Color.white);
            weaponTypeField.setModel(new DefaultComboBoxModel<>(new String[] {
                "FLAMER",
                "BOLTGUN",
                "GRENADE"
            }));
            weaponTypeField.setBorder(new EtchedBorder());
            weaponTypeField.setSelectedIndex(1);
            removeGreaterPanel.add(weaponTypeField, "cell 5 3,aligny center,grow 100 0,height 25:30:50");

            //---- coordinatesData ----
            coordinatesData.setText("\u041a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b");
            coordinatesData.setHorizontalAlignment(SwingConstants.CENTER);
            coordinatesData.setFont(new Font("Arial", Font.BOLD, 14));
            coordinatesData.setForeground(new Color(40, 61, 82));
            removeGreaterPanel.add(coordinatesData, "cell 2 4 3 1");

            //---- xTitle ----
            xTitle.setText("X");
            xTitle.setForeground(Color.white);
            xTitle.setHorizontalAlignment(SwingConstants.RIGHT);
            xTitle.setFont(new Font("Arial", Font.BOLD, 12));
            removeGreaterPanel.add(xTitle, "cell 1 5");

            //---- coorX ----
            coorX.setHorizontalAlignment(SwingConstants.CENTER);
            coorX.setBackground(Color.white);
            coorX.setBorder(new EtchedBorder());
            coorX.setToolTipText("x");
            removeGreaterPanel.add(coorX, "cell 2 5,aligny center,grow 100 0,height 25:30:50");

            //---- coorY ----
            coorY.setHorizontalAlignment(SwingConstants.CENTER);
            coorY.setBackground(Color.white);
            coorY.setBorder(new EtchedBorder());
            coorY.setToolTipText("y");
            removeGreaterPanel.add(coorY, "cell 4 5,aligny center,grow 100 0,height 25:30:50");

            //---- yTitle ----
            yTitle.setText("Y");
            yTitle.setForeground(Color.white);
            yTitle.setHorizontalAlignment(SwingConstants.LEFT);
            yTitle.setFont(new Font("Arial", Font.BOLD, 12));
            removeGreaterPanel.add(yTitle, "cell 5 5");

            //---- chapterData ----
            chapterData.setText("\u0427\u0430\u0441\u0442\u044c");
            chapterData.setHorizontalAlignment(SwingConstants.CENTER);
            chapterData.setFont(new Font("Arial", Font.BOLD, 14));
            chapterData.setForeground(new Color(40, 61, 82));
            removeGreaterPanel.add(chapterData, "cell 2 6 3 1");

            //---- chapterNameTitle ----
            chapterNameTitle.setText("\u0418\u043c\u044f");
            chapterNameTitle.setForeground(Color.white);
            chapterNameTitle.setHorizontalAlignment(SwingConstants.RIGHT);
            chapterNameTitle.setFont(new Font("Arial", Font.BOLD, 12));
            removeGreaterPanel.add(chapterNameTitle, "cell 1 7");

            //---- chapterName ----
            chapterName.setHorizontalAlignment(SwingConstants.CENTER);
            chapterName.setBackground(Color.white);
            chapterName.setBorder(new EtchedBorder());
            chapterName.setToolTipText("ChapterName");
            removeGreaterPanel.add(chapterName, "cell 2 7,aligny center,grow 100 0,height 25:30:50");

            //---- chapterLegion ----
            chapterLegion.setHorizontalAlignment(SwingConstants.CENTER);
            chapterLegion.setBackground(Color.white);
            chapterLegion.setBorder(new EtchedBorder());
            chapterLegion.setToolTipText("ChapterLegion");
            removeGreaterPanel.add(chapterLegion, "cell 4 7,aligny center,grow 100 0,height 25:30:50");

            //---- chapterLegionTitle ----
            chapterLegionTitle.setText("\u041b\u0435\u0433\u0438\u043e\u043d");
            chapterLegionTitle.setForeground(Color.white);
            chapterLegionTitle.setHorizontalAlignment(SwingConstants.LEFT);
            chapterLegionTitle.setFont(new Font("Arial", Font.BOLD, 12));
            removeGreaterPanel.add(chapterLegionTitle, "cell 5 7");

            //---- removeButton ----
            removeButton.setText("Remove");
            removeButton.setForeground(new Color(225, 183, 144));
            removeButton.setFont(new Font("Arial", Font.BOLD, 12));
            removeButton.setBackground(new Color(40, 61, 82));
            removeButton.setBorder(new EtchedBorder());
            removeGreaterPanel.add(removeButton, "cell 3 9,aligny center,grow 100 0,height 30:30:50");
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel removeGreaterPanel;
    private JLabel currentUser;
    private JLabel name;
    private JButton backButton;
    private JLabel marineData;
    private JLabel nameTitle;
    private JLabel healthTitle;
    private JLabel heartTitle;
    private JLabel achieveTitle;
    private JLabel weaponTitle;
    private JTextField nameField;
    private JTextField healthField;
    private JTextField heartCountField;
    private JTextField achieveField;
    private JComboBox<String> weaponTypeField;
    private JLabel coordinatesData;
    private JLabel xTitle;
    private JTextField coorX;
    private JTextField coorY;
    private JLabel yTitle;
    private JLabel chapterData;
    private JLabel chapterNameTitle;
    private JTextField chapterName;
    private JTextField chapterLegion;
    private JLabel chapterLegionTitle;
    private JButton removeButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private Client client;


    public JPanel getRemoveGreaterPanel() {
        return removeGreaterPanel;
    }
}
