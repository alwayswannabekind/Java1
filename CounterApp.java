import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CounterApp extends JFrame {

    private int counter = 0;

    public CounterApp() {
        setTitle("CounterApp window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(500, 500, 1000, 1000);
        setLayout(null);
        Font font = new Font("Arial", Font.BOLD, 30);

        JLabel counterView = new JLabel(String.valueOf(counter));
        counterView.setHorizontalAlignment(SwingConstants.CENTER);
        counterView.setFont(font);
        counterView.setBounds(450,80,100, 30);
        add(counterView);

        JButton resetButton = new JButton(" RESET ");
        resetButton.setBounds(450, 50, 100, 20);
        add(resetButton);

        JButton incrementButton = new JButton(">");
        incrementButton.setBounds(550, 70, 100, 20);
        add(incrementButton);

        JButton decrementButton = new JButton("<");
        decrementButton.setBounds(350, 70, 100, 20);
        add(decrementButton);

        JButton minusTenButton = new JButton(" - 10 ");
        minusTenButton.setBounds(350, 90, 100, 20);
        add(minusTenButton);

        JButton plusTenButton = new JButton(" + 10 ");
        plusTenButton.setBounds(550, 90, 100, 20);
        add(plusTenButton);


        // -----------------------------------------------------------
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == incrementButton) {
                    counter++;
                } else if (e.getSource() == decrementButton) {
                    counter--;
                } else if (e.getSource() == resetButton) {
                    counter = 0;
                } else if (e.getSource() == minusTenButton) {
                    counter -= 10;
                } else if (e.getSource() == plusTenButton) {
                    counter += 10;
                }
                refreshCounterView(counterView);
            }
        };

        decrementButton.addActionListener(actionListener);
        incrementButton.addActionListener(actionListener);
        resetButton.addActionListener(actionListener);
        minusTenButton.addActionListener(actionListener);
        plusTenButton.addActionListener(actionListener);

        setVisible(true);
    }

    private void refreshCounterView(JLabel counterView) {
        counterView.setText(String.valueOf(counter));
    }

    public static void main(String[] args) {
        new CounterApp();
    }
}
