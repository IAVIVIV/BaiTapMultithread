package BT;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class BaiTapMultithread extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel clockLabel;
	private JTextField timezoneField;

	public BaiTapMultithread() {
		setTitle("World Clock");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 150);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		clockLabel = new JLabel();
		clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(clockLabel, BorderLayout.CENTER);

		JPanel controlPanel = new JPanel(new BorderLayout());
		timezoneField = new JTextField();
		controlPanel.add(timezoneField, BorderLayout.CENTER);

		JButton addButton = new JButton("Add Clock");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addClock();
			}
		});
		controlPanel.add(addButton, BorderLayout.EAST);

		add(controlPanel, BorderLayout.SOUTH);

		// Start the clock
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateTime();
			}
		});
		timer.start();
	}

	private void updateTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		clockLabel.setText(sdf.format(cal.getTime()));
	}

	private void addClock() {
		String timezone = timezoneField.getText().trim();
		if (timezone.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter a timezone.");
			return;
		}

		TimeZone tz = TimeZone.getTimeZone(timezone);
		if (tz == null) {
			JOptionPane.showMessageDialog(this, "Invalid timezone.");
			return;
		}

		JFrame newClockFrame = new JFrame("World Clock - " + timezone);
		JLabel newClockLabel = new JLabel();
		newClockLabel.setHorizontalAlignment(SwingConstants.CENTER);
		newClockFrame.add(newClockLabel);

		newClockFrame.setSize(300, 150);
		newClockFrame.setLocationRelativeTo(null);

		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				sdf.setTimeZone(tz);
				newClockLabel.setText(sdf.format(cal.getTime()));
			}
		});
		timer.start();

		newClockFrame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new BaiTapMultithread().setVisible(true);
			}
		});
	}
}
