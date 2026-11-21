import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TodoKuliah extends JFrame implements ActionListener {

    // Atomic components
    JTextField txtTugas = new JTextField(20);
    JComboBox<String> cmbMatkul = new JComboBox<>(new String[]{
            "PBO", "Algoritma & Struktur Data", "Metode Numerik", "Sistem Basis Data", "Kecerdasan Buatan"});
    JCheckBox cbPenting = new JCheckBox("Penting");
    JButton btnTambah = new JButton("Tambah");
    JButton btnSelesai = new JButton("Tandai/Batal Selesai");
    JButton btnHapus = new JButton("Hapus");
    JButton btnBersihkan = new JButton("Bersihkan Selesai");
    DefaultListModel<String> model = new DefaultListModel<>();
    JList<String> daftar = new JList<>(model);
    JLabel lblStatus = new JLabel("Total: 0 | Selesai: 0", SwingConstants.CENTER);

    public TodoKuliah() {
        super("To-Do List Tugas Kuliah");                
        setSize(560, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);    
        setLayout(new BorderLayout(5, 5));                

        
        JPanel baris1 = new JPanel(new FlowLayout());     
        baris1.add(new JLabel("Tugas:"));
        baris1.add(txtTugas);
        baris1.add(btnTambah);
        JPanel baris2 = new JPanel(new FlowLayout());
        baris2.add(new JLabel("Mata Kuliah:"));
        baris2.add(cmbMatkul);
        baris2.add(cbPenting);
        JPanel panelInput = new JPanel(new GridLayout(2, 1)); 
        panelInput.add(baris1);
        panelInput.add(baris2);

        
        JPanel panelAksi = new JPanel(new GridLayout(1, 3, 5, 5));
        panelAksi.add(btnSelesai);
        panelAksi.add(btnHapus);
        panelAksi.add(btnBersihkan);
        JPanel panelBawah = new JPanel(new BorderLayout());
        panelBawah.add(panelAksi, BorderLayout.CENTER);
        panelBawah.add(lblStatus, BorderLayout.SOUTH);

        add(panelInput, BorderLayout.NORTH);
        add(new JScrollPane(daftar), BorderLayout.CENTER);
        add(panelBawah, BorderLayout.SOUTH);

        
        txtTugas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) tambah();
            }
        });
        
        daftar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) toggleSelesai();
            }
        });
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                keluar();
            }
        });
    }


    void pasangListener() {
        for (JButton b : new JButton[]{btnTambah, btnSelesai, btnHapus, btnBersihkan}) {
            b.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTambah) tambah();
        else if (e.getSource() == btnSelesai) toggleSelesai();
        else if (e.getSource() == btnHapus) hapus();
        else bersihkan();
    }

    void tambah() {
        String tugas = txtTugas.getText().trim();
        if (tugas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tugas tidak boleh kosong!");
            return;
        }
        String penting = cbPenting.isSelected() ? "(!) " : "";
        model.addElement("[" + cmbMatkul.getSelectedItem() + "] " + penting + tugas);
        txtTugas.setText("");
        cbPenting.setSelected(false);
        updateStatus();
    }

    int pilih() {
        int i = daftar.getSelectedIndex();
        if (i == -1) JOptionPane.showMessageDialog(this, "Pilih tugas dulu!");
        return i;
    }

    void toggleSelesai() {
        int i = pilih();
        if (i == -1) return;
        String s = model.get(i);
        model.set(i, s.startsWith("[SELESAI] ") ? s.substring(10) : "[SELESAI] " + s);
        updateStatus();
    }

    void hapus() {
        int i = pilih();
        if (i != -1 && JOptionPane.showConfirmDialog(this, "Hapus tugas ini?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            model.remove(i);
            updateStatus();
        }
    }

    void bersihkan() {
        for (int i = model.size() - 1; i >= 0; i--) {
            if (model.get(i).startsWith("[SELESAI]")) model.remove(i);
        }
        updateStatus();
    }

    void updateStatus() {
        int selesai = 0;
        for (int i = 0; i < model.size(); i++) {
            if (model.get(i).startsWith("[SELESAI]")) selesai++;
        }
        lblStatus.setText("Total: " + model.size() + " | Selesai: " + selesai);
    }

    void keluar() {
        if (JOptionPane.showConfirmDialog(this, "Yakin ingin keluar?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TodoKuliah app = new TodoKuliah();
            app.pasangListener();
            app.setVisible(true);
        });
    }
}