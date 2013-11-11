package labyrinth;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class App 
{
    private static Maze maze;
    private static List<Integer> path;
    private static boolean showPath = false;
    private static int[][] intMaze = {
            {0, 0, 0, 0, 0, 0},
            {1, 1, 0, 1, 0, 0},
            {0, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 1, 0},
            {0, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0}
    };

    public static void addComponentToPane(Container pane) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JTable mazeTable = new JTable(intMaze.length,intMaze[0].length);
        for (int i = 0; i < intMaze[0].length; i++) {
            mazeTable.getColumnModel().getColumn(i).setCellRenderer(new myCellRenderer());
            mazeTable.getColumnModel().getColumn(i).setPreferredWidth(20);
        }
        JButton showButton = new JButton("Найти выход");
        showButton.addActionListener(new ShowPathListener(mazeTable));
        panel.add(mazeTable);
        panel.add(showButton);
        pane.add(panel);
    }

    public static void createGUI() {
        JFrame frame = new JFrame("Лабиринт");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentToPane(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    private static class myCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

            if (maze.ifSolved(row, col) && showPath) {
                l.setBackground(Color.RED);
            } else
            if (intMaze[row][col] == 1) {
                l.setBackground(Color.WHITE);
            } else
                l.setBackground(Color.BLACK);
            return l;
        }
    }

    private static class ShowPathListener implements ActionListener {
        JTable table;
        private ShowPathListener (JTable table) {
            this.table = table;
        }
        public void actionPerformed(ActionEvent e) {
            showPath = true;
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            tableModel.fireTableDataChanged();
        }
    }

    public static void main( String[] args )
    {
        maze = new Maze(intMaze);
        path = maze.path;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });

    }
}
