package forum;

import model.Animal;
import model.AnimalsData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimalsUIs extends JFrame implements ActionListener {

    private JTextField txtID, txtSpecies; // text given from user
    private JList liHabitat, liTypes;  // list                           // Raw use of parameterized class 'JList'?
    int id; // randomize id or given
    private JTextArea txtDisplay; // shows the tex

    AnimalsData data;
    public AnimalsUIs()  // GUI
    {
        super("Animals register");  // title
        id=1;
        data=new AnimalsData();
        configureUI(); // fetch the info/ present
        setSize(730,500);  // play with
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false); // cannot change the window as u/I
        setVisible(true); // shows the window

    }
    private void configureUI()
    {

        JPanel buttonPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,20,20)); // buttons on gui present canter & flowing
        // buttons, creates new button called:
        JButton btnAdd; // local variable
        buttonPanel.add(btnAdd = new JButton("Add"));
        JButton btnRemove;
        buttonPanel.add(btnRemove = new JButton("Remove"));
        JButton btnUpdate;
        buttonPanel.add(btnUpdate = new JButton("Update"));
        // Buttons
        JButton btnSort;
        buttonPanel.add(btnSort = new JButton("Sort"));
        // Initialize actionCommand and tell listener.
        btnAdd.setActionCommand("Add");
        btnAdd.addActionListener(this);
        // remove
        btnRemove.setActionCommand("Remove");
        btnRemove.addActionListener(this);
        // Update
        btnUpdate.setActionCommand("Update");
        btnUpdate.addActionListener(this);
        // Sort
        btnSort.setActionCommand("Sort");
        btnSort.addActionListener(this);


        JPanel leftPanel=new JPanel();
        // left panel on GUI
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // check the documentation

        JLabel l=new JLabel("ID:",JLabel.LEFT);
        leftPanel.add(l);
        txtID=new JTextField(20);
        l.setLabelFor(txtID);
        leftPanel.add(txtID);

        leftPanel.add(l=new JLabel("Species:"));
        txtSpecies =new JTextField(20);
        l.setLabelFor(txtSpecies); // dyreart
        leftPanel.add(txtSpecies);

        // scroll menu/pane
        String[] types ={"Mammal","Bird","Fish","Reptile"};
        liTypes = new JList<>(types); // readonly
        // one selection a time e.g. mammal:
        liTypes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        liTypes.setVisibleRowCount(4); // four animal type, that user can choose from.
        // create scroll pane and add the list types to it.
        JScrollPane scrTypes=new JScrollPane(liTypes);
        // give demensions
        scrTypes.setPreferredSize(new Dimension(100,100));
        leftPanel.add(l=new JLabel("Type:"));
        liTypes.setSelectedIndex(0); // start from index 0
        l.setLabelFor(scrTypes);
        // add the left pane to this panel.
        leftPanel.add(scrTypes);

        // copy paste as above - Scroll menu for Habitat
        String[] habitat ={"Forest","Desert", "Polar", "Ocean", "Jungle", "Freshwater","Mountain"};
        liHabitat = new JList<>(habitat);
        liHabitat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrHabitat=new JScrollPane(liHabitat);
        scrHabitat.setPreferredSize(new Dimension(100, 60));
        leftPanel.add(l=new JLabel("Habitat:"));
        liHabitat.setSelectedIndex(0);
        l.setLabelFor(scrHabitat);
        leftPanel.add(scrHabitat);

        // display text

        txtDisplay=new JTextArea();
        JScrollPane scrDisplay=new JScrollPane(txtDisplay);
        JPanel centFlowPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));

        scrDisplay.setPreferredSize(new Dimension(400,270));
        centFlowPanel.add(scrDisplay);


        // mainPanel
        Container mainPanel=getContentPane();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(centFlowPanel, BorderLayout.CENTER);
        // https://docs.oracle.com/javase/7/docs/api/java/awt/BorderLayout.html
        pack();
    }


    // when the user press the butttons.
    @Override
    public void actionPerformed(ActionEvent e) { // user click one of those buttons
        if(e.getActionCommand().equals("Add"))
        {
            String species= txtSpecies.getText();
            String type=(String) liTypes.getSelectedValue();
            String habitat=(String) liHabitat.getSelectedValue();
            if(species==null || species.trim().equals(""))
                JOptionPane.showMessageDialog(this, "Please enter a valid species.");
            else
            {

                data.add(new Animal(id,species,type,habitat));
                id++;
                updateDisplay();
            }
        }
        else if(e.getActionCommand().equals("Remove"))
        {
            String id=txtID.getText();

            if(id==null || id.trim().equals(""))
                JOptionPane.showMessageDialog(this, " Enter a valid ID.");
            else
            {
                int num=Integer.parseInt(id);
                if(data.remove(num))
                    updateDisplay();
                else
                    JOptionPane.showMessageDialog(this,"There is no animal ID with = "+num);
            }
        }
        /// Update + messageDialog , when userinput is incorrect.
        else if(e.getActionCommand().equals("Update"))
        {
            String id =txtID.getText();
            String species= txtSpecies.getText();
            String type=(String) liTypes.getSelectedValue();
            String habitat=(String) liHabitat.getSelectedValue();
            // Show messageDialog
            if(id==null || id.trim().equals(""))
                JOptionPane.showMessageDialog(this, " Enter an ID");
            else if(species==null || species.trim().equals(""))
                JOptionPane.showMessageDialog(this, "reenter a valid species.");
            else
            {
                int num=Integer.parseInt(id);
                if(data.update(num,species,type,habitat))
                    updateDisplay();
                else
                    JOptionPane.showMessageDialog(this,"Could not find animal with ID = "+num);
            }
        }
        // sort
        else if(e.getActionCommand().equals("Sort"))
        {
            String type=(String) liTypes.getSelectedValue();
            String habitat=(String) liHabitat.getSelectedValue();
            data.sort(type,habitat);
            updateDisplay();
        }

    }

    private void updateDisplay()
    {
        txtDisplay.setText(data.toString());
        txtDisplay.repaint(); // is a method for updates/ change previous input

    }
}
