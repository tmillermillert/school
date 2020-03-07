package main.java.memoranda.ui;


import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class SearchFrame extends JFrame {
	private JPanel contentPane;
    private JTable table;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final ButtonGroup buttonGroup_1 = new ButtonGroup();
    private DefaultTableModel tm = new DefaultTableModel(10,10);

    public SearchFrame() {
        setTitle("Search");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 556, 615);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        JButton btnSearch = new JButton("Search");
        JLabel lblTable = new JLabel("Results");
        JTextField searchField = new JTextField();

        table = new JTable();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);


        table.setModel(tm);
        table.setBounds(30, 40, 200, 300);
        table.repaint();
        contentPane.add(searchField);
        contentPane.add(btnSearch);
        contentPane.add(lblTable);
        contentPane.add(table);

        contentPane.setVisible(true);

}
}