package org.example;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MoodListFrame extends JFrame {

    public MoodListFrame() {
        setTitle("Daftar Mood");
        setSize(600, 300);
        setLocationRelativeTo(null);

        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Tanggal", "Mood", "Catatan"}, 0);

        for (Mood m : MoodManager.moods) {
            model.addRow(new Object[]{
                    m.getDate(),
                    m.getMood(),
                    m.getNote()
            });
        }

        JTable table = new JTable(model);
        add(new JScrollPane(table));

        setVisible(true);
    }
}
