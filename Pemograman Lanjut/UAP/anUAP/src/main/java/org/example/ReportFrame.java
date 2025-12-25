package org.example;
import javax.swing.*;
import java.util.HashMap;

public class ReportFrame extends JFrame {
    public ReportFrame() {
        setTitle("Laporan Mood");
        setSize(300, 300);
        setLocationRelativeTo(null);
        HashMap<String, Integer> report = new HashMap<>();

        for (Mood m : MoodManager.moods) {
            report.put(m.getMood(), report.getOrDefault(m.getMood(), 0) + 1);
        }
    }
}