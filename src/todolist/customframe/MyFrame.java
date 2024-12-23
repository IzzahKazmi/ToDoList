/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.customframe;

import todolist.about.AboutClass;
import todolist.icon.MyIconPack;
import todolist.listdata.Data;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * *
 *
 * This class handles every set up and functionality.
 *
 * @author Moshiur
 */
public final class MyFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JPanel buttonpanel;
    
    public static final  String HOME_DIR = System.getProperty("user.home");

    private JLabel timeLabel;

    private final JButton addbutton;
    private final JButton deletebutton;
    private final JButton deleteallbutton;
    private final JButton savebutton;
    private final JButton exitbutton;
    private final JButton aboutbutton;

    private String dateout;
    private JTextArea textField;
    JTextField inputtexfield;

    private JTable mytable;
    DefaultTableModel mymodel;
    Object[][] ob;
    String[] colnames;
    Vector<Data> myvec = new Vector<>();
    private int prev = -1;

    /**
     * *
     * every set up is made in this constructor.
     */
    public MyFrame() {

        //panel 1 details
        super("MAZI ToDoList");
        Image icon = new ImageIcon(getClass().getResource("appicon.png")).getImage();
        setIconImage(icon);
        // window title
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //   UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        addWindowListener(new WindowAdapter() {             // minimize to task bar
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                int indx = mytable.getSelectedRow();
                if (indx != -1) {
                    Data temp = myvec.elementAt(indx);
                    temp.setDetails(textField.getText());
                }
                saveList();
                setExtendedState(JFrame.ICONIFIED);
            }
        });

        // my frame lay out
        setLayout(new BorderLayout());
        //setting bottom panel

        timeLabel = new JLabel("MAZI");
        JLabel info = new JLabel("MAZI");
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(timeLabel, BorderLayout.WEST);
        panel2.add(info, BorderLayout.EAST);

        textField = new JTextArea("Add the details of your task here...");
        //textField.setCaretColor(Color.WHITE);
        textField.setRows(4);
        //textField.setBackground(new Color(235, 235, 235));
        textField.setBackground(new Color(120, 80, 120));
        textField.setForeground(new Color(235, 235, 235));
        textField.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JScrollPane myscroll2 = new JScrollPane(textField);
        JLabel detailslabel = new JLabel("Task Details:  ");
        //detailslabel.setBackground(new Color(120, 80, 120));
        detailslabel.setForeground(new Color(120, 80, 120));
        detailslabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        //  myscroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        myscroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //initiating table panel
        //creating table
        colnames = new String[]{"STATUS", "TASKS", "ADDED ON"};
        mytable = new JTable() {
            private static final long serialVersionUID = 1L;

            @Override
            public Class getColumnClass(int column) {

                // determines the types of the columns.
                if (column == 0) {
                    return Boolean.class;
                } else {
                    return String.class;
                }

            }
        };

        mymodel = new DefaultTableModel(colnames, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int col) {
                // setting col 1 & col 2 editable, could be also set be by returning false  
                return col != 2;
            }
        };

mytable.setModel(mymodel);
mytable.setBackground(new Color(250, 240, 245)); // Pastel lavender-pink for the background
mytable.setForeground(new Color(0, 0, 0)); // Soft lavender for the text
mytable.setFont(new Font("SansSerif", Font.PLAIN, 18)); // Use default font
mytable.setFillsViewportHeight(true);
mytable.setRowHeight(40);
mytable.setIntercellSpacing(new Dimension(2, 2));
mytable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 20)); // Use default font for the header
//mytable.getTableHeader().setFont(mytable.getTableHeader().getFont()); // Use default font for the header
mytable.setGridColor(new Color(200, 180, 200)); // Soft lavender-gray for grid
mytable.setShowGrid(true);
mytable.setSelectionBackground(new Color(230, 200, 220)); // Pastel pink for selection background
((DefaultTableCellRenderer) mytable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
mytable.getColumnModel().getColumn(0).setMinWidth(150);
mytable.getColumnModel().getColumn(0).setMaxWidth(150);
mytable.getColumnModel().getColumn(1).setMinWidth(320);
mytable.getColumnModel().getColumn(2).setMinWidth(320);
mytable.getColumnModel().getColumn(0).setResizable(false);
mytable.getTableHeader().setReorderingAllowed(false);

        mytable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if ((boolean) mymodel.getValueAt(row, 0)) {
                    c.setBackground(new Color(120, 80, 120));
                    //c.setBackground(new Color(75, 84, 89));
                } else if (isSelected) {
                    c.setBackground(mytable.getSelectionBackground());
                } else {
                    c.setBackground(mytable.getBackground());
                }
                return c;
            }
        });
        mytable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {

                try {
                    if (prev != -1) {
                        Data temp = myvec.elementAt(prev);
                        temp.setDetails(textField.getText());
                    }
                    Data temp = myvec.get(mytable.getSelectedRow());
                    textField.setText(temp.getDetails());
                    prev = mytable.getSelectedRow();

                } catch (Exception e) {

                }

            }
        }
        );
        inputtexfield = new JTextField("Add your task here...");
        inputtexfield.setFont(new Font("SansSerif", Font.PLAIN, 20));
        //inputtexfield.setForeground(new Color(200, 160, 230)); // Light purple for text
        inputtexfield.setForeground(new Color(235, 235, 235)); // Light purple for text
        //inputtexfield.setBackground(new Color(75, 0, 130));    // Dark purple for background
        inputtexfield.setBackground(new Color(120, 80, 120));    // Dark purple for background
        //inputtexfield.setCaretColor(new Color(148, 0, 211));
        inputtexfield.setFocusable(false);
        inputtexfield.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {

            }

            @Override
            public void mousePressed(MouseEvent me) {
                inputtexfield.setFocusable(true);
            }

            @Override
            public void mouseReleased(MouseEvent me) {

            }

            @Override
            public void mouseEntered(MouseEvent me) {
                inputtexfield.setFocusable(true);
            }

            @Override
            public void mouseExited(MouseEvent me) {

            }
        });
        inputtexfield.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent fe) {
                inputtexfield.setText("Add new Task..");
            }

            @Override
            public void focusGained(FocusEvent fe) {

                if (inputtexfield.getText().equals("Add your task here...")) {
                    inputtexfield.setText("");
                }
            }
        });
        inputtexfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String s1 = ae.getActionCommand();
                s1 = s1.trim();
                inputtexfield.setText("Add your task here...");
                if (!s1.isEmpty()) {
                    String s3 = "Add your task here...";
                    Data temp = new Data(s1, new Date(), s3);
                    myvec.add(temp);
                    mymodel.addRow(temp.getData());
                }
            }
        });
        
        JScrollPane myscroll = new JScrollPane(mytable);
        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new BorderLayout());
        mainpanel.add(myscroll, BorderLayout.CENTER);
        mainpanel.add(inputtexfield, BorderLayout.NORTH);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(detailslabel, BorderLayout.NORTH);
        panel1.add(myscroll2, BorderLayout.CENTER);
        panel1.add(panel2, BorderLayout.SOUTH);
        mainpanel.add(panel1, BorderLayout.SOUTH);
        add(mainpanel);
        
        
        // calls the method to load the tasks into table while starting the application
        loadList();

        //creating button panel
        //
        buttonpanel = new JPanel();
        buttonpanel.setLayout(new GridLayout(5, 1, 0, 0));
        //creating buttons
        MyIconPack myicon = new MyIconPack();
addbutton = new JButton("", myicon.addicon);
addbutton.setToolTipText("Add New Task");
addbutton.setBackground(new Color(250, 240, 200)); // Light pastel lavender

deletebutton = new JButton("", myicon.deleteicon);
deletebutton.setToolTipText("Delete Selected Task");
deletebutton.setBackground(new Color(240, 200, 210));

deleteallbutton = new JButton("", myicon.deleteallicon);
deleteallbutton.setToolTipText("Delete ALL Tasks");
deleteallbutton.setBackground(new Color(250, 240, 200)); // Light pastel yellow

savebutton = new JButton("", myicon.saveicon);
savebutton.setToolTipText("Save Current List");
savebutton.setBackground(new Color(240, 200, 210));

exitbutton = new JButton("", myicon.exiticon);
exitbutton.setToolTipText("Click to Exit");
exitbutton.setBackground(new Color(240, 200, 210));

aboutbutton = new JButton("", myicon.abouticon);
aboutbutton.setToolTipText("About Us");
aboutbutton.setBackground(new Color(240, 200, 210));


// registering buttons to button panel
//  buttonpanel.add(addbutton);
        buttonpanel.add(deletebutton);
        buttonpanel.add(savebutton);
        buttonpanel.add(deleteallbutton);
        buttonpanel.add(aboutbutton);
        buttonpanel.add(exitbutton);

//registering button handler to the 6 buttons
        buttonhnadler mybuttonhandler = new buttonhnadler();
        addbutton.addActionListener(mybuttonhandler);
        deletebutton.addActionListener(mybuttonhandler);
        deleteallbutton.addActionListener(mybuttonhandler);
        savebutton.addActionListener(mybuttonhandler);
        exitbutton.addActionListener(mybuttonhandler);
        aboutbutton.addActionListener(mybuttonhandler);
//registering  button panel to the main window frame
        add(buttonpanel, BorderLayout.EAST);
        buttonpanel.requestFocusInWindow();

    }
//creating button listener to make the button functioning

    class buttonhnadler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == savebutton) {
                try {

                    int indx = mytable.getSelectedRow();
                    if (indx != -1) {
                        Data temp = myvec.elementAt(indx);
                        temp.setDetails(textField.getText());
                    }
                    saveList();
                } catch (Exception e) {
                    System.out.println("Save button" + e);
                }

            }
            if (ae.getSource() == aboutbutton) {
                AboutClass about = new AboutClass();
                about.setSize(220, 330);
                about.setAlwaysOnTop(true);
                about.setBounds(220, 50, 250, 270);
                about.setVisible(true);
                about.setResizable(false);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dim.getWidth() - about.getWidth()) / 2);
                int y = 160;
                about.setLocation(x, y);
                Image icon = new ImageIcon(getClass().getResource("about.png")).getImage();
                about.setIconImage(icon);
            }
            if (ae.getSource() == exitbutton) {

                int indx = mytable.getSelectedRow();
                if (indx != -1) {
                    Data temp = myvec.elementAt(indx);
                    temp.setDetails(textField.getText());
                }
                saveList();
                myvec.clear();
                System.exit(0);
            }

            if (ae.getSource() == deletebutton) {
                if (mytable.getSelectedRowCount() == 0) {
                    JOptionPane.showMessageDialog(rootPane, "No task is selected. Select a task.", "Warning!", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        int showConfirmDialog = JOptionPane.showConfirmDialog(rootPane, "Are you sure?");
                        if (showConfirmDialog == 0) {

                            int indx = mytable.getSelectedRows().length;
                            for (int i = 0; i < indx; i++) {
                                int selected_row = mytable.getSelectedRow();
                                mymodel.removeRow(selected_row);
                                myvec.remove(selected_row);
                            }
                            prev = -1;
                            //   System.out.println("present row in delte button" + indx);
                            inputtexfield.setText("Add your task here...");
                            textField.setText("Add the details of your task here...");
                        }
                    } catch (Exception e) {
                        System.out.println("delete button: " + e);

                    }

                }
            }
            if (ae.getSource() == deleteallbutton) {
                try {
                    String ps = JOptionPane.showInputDialog(rootPane, "Type  \"MAZI\"  to Confirm.");
                    if (ps.equals("MAZI")) {
                        while (mytable.getRowCount() > 0) {
                            mymodel.removeRow(0);
                        }
                        myvec.clear();
                        prev = -1;
                        inputtexfield.setText("Add your task here...");
                        textField.setText("Add the details of your task here...");

                    }

                } catch (Exception ee) {

                }
            }
        }
    }

    /**
     * *
     * this method encapsulating timelabel.. returns timelabel reference..
     * currently it's being used in TimeThread() class.
     *
     * @return Jlabel timelabel reference to the caller.
     */
    public JLabel getTimeLabel() {
        return timeLabel;
    }

    /**
     * *
     *
     * this method saves the current table into a file while exiting the app or
     * save button is pressed.
     */
    private void saveList() {

        try {

            File myfile = new File(HOME_DIR + "/.TodoList.ms");
            try (ObjectOutputStream myout = new ObjectOutputStream(new FileOutputStream(myfile))) {
                for (int i = 0; i < mymodel.getRowCount(); i++) {
                    myvec.get(i).setFlag((boolean) mymodel.getValueAt(i, 0));
                    myvec.get(i).setTitle((String) mymodel.getValueAt(i, 1));
                }
                for (Data temp : myvec) {
                    myout.writeObject(temp);

                }
                myout.close();
            }

        } catch (Exception e) {
                    e.printStackTrace();
            //   System.out.println("saveList: " + e);
        }
    }

    /**
     * *
     *
     * this method loads the task list when the application starts up.
     */
    private void loadList() {

        String tasklist = null;
        Data inputdata;
        String pathtoload = HOME_DIR +  "/.TodoList.ms";
        File myfile = new File(pathtoload);
        ObjectInputStream myin = null;
        try {
            myin = new ObjectInputStream(new FileInputStream(myfile));
        } catch (IOException ex) {
            //  Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while ((inputdata = (Data) myin.readObject()) != null) {
                if (!inputdata.isPast()) {
                    myvec.add(inputdata);
                    mymodel.addRow(inputdata.getData());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
