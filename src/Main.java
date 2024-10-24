import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Main extends JFrame {

    private JTextField baseNameField;
    private JTextField startIndexField;
    private JLabel directoryLabel;
    private File selectedDirectory;
    private JRadioButton indexAtStartButton;
    private JRadioButton indexAtEndButton;

    public Main() {
        setTitle("Rebatcher");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // Directory chooser components
        JLabel directoryPrompt = new JLabel("Select Directory:");
        directoryLabel = new JLabel("No directory selected");
        JButton chooseDirectoryButton = new JButton("Choose Directory");

        // Base name input components
        JLabel baseNamePrompt = new JLabel("Base Name:");
        baseNameField = new JTextField();

        // Starting index input components
        JLabel startIndexPrompt = new JLabel("Start Index:");
        startIndexField = new JTextField("1");

        // Index position radio buttons
        JLabel indexPositionPrompt = new JLabel("Index Position:");
        indexAtStartButton = new JRadioButton("Start", true);  // Default: index at the start
        indexAtEndButton = new JRadioButton("End");

        // Group radio buttons
        ButtonGroup indexPositionGroup = new ButtonGroup();
        indexPositionGroup.add(indexAtStartButton);
        indexPositionGroup.add(indexAtEndButton);

        // Rename button
        JButton renameButton = new JButton("Rename Files");

        // Add components to the window
        add(directoryPrompt);
        add(directoryLabel);
        add(chooseDirectoryButton);
        add(new JLabel()); // empty placeholder
        add(baseNamePrompt);
        add(baseNameField);
        add(startIndexPrompt);
        add(startIndexField);
        add(indexPositionPrompt);
        add(indexAtStartButton);
        add(new JLabel()); // empty placeholder for better layout
        add(indexAtEndButton);
        add(renameButton);

        // Choose directory button action
        chooseDirectoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int option = fileChooser.showOpenDialog(Main.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    selectedDirectory = fileChooser.getSelectedFile();
                    directoryLabel.setText(selectedDirectory.getAbsolutePath());
                } else {
                    directoryLabel.setText("No directory selected");
                }
            }
        });

        // Rename button action
        renameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String baseName = baseNameField.getText();
                String startIndexStr = startIndexField.getText();

                if (selectedDirectory == null || !selectedDirectory.isDirectory()) {
                    JOptionPane.showMessageDialog(null, "Please select a valid directory.");
                    return;
                }

                if (baseName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid base name.");
                    return;
                }

                int startIndex;
                try {
                    startIndex = Integer.parseInt(startIndexStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for the start index.");
                    return;
                }

                // Check where to place the index (start or end)
                boolean indexAtStart = indexAtStartButton.isSelected();

                renameFiles(selectedDirectory, baseName, startIndex, indexAtStart);
            }
        });
    }

    // Method to rename files in the directory
    private void renameFiles(File directory, String baseName, int startIndex, boolean indexAtStart) {
        File[] files = directory.listFiles();

        if (files == null || files.length == 0) {
            JOptionPane.showMessageDialog(null, "No files found in the selected directory.");
            return;
        }

        int index = startIndex;

        for (File file : files) {
            if (file.isFile()) {
                // Get file extension
                String fileName = file.getName();
                String fileExtension = "";

                int i = fileName.lastIndexOf('.');
                if (i > 0) {
                    fileExtension = fileName.substring(i);  // Keep the dot (e.g., ".txt")
                }

                // Create the new file name based on the chosen index position
                String newFileName;
                if (indexAtStart) {
                    newFileName = index + baseName + fileExtension;
                } else {
                    newFileName = baseName + index + fileExtension;
                }

                File newFile = new File(directory.getAbsolutePath() + "/" + newFileName);

                // Rename the file
                if (file.renameTo(newFile)) {
                    System.out.println("Renamed: " + file.getName() + " -> " + newFileName);
                } else {
                    System.out.println("Failed to rename: " + file.getName());
                }

                index++;
            }
        }

        JOptionPane.showMessageDialog(null, "Files renamed successfully.");
    }

    public static void main(String[] args) {
        // Create and display the GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main frame = new Main();
                frame.setVisible(true);
            }
        });
    }
}
