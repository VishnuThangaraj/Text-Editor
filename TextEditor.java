import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

    // Frame (Layout for the textEditor)
    JFrame frame;
    JScrollPane scrollPane;
    JPanel mainPanel;
    JMenuBar menuBar;
    JMenu file, edit;
    // File- Menu items
    JMenuItem newFile, openFile, saveFile;

    // Edit- Menu items
    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea; // textArea to type the notes

    // constructor
    TextEditor(){
        // Initializations
        frame = new JFrame();
        menuBar = new JMenuBar();
        textArea = new JTextArea();

        // Menu Initialization
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // ############   FileMenu items   ############ //

        newFile = new JMenuItem("🆕  New File");
        openFile = new JMenuItem("📖  Open File");
        saveFile = new JMenuItem("✅  Save File");

        // Action listeners for the file menu Items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // add file menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // ############   EditMenu items   ############ //

        cut = new JMenuItem("✂️  Cut");
        copy = new JMenuItem("📃  Copy");
        paste = new JMenuItem("📋  Paste");
        selectAll = new JMenuItem("🏁  Select All");
        close = new JMenuItem("❌  Close");

        // Action Listeners for Edit Menu Items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // Add Edit menu items to Edit Menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // add menu items to the Menu-Bar
        menuBar.add(file);
        menuBar.add(edit);

        // set Menu-Bar to the Frame
        frame.setJMenuBar(menuBar);

        // JPanel to add the scroll bar Horizontally and vertically
        mainPanel = new JPanel();

        // set the panel border and layout
        mainPanel.setBorder(new EmptyBorder(5,5,5,5));
        mainPanel.setLayout(new BorderLayout(0,0));

        // add the text-Area to the panel
        mainPanel.add(textArea, BorderLayout.CENTER);

        // Create scroll pane
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add jScroll to panel
        mainPanel.add(scrollPane);
        frame.add(mainPanel);

        // Set the window size of the text-Editor
        frame.setBounds(300,150,800,500);
        frame.setTitle("Notepad (v1.0)");
        frame.setVisible(true);
        frame.setLayout(null);
    }

    // decides which to occur when the menu is selected(Functionalities)
    @Override
    public void actionPerformed(ActionEvent event) {

        // ############   EditMenu Functionalities   ############ //

        if(event.getSource() == cut){ // cut
            textArea.cut();
        }
        if(event.getSource() == copy){ // copy
            textArea.copy();
        }
        if(event.getSource() == paste){ // paste
            textArea.paste();
        }
        if(event.getSource() == selectAll) { // select-All
            textArea.selectAll();
        }
        if(event.getSource() == close){ // close
            System.exit(0); // end the text-Editor application
        }

        // ############   FileMenu Functionalities   ############ //

        if(event.getSource() == openFile){ // open existing file from storage
            JFileChooser fileSelect = new JFileChooser("C:"); // default directory (C: (drive))
            int chooseOption = fileSelect.showOpenDialog(null);

            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // get the current file from the storage
                File currentFile = fileSelect.getSelectedFile();

                String filePath = currentFile.getPath();

                try{
                    FileReader fileReader = new FileReader(filePath);

                    // BufferedReader
                    BufferedReader reader = new BufferedReader(fileReader);
                    String intermediate = "";
                    StringBuilder output = new StringBuilder();

                    // read the chosen file
                    while((intermediate = reader.readLine()) != null){
                        output.append(intermediate).append("\n");
                    }

                    // set the output string to the textArea
                    textArea.setText(output.toString());
                }
                catch(IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }

        if(event.getSource() == saveFile){ // save the current file to storage
            JFileChooser fileChooser = new JFileChooser("C:");
            int ChooseOption = fileChooser.showSaveDialog(null);

            if(ChooseOption == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");

                try{
                    FileWriter write = new FileWriter(file);
                    BufferedWriter writer = new BufferedWriter(write);

                    // Write the contents of textArea
                    textArea.write(writer);
                    writer.close();
                }
                catch(IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }

        if(event.getSource() == newFile){
            TextEditor editor = new TextEditor(); // creates new Empty Window
        }
    }

    public static void main(String[] args) {
        // create instance for the text-Editor
        TextEditor editor = new TextEditor(); // start
    }
}
