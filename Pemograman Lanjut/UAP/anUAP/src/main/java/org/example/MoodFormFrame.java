package org.example;
import javax.swing.*;
import java.time.LocalDate;

public class MoodFormFrame extends JFrame {

    public MoodFormFrame() {
        setTitle("Input Mood");
        setSize(350, 300);
        setLocationRelativeTo(null);

        JTextField txtDate = new JTextField(LocalDate.now().toString());
        JComboBox<String> cmbMood = new JComboBox<>(
                new String[]{"Happy", "Sad", "Angry", "Calm", "Excited"});
        JTextArea txtNote = new JTextArea(4, 20);

        JButton btnSave = new JButton("Save");

        btnSave.addActionListener(e -> {
            Mood mood = new Mood(
                    txtDate.getText(),
                    cmbMood.getSelectedItem().toString(),
                    txtNote.getText()
            );

            MoodManager.moods.add(mood);
            MoodManager.saveData();
            JOptionPane.showMessageDialog(this, "Mood berhasil disimpan!");
            dispose();
        });

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Tanggal"));
        add(txtDate);
        add(new JLabel("Mood"));
        add(cmbMood);
        add(new JLabel("Catatan"));
        add(new JScrollPane(txtNote));
        add(btnSave);

        setVisible(true);
    }
}