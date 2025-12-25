package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ExpenseTrackerUI extends JFrame {

    private JTextField txtName, txtAmount, txtDate;
    private JTable table;
    private DefaultTableModel model;
    private JLabel lblTotal;

    public ExpenseTrackerUI() {
        setTitle("Aplikasi Pengeluaran Mingguan");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initUI();

        // pack supaya ukuran mengikuti preferred sizes komponen
        pack();
        // beri minimum size agar window tidak menyusut terlalu kecil
        setMinimumSize(new Dimension(640, 420));
        setLocationRelativeTo(null);
    }

    private void initUI() {
        // Root panel dengan padding
        JPanel root = new JPanel(new BorderLayout(8, 8));
        root.setBorder(new EmptyBorder(8, 8, 8, 8));
        setContentPane(root);

        // ====== INPUT PANEL (GridBagLayout agar rapi) ======
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;

        // Nama Barang (label + field)
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Nama Barang:"), gbc);

        txtName = new JTextField();
        txtName.setColumns(18); // kontrol lebar field
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(txtName, gbc);

        // Harga (label + field)
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2; gbc.gridy = 0;
        inputPanel.add(new JLabel("Harga (Rp):"), gbc);

        txtAmount = new JTextField();
        txtAmount.setColumns(9);
        gbc.gridx = 3; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(txtAmount, gbc);

        // Tanggal (label + field)
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Tanggal (dd-mm-yyyy):"), gbc);

        txtDate = new JTextField();
        txtDate.setColumns(9);
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(txtDate, gbc);

        // Tombol Add / Update / Delete pada baris yang sama (akan ditaruh di kanan)
        JPanel buttonGroup = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        buttonGroup.add(btnAdd);
        buttonGroup.add(btnUpdate);
        buttonGroup.add(btnDelete);

        gbc.gridx = 3; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(buttonGroup, gbc);

        // Tambah inputPanel ke root NORTH
        root.add(inputPanel, BorderLayout.NORTH);

        // ====== TABEL ======
        String[] columns = {"Nama Barang", "Harga", "Tanggal"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // edit via form
            }
        };
        table = new JTable(model);
        table.setRowHeight(26);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true); // memungkinkan sort klik header
        // atur agar kolom harga tidak terlalu sempit: set preferred widths
        table.getColumnModel().getColumn(0).setPreferredWidth(260);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);

        JScrollPane scrollPane = new JScrollPane(table);
        // berikan preferred size pada scrollPane supaya tidak memenuhi area kosong berlebihan
        scrollPane.setPreferredSize(new Dimension(620, 280));
        root.add(scrollPane, BorderLayout.CENTER);

        // ====== TOTAL PANEL di SOUTH ======
        lblTotal = new JLabel("Total Pengeluaran: Rp 0");
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 14));
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.add(lblTotal);
        root.add(totalPanel, BorderLayout.SOUTH);

        // ====== ACTIONS ======
        btnAdd.addActionListener(e -> addExpense());
        btnDelete.addActionListener(e -> deleteExpense());
        btnUpdate.addActionListener(e -> updateExpense());

        // double click row untuk isi form (mempermudah update)
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int r = table.getSelectedRow();
                    if (r != -1) {
                        // jika table di-sorted, dapatkan model index
                        int modelRow = table.convertRowIndexToModel(r);
                        txtName.setText(model.getValueAt(modelRow, 0).toString());
                        txtAmount.setText(model.getValueAt(modelRow, 1).toString());
                        txtDate.setText(model.getValueAt(modelRow, 2).toString());
                    }
                }
            }
        });
    }

    private void addExpense() {
        String name = txtName.getText().trim();
        String amountStr = txtAmount.getText().trim();
        String date = txtDate.getText().trim();

        if (name.isEmpty()) { JOptionPane.showMessageDialog(this, "Nama barang belum diisi!"); return; }
        if (amountStr.isEmpty()) { JOptionPane.showMessageDialog(this, "Harga belum diisi!"); return; }
        if (date.isEmpty()) { JOptionPane.showMessageDialog(this, "Tanggal belum diisi!"); return; }

        try {
            double amount = Double.parseDouble(amountStr);
            model.addRow(new Object[]{name, amount, date});
            clearInputs();
            calculateTotal();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka!");
        }
    }

    private void deleteExpense() {
        int selected = table.getSelectedRow();
        if (selected == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!");
            return;
        }
        int modelRow = table.convertRowIndexToModel(selected);
        int confirm = JOptionPane.showConfirmDialog(this, "Hapus data terpilih?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            model.removeRow(modelRow);
            calculateTotal();
        }
    }

    private void updateExpense() {
        int selected = table.getSelectedRow();
        if (selected == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin diupdate!");
            return;
        }
        int modelRow = table.convertRowIndexToModel(selected);

        String name = txtName.getText().trim();
        String amountStr = txtAmount.getText().trim();
        String date = txtDate.getText().trim();

        if (name.isEmpty() || amountStr.isEmpty() || date.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi untuk update!");
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);
            model.setValueAt(name, modelRow, 0);
            model.setValueAt(amount, modelRow, 1);
            model.setValueAt(date, modelRow, 2);
            clearInputs();
            calculateTotal();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka!");
        }
    }

    private void calculateTotal() {
        double total = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            Object val = model.getValueAt(i, 1);
            if (val != null) {
                try {
                    total += Double.parseDouble(val.toString());
                } catch (NumberFormatException ignored) {}
            }
        }
        lblTotal.setText("Total Pengeluaran: Rp " + String.format("%,.0f", total));
    }

    private void clearInputs() {
        txtName.setText("");
        txtAmount.setText("");
        txtDate.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ExpenseTrackerUI ui = new ExpenseTrackerUI();
            ui.setVisible(true);
        });
    }
}
