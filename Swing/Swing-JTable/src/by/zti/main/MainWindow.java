package by.zti.main;

import javax.swing.*;

class MainWindow extends JFrame {

	private static final long serialVersionUID = 1696672477561497852L;
	private JTable table;

	MainWindow() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(640, 480);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 480, 430);
		getContentPane().add(scrollPane);

		PersonModel model = new PersonModel();
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(e -> {
            Person person = new Person();
            person.setName(JOptionPane.showInputDialog("Enter name:"));
            person.setSurname(JOptionPane.showInputDialog("Enter surname"));
            Main.persons.add(person);
            table.updateUI();
        });
		btnAdd.setBounds(535, 11, 89, 23);
		getContentPane().add(btnAdd);
		
		JButton btnRomove = new JButton("Romove");
		btnRomove.addActionListener(arg0 -> {
            if(table.getSelectedRow()==-1||Main.persons.size()>0){return;}
            Main.persons.remove(table.getSelectedRow());
            table.updateUI();
        });
		btnRomove.setBounds(535, 45, 89, 23);
		getContentPane().add(btnRomove);
		setVisible(true);
	}
}
