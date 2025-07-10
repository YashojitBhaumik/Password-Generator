import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class PasswordGeneratorGUI extends JFrame {
    private JTextField lengthField;
    private JCheckBox specialCharsCheckBox;
    private JTextArea outputArea;

    public PasswordGeneratorGUI() {
        setTitle("Password Generator");//window title
        setSize(400, 400);//size of window to be 400*400 pixels
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//setting default windows behaviours
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));//initialising JPanel class
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lengthLabel = new JLabel("Enter password length:");
        lengthField = new JTextField();

        JLabel specialCharsLabel = new JLabel("Include special characters:");
        specialCharsCheckBox = new JCheckBox();

        JButton generateButton = new JButton("Generate Passwords");

        inputPanel.add(lengthLabel);
        inputPanel.add(lengthField);
        inputPanel.add(specialCharsLabel);
        inputPanel.add(specialCharsCheckBox);
        inputPanel.add(new JLabel()); // Spacer
        inputPanel.add(generateButton);

        // Output Area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Action Listener
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generatePasswords();
            }
        });

        setVisible(true);
    }

    private void generatePasswords() {
        outputArea.setText("");
        int n;
        try {
            n = Integer.parseInt(lengthField.getText());
            if (n < 8) {
                JOptionPane.showMessageDialog(this, "Password should be at least 8 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for length.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean includeSpecialChars = specialCharsCheckBox.isSelected();

        for (int i = 0; i < 10; i++) {
            outputArea.append(generatePassword(n, includeSpecialChars) + "\n");
        }
    }

    private String generatePassword(int length, boolean includeSpecialChars) {
        StringBuilder str = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            int[] arr = new int[4];
            arr[0] = r.nextInt(90 - 65 + 1) + 65;  // Uppercase
            arr[1] = r.nextInt(122 - 97 + 1) + 97; // Lowercase
            arr[2] = r.nextInt(57 - 48 + 1) + 48;  // Digits
            arr[3] = r.nextInt(47 - 33 + 1) + 33;  // Special characters

            char ch;
            if (includeSpecialChars) {
                ch = (char) (arr[r.nextInt(4)]);
            } else {
                ch = (char) (arr[r.nextInt(3)]);
            }
            str.append(ch);
        }
        return str.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PasswordGeneratorGUI());
    }
}
