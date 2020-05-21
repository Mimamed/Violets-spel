import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel_Game extends Panel
{

    static JButton button1 = new JButton(), button2 = new JButton(), button3 = new JButton(), button4 = new JButton();
    static boolean youAnswered = false, heAnswered, iBegin;
    static String answer, hisAnswer;
    static JLabel winLabel = new JLabel();

    Panel_Game()
    {
        startUp();
        setup();
    }

    public void setup()
    {
        this.add(button4);
        this.add(button3);
        this.add(button2);
        this.add(button1);
        this.add(winLabel);

        winLabel.setBounds(200, 10, 100, 30);
        button4.addActionListener(new GoBack());
        button4.setText("Go Back");
        button4.setBackground(Color.RED);
        button4.setBounds(220, 400, 60, 25);
        button4.setFocusPainted(false);
        button4.setMargin(new Insets(0,0,0,0));

        button3.setBounds(5, 50, 150, 150);
        button3.setFocusPainted(false);
        button3.setMargin(new Insets(0,0,0,0));
        button3.setBackground(Color.BLUE);
        button3.setText("Stone");
        button3.addActionListener(new Stone());

        button2.setBounds(166, 50, 150, 150);
        button2.setFocusPainted(false);
        button2.setMargin(new Insets(0,0,0,0));
        button2.setBackground(Color.ORANGE);
        button2.setText("Sizer");
        button2.addActionListener(new Sizer());

        button1.setBounds(327, 50, 150, 150);
        button1.setFocusPainted(false);
        button1.setMargin(new Insets(0,0,0,0));
        button1.setBackground(Color.GREEN);
        button1.setText("Paper");
        button1.addActionListener(new Paper());
    }

    private static void winOrLose(boolean win)
    {
        if (win)
        {
            winLabel.setText("You won");
        }
        else
        {
            winLabel.setText("You Lost");
        }
    }

    public static void uppdateTurns(boolean myTurn)
    {
        if (myTurn)
        {
            button3.setEnabled(true);
            button2.setEnabled(true);
            button1.setEnabled(true);
        }
        else
        {
            button3.setEnabled(false);
            button2.setEnabled(false);
            button1.setEnabled(false);
        }
    }

    public static void gotAnswer(String choice)
    {

        if (iBegin == true || youAnswered) // se till att answered börjar false
        {
            hisAnswer = choice;
            if (answer.equals(choice))
            {
                //ingen får poäng, inget händer
                winLabel.setText("Equal");
            }
            else if (answer.equals("Stone") && choice.equals("paper"))
            {
                winOrLose(false);
            }
            else if (answer.equals("Stone") && choice.equals("Sizer"))
            {
                winOrLose(true);
            }
            else if (answer.equals("Paper") && choice.equals("Stone"))
            {
                winOrLose(true);
            }
            else if (answer.equals("Paper") && choice.equals("Sizer"))
            {
                winOrLose(false);
            }
            else if (answer.equals("Sizer") && choice.equals("Stone"))
            {
                winOrLose(false);
            }
            else if (answer.equals("Sizer") && choice.equals("Paper"))
            {
                winOrLose(true);
            }

            if (youAnswered)
            {
                uppdateTurns(false);
            }
        }
        else
        {
            hisAnswer = choice;
            uppdateTurns(true);
        }
    }

    private static class GoBack implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Main.window.changePanel(Main.window.Menu);
            Main.connection.interrupt();
        }
    }

    private static class Stone implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            answer = "Stone";
            youAnswered = true;
            if (!iBegin)
            {
                gotAnswer(hisAnswer);
                Main.network.sendOrReadMessage(answer, false);
            }
            else
            {
                Main.network.sendOrReadMessage(answer, false);
                Main.network.sendOrReadMessage(null, true);
            }
            uppdateTurns(false);
        }
    }

    private static class Sizer implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            answer = "Sizer";
            if (!iBegin)
            {
                youAnswered = true;
                gotAnswer(hisAnswer);
            }
            else
            {
                Main.network.sendOrReadMessage(answer, false);
                Main.network.sendOrReadMessage(null, true);
            }
            uppdateTurns(false);
        }
    }


    private static class Paper implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            answer = "Paper";
            if (!iBegin)
            {
                youAnswered = true;
                gotAnswer(hisAnswer);
            }
            else
            {
                Main.network.sendOrReadMessage(answer, false);
                Main.network.sendOrReadMessage(null, true);
            }
            uppdateTurns(false);
        }
    }


}
