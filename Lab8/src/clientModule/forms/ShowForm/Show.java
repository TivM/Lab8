/*
 * Created by JFormDesigner on Sun May 02 21:35:52 MSK 2021
 */

package clientModule.forms.ShowForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

import clientModule.App;
import clientModule.Client;
import common.data.Chapter;
import common.data.Coordinates;
import common.data.SpaceMarine;
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
public class Show extends JPanel {
    public Show(Client client) {
        initComponents();
        this.client = client;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isFilter = false;
                App.mainFrame.setContentPane(App.mainMenu.getMainMenuPanel());
                App.mainFrame.validate();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawTable();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isFilter = false;
                drawTable();
            }
        });
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.mainFrame.setContentPane(App.filter.getFilterPanel());
                App.mainFrame.validate();
            }
        });
        table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) updateCell(e);
            }
        });
    }

    private void updateCell(TableModelEvent e) {
        int key = (int) table.getModel().getValueAt(e.getFirstRow(), 1);
        SpaceMarineLite updateMarine = new SpaceMarineLite(null, null, -1, -1, null, null, null);
        if (e.getColumn() == 2) {
            String name = String.valueOf(table.getModel().getValueAt(e.getFirstRow(), e.getColumn()));
            if (name.isEmpty()) {
                isNotValued = false;
                JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError1"));
                table.getModel().setValueAt(collection.get(key).getName(), e.getFirstRow(), e.getColumn());
                return;
            }
            updateMarine.setName(name);
        }
        Coordinates coordinates = null;
        if (e.getColumn() == 3) {
            try {
                double x = Double.parseDouble(String.valueOf(table.getModel().getValueAt(e.getFirstRow(), e.getColumn())));
                if (x <= -666) throw new NotDeclaredValueException();
                coordinates = new Coordinates(x, Float.parseFloat(String.valueOf(table.getModel().getValueAt(e.getFirstRow(), e.getColumn() + 1))));
            } catch (NumberFormatException exception) {
                isNotValued = false;
                JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError7"));
                table.getModel().setValueAt(collection.get(key).getCoordinates().getX(), e.getFirstRow(), e.getColumn());
                return;
            } catch (NotDeclaredValueException exception) {
                isNotValued = false;
                JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError8"));
                table.getModel().setValueAt(collection.get(key).getCoordinates().getX(), e.getFirstRow(), e.getColumn());
                return;
            }
            updateMarine.setCoordinates(coordinates);
        }
        if (e.getColumn() == 4) {
            try {
                float y = Float.parseFloat(String.valueOf(table.getModel().getValueAt(e.getFirstRow(), e.getColumn())));
                if (y <= -604) throw new NotDeclaredValueException();
                coordinates = new Coordinates(Double.parseDouble(String.valueOf(table.getModel().getValueAt(e.getFirstRow(), e.getColumn() - 1))), y);
            } catch (NumberFormatException exception) {
                isNotValued = false;
                JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError9"));
                table.getModel().setValueAt(collection.get(key).getCoordinates().getY(), e.getFirstRow(), e.getColumn());
                return;
            } catch (NotDeclaredValueException exception) {
                isNotValued = false;
                JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError10"));
                table.getModel().setValueAt(collection.get(key).getCoordinates().getY(), e.getFirstRow(), e.getColumn());
                return;
            }
            updateMarine.setCoordinates(coordinates);
        }
        if (e.getColumn() == 6) {
            try {
                int health = Integer.parseInt(String.valueOf(table.getModel().getValueAt(e.getFirstRow(), e.getColumn())));
                if (health <= 0) throw new NotDeclaredValueException();
                updateMarine.setHealth(health);
            } catch (NumberFormatException exception) {
                isNotValued = false;
                JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError2"));
                table.getModel().setValueAt(collection.get(key).getHealth(), e.getFirstRow(), e.getColumn());
                return;
            } catch (NotDeclaredValueException exception) {
                isNotValued = false;
                JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError3"));
                table.getModel().setValueAt(collection.get(key).getHealth(), e.getFirstRow(), e.getColumn());
                return;
            }
        }
        if (e.getColumn() == 7) {
            try {
                int heartCount = Integer.parseInt(String.valueOf(table.getModel().getValueAt(e.getFirstRow(), e.getColumn())));
                if (heartCount <= 0 || heartCount > 3) throw new NotDeclaredValueException();
                updateMarine.setHealth(heartCount);
            } catch (NumberFormatException exception) {
                isNotValued = false;
                JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError4"));
                table.getModel().setValueAt(collection.get(key).getHeartCount(), e.getFirstRow(), e.getColumn());
                return;
            } catch (NotDeclaredValueException exception) {
                isNotValued = false;
                JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError5"));
                table.getModel().setValueAt(collection.get(key).getHeartCount(), e.getFirstRow(), e.getColumn());
                return;
            }
        }
        if (e.getColumn() == 8) {
            String achieve = String.valueOf(table.getModel().getValueAt(e.getFirstRow(), e.getColumn()));
            if (achieve.isEmpty()) {
                isNotValued = false;
                JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError6"));
                table.getModel().setValueAt(collection.get(key).getAchievements(), e.getFirstRow(), e.getColumn());
                return;
            }
            updateMarine.setAchievements(achieve);
        }
        if (e.getColumn() == 9) {
            String weapon = String.valueOf(table.getModel().getValueAt(e.getFirstRow(), e.getColumn()));
            if (weapon.isEmpty()) {
                isNotValued = false;
                JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError15"));
                table.getModel().setValueAt(collection.get(key).getWeaponType().toString(), e.getFirstRow(), e.getColumn());
                return;
            }
            if (!weapon.equals("BOLTGUN") && !weapon.equals("GRENADE") && !weapon.equals("FLAMER")) {
                isNotValued = false;
                JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError16"));
                table.getModel().setValueAt(collection.get(key).getWeaponType().toString(), e.getFirstRow(), e.getColumn());
                return;
            }
            updateMarine.setWeaponType(Weapon.valueOf(weapon));
        }
        Chapter chapter = null;
        if (e.getColumn() == 10) {
            String name = String.valueOf(table.getModel().getValueAt(e.getFirstRow(), e.getColumn()));
            if (name.isEmpty()) {
                isNotValued = false;
                JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError11"));
                table.getModel().setValueAt(collection.get(key).getChapter().getName(), e.getFirstRow(), e.getColumn());
                return;
            }
            chapter = new Chapter(name, String.valueOf(table.getModel().getValueAt(e.getFirstRow(), e.getColumn() + 1)));
            updateMarine.setChapter(chapter);
        }
        if (e.getColumn() == 11) {
            String legion = String.valueOf(table.getModel().getValueAt(e.getFirstRow(), e.getColumn()));
            if (legion.isEmpty()) {
                isNotValued = false;
                JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("valuesOptionPaneError12"));
                table.getModel().setValueAt(collection.get(key).getChapter().getParentLegion(), e.getFirstRow(), e.getColumn());
                return;
            }
            chapter = new Chapter(String.valueOf(table.getModel().getValueAt(e.getFirstRow(), e.getColumn() - 1)), legion);
            updateMarine.setChapter(chapter);
        }
        try {
            client.send(new Request("update",
                    String.valueOf(table.getModel().getValueAt(e.getFirstRow(), 0)),
                    updateMarine,
                    this.client.getUser()));
            Response fromServer = client.receive();
            if (isNotValued) JOptionPane.showMessageDialog(null, fromServer.localize(false));
            else isNotValued = true;
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("ioPaneError"));
        } catch (ClassNotFoundException classNotFoundException) {
            JOptionPane.showMessageDialog(null, LocaleBundle.getCurrentBundle().getString("classNotFoundError"));
        }
    }

    public void localize() {
        backButton.setText(LocaleBundle.getCurrentBundle().getString("back_button"));
        filterButton.setText(LocaleBundle.getCurrentBundle().getString("show_filterButton"));
        resetButton.setText(LocaleBundle.getCurrentBundle().getString("show_resetButton"));
        updateButton.setText(LocaleBundle.getCurrentBundle().getString("show_updateButton"));
        TableColumnModel model = table.getTableHeader().getColumnModel();
        model.getColumn(1).setHeaderValue(LocaleBundle.getCurrentBundle().getString("show_keyColumn"));
        model.getColumn(2).setHeaderValue(LocaleBundle.getCurrentBundle().getString("show_nameColumn"));
        model.getColumn(5).setHeaderValue(LocaleBundle.getCurrentBundle().getString("show_dateColumn"));
        model.getColumn(6).setHeaderValue(LocaleBundle.getCurrentBundle().getString("show_healthColumn"));
        model.getColumn(7).setHeaderValue(LocaleBundle.getCurrentBundle().getString("show_heartColumn"));
        model.getColumn(8).setHeaderValue(LocaleBundle.getCurrentBundle().getString("show_achieveColumn"));
        model.getColumn(9).setHeaderValue(LocaleBundle.getCurrentBundle().getString("show_weaponColumn"));
        model.getColumn(10).setHeaderValue(LocaleBundle.getCurrentBundle().getString("show_chapterNameColumn"));
        model.getColumn(11).setHeaderValue(LocaleBundle.getCurrentBundle().getString("show_chapterLegionColumn"));
        model.getColumn(12).setHeaderValue(LocaleBundle.getCurrentBundle().getString("show_userColumn"));
        table.getTableHeader().repaint();
    }

    public void drawTable() {
        try {
            this.client.send(new Request("show", "", this.client.getUser()));
            Runnable receive = new Runnable() {
                @Override
                public void run() {
                    Response fromServer = null;
                    try {
                        fromServer = client.receive();
                        collection = fromServer.getCollection();
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        RowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
                        table.setRowSorter(sorter);
                        while (model.getRowCount() > 0) model.removeRow(0);
                        for (Map.Entry<Integer, SpaceMarine> e : collection.entrySet()) {
                            if (isFilter && !checkValueForFilter(e.getValue(), e.getKey())) {
                                continue;
                            }
                            LocalDateTime ldt = e.getValue().getCreationDate();
                            Date outDate = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
                            model.addRow(new Object[]{
                                    e.getValue().getId(),
                                    e.getKey(),
                                    e.getValue().getName(),
                                    e.getValue().getCoordinates().getX(),
                                    e.getValue().getCoordinates().getY(),
                                    outDate,
                                    e.getValue().getHealth(),
                                    e.getValue().getHeartCount(),
                                    e.getValue().getAchievements(),
                                    e.getValue().getWeaponType().toString(),
                                    e.getValue().getChapter().getName(),
                                    e.getValue().getChapter().getParentLegion(),
                                    e.getValue().getOwner().getLogin()
                            });
                        }
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
            exception.printStackTrace();
        }
    }

    public void setUser(User user) {
        this.client.setUser(user);
        this.currentUser.setText(user.getLogin());
    }

    private boolean checkValueForFilter(SpaceMarine marine, int key) {
        String chooseField = App.filter.getChooseField().getItemAt(App.filter.getChooseField().getSelectedIndex());
        String argumentField = App.filter.getArgumentField().getText();
        String chooseTypeFilter = App.filter.getChooseTypeFilter().getItemAt(App.filter.getChooseTypeFilter().getSelectedIndex());
        if (chooseField.equals("id")) {
            try {
                int id = Integer.parseInt(argumentField);
                if (chooseTypeFilter.equals("=") && id == marine.getId()) {
                    return true;
                }
                if (chooseTypeFilter.equals(">") && marine.getId() > id) return true;
                if (chooseTypeFilter.equals("<") && marine.getId() < id) return true;
                return false;
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Поле id должно быть числом!");
                return false;
            }
        }
        if (chooseField.equals("key")) {
            try {
                int getKey = Integer.parseInt(argumentField);
                if (chooseTypeFilter.equals("=") && getKey == key) {
                    return true;
                }
                if (chooseTypeFilter.equals(">") && key > getKey) return true;
                if (chooseTypeFilter.equals("<") && key < getKey) return true;
                return false;
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Поле key должно быть числом!");
                return false;
            }
        }
        if (chooseField.equals("name")) {
            if (chooseTypeFilter.equals("=") && argumentField.equals(marine.getName())) {
                return true;
            }
            if (chooseTypeFilter.equals(">") && argumentField.compareTo(marine.getName()) < 0) return true;
            if (chooseTypeFilter.equals("<") && argumentField.compareTo(marine.getName()) > 0) return true;
            return false;
        }
        if (chooseField.equals("x")) {
            try {
                double x = Double.parseDouble(argumentField);
                if (chooseTypeFilter.equals("=") && marine.getCoordinates().getX() == x) {
                    return true;
                }
                if (chooseTypeFilter.equals(">") && marine.getCoordinates().getX() > x) return true;
                if (chooseTypeFilter.equals("<") && marine.getCoordinates().getX() < x) return true;
                return false;
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Поле x должно быть числом!");
                return false;
            }
        }
        if (chooseField.equals("y")) {
            try {
                float y = Float.parseFloat(argumentField);
                if (chooseTypeFilter.equals("=") && marine.getCoordinates().getY() == y) {
                    return true;
                }
                if (chooseTypeFilter.equals(">") && marine.getCoordinates().getY() > y) return true;
                if (chooseTypeFilter.equals("<") && marine.getCoordinates().getY() < y) return true;
                return false;
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Поле y должно быть числом!");
                return false;
            }
        }
        if (chooseField.equals("health")) {
            try {
                int health = Integer.parseInt(argumentField);
                if (chooseTypeFilter.equals("=") && marine.getHealth() == health) {
                    return true;
                }
                if (chooseTypeFilter.equals(">") && marine.getHealth() > health) return true;
                if (chooseTypeFilter.equals("<") && marine.getHealth() < health) return true;
                return false;
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Поле health должно быть числом!");
                return false;
            }
        }
        if (chooseField.equals("heart")) {
            try {
                int heart = Integer.parseInt(argumentField);
                if (chooseTypeFilter.equals("=") && marine.getHealth() == heart) {
                    return true;
                }
                if (chooseTypeFilter.equals(">") && marine.getHeartCount() > heart) return true;
                if (chooseTypeFilter.equals("<") && marine.getHeartCount() < heart) return true;
                return false;
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Поле heart должно быть числом!");
                return false;
            }
        }
        if (chooseField.equals("achieve")) {
            if (chooseTypeFilter.equals("=") && argumentField.equals(marine.getAchievements())) {
                return true;
            }
            if (chooseTypeFilter.equals(">") && argumentField.compareTo(marine.getAchievements()) < 0) return true;
            if (chooseTypeFilter.equals("<") && argumentField.compareTo(marine.getAchievements()) > 0) return true;
            return false;
        }
        if (chooseField.equals("weapon")) {
            if (chooseTypeFilter.equals("=") && argumentField.equals(marine.getWeaponType().toString())) {
                return true;
            }
            if (chooseTypeFilter.equals(">") && argumentField.compareTo(marine.getWeaponType().toString()) < 0) return true;
            if (chooseTypeFilter.equals("<") && argumentField.compareTo(marine.getWeaponType().toString()) > 0) return true;
            return false;
        }
        if (chooseField.equals("chapterName")) {
            if (chooseTypeFilter.equals("=") && argumentField.equals(marine.getChapter().getName())) {
                return true;
            }
            if (chooseTypeFilter.equals(">") && argumentField.compareTo(marine.getChapter().getName()) < 0) return true;
            if (chooseTypeFilter.equals("<") && argumentField.compareTo(marine.getChapter().getName()) > 0) return true;
            return false;
        }
        if (chooseField.equals("chapterLegion")) {
            if (chooseTypeFilter.equals("=") && argumentField.equals(marine.getChapter().getParentLegion())) {
                return true;
            }
            if (chooseTypeFilter.equals(">") && argumentField.compareTo(marine.getChapter().getParentLegion()) < 0) return true;
            if (chooseTypeFilter.equals("<") && argumentField.compareTo(marine.getChapter().getParentLegion()) > 0) return true;
            return false;
        }
        if (chooseField.equals("user")) {
            if (chooseTypeFilter.equals("=") && argumentField.equals(marine.getOwner().getLogin())) {
                return true;
            }
            if (chooseTypeFilter.equals(">") && argumentField.compareTo(marine.getOwner().getLogin()) < 0) return true;
            if (chooseTypeFilter.equals("<") && argumentField.compareTo(marine.getOwner().getLogin()) > 0) return true;
            return false;
        }
        return true;
    }

    public void setFilter(boolean filter) {
        isFilter = filter;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        showPanel = new JPanel();
        currentUser = new JLabel();
        name = new JLabel();
        backButton = new JButton();
        filterButton = new JButton();
        resetButton = new JButton();
        updateButton = new JButton();
        scrollPane = new JScrollPane();
        table = new JTable();

        //======== showPanel ========
        {
            showPanel.setBackground(new Color(225, 183, 144));
            showPanel.setLayout(new MigLayout(
                "insets 0,hidemode 3",
                // columns
                "[70,grow,fill]" +
                "[70,grow,fill]" +
                "[grow,fill]" +
                "[grow,fill]" +
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
                "[50,grow,fill]" +
                "[grow]"));

            //---- currentUser ----
            currentUser.setText("test");
            currentUser.setHorizontalAlignment(SwingConstants.CENTER);
            currentUser.setFont(new Font("Arial", Font.BOLD, 14));
            currentUser.setForeground(new Color(40, 61, 82));
            showPanel.add(currentUser, "cell 0 0");

            //---- name ----
            name.setText("SpaceMarine");
            name.setHorizontalAlignment(SwingConstants.CENTER);
            name.setFont(new Font("Arial Black", Font.BOLD, 36));
            name.setBackground(new Color(255, 102, 102));
            name.setForeground(new Color(40, 61, 82));
            showPanel.add(name, "cell 1 0 5 1,align center center,grow 0 0");

            //---- backButton ----
            backButton.setText("\u041d\u0430\u0437\u0430\u0434");
            backButton.setBackground(new Color(40, 61, 82));
            backButton.setFont(new Font("Arial", Font.BOLD, 12));
            backButton.setForeground(Color.white);
            backButton.setBorder(new EtchedBorder());
            showPanel.add(backButton, "cell 6 0,align center center,grow 0 0,width 70:70:110,height 30:30:50");

            //---- filterButton ----
            filterButton.setText("\u0424\u0438\u043b\u044c\u0442\u0440");
            filterButton.setForeground(Color.white);
            filterButton.setBackground(new Color(40, 61, 82));
            filterButton.setFont(new Font("Arial", Font.BOLD, 12));
            filterButton.setBorder(new EtchedBorder());
            showPanel.add(filterButton, "cell 2 1,aligny center,grow 100 0");

            //---- resetButton ----
            resetButton.setText("\u0421\u0431\u0440\u043e\u0441");
            resetButton.setForeground(Color.white);
            resetButton.setBackground(new Color(40, 61, 82));
            resetButton.setFont(new Font("Arial", Font.BOLD, 12));
            resetButton.setBorder(new EtchedBorder());
            showPanel.add(resetButton, "cell 3 1,aligny center,grow 100 0");

            //---- updateButton ----
            updateButton.setText("\u041e\u0431\u043d\u043e\u0432\u0438\u0442\u044c");
            updateButton.setForeground(Color.white);
            updateButton.setBackground(new Color(40, 61, 82));
            updateButton.setFont(new Font("Arial", Font.BOLD, 12));
            updateButton.setBorder(new EtchedBorder());
            showPanel.add(updateButton, "cell 4 1,aligny center,grow 100 0");

            //======== scrollPane ========
            {

                //---- table ----
                table.setModel(new DefaultTableModel(
                    new Object[][] {
                    },
                    new String[] {
                        "id", "key", "name", "x", "y", "date", "health", "heart", "achieve", "weapon", "chapterName", "chapterLegion", "user"
                    }
                ) {
                    Class<?>[] columnTypes = new Class<?>[] {
                        Integer.class, Integer.class, String.class, Double.class, Float.class, Date.class, Integer.class, Integer.class, String.class, String.class, String.class, String.class, String.class
                    };
                    boolean[] columnEditable = new boolean[] {
                        false, false, true, true, true, false, true, true, true, true, true, true, false
                    };
                    @Override
                    public Class<?> getColumnClass(int columnIndex) {
                        return columnTypes[columnIndex];
                    }
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnEditable[columnIndex];
                    }
                });
                table.setFont(new Font("Arial", Font.PLAIN, 12));
                scrollPane.setViewportView(table);
            }
            showPanel.add(scrollPane, "cell 0 2 7 8");
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel showPanel;
    private JLabel currentUser;
    private JLabel name;
    private JButton backButton;
    private JButton filterButton;
    private JButton resetButton;
    private JButton updateButton;
    private JScrollPane scrollPane;
    private JTable table;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private Client client;
    private boolean isFilter;
    private TreeMap<Integer, SpaceMarine> collection;
    private boolean isNotValued;

    public JPanel getShowPanel() {
        return showPanel;
    }
}
