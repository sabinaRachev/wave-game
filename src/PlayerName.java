import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerName extends JFrame {
    private JPanel Jpanel;
    private JTextField txtInput;
    private JLabel title;
    private JButton button;
    private JButton backButton;
    private String playerName;
    private Game game;

    public PlayerName(String title, Game game) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(Jpanel);
        this.getContentPane().setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(300,200));
        this.setLocationRelativeTo(null);
        txtInput.setPreferredSize(new Dimension(30,30));
        button.setPreferredSize(new Dimension(25,30));
        button.setBackground(Color.GRAY);
        backButton.setBackground(Color.GRAY);
        this.pack();
        this.game = game;
        playerName = null;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              setPlayerName();
                if (validInput()) {
                    game.gameState = Game.STATE.Select;
                    txtInput.setText("");
                }
                else  JOptionPane.showMessageDialog(null, "invalid input!","Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public  void setPlayerName() {
        if (!txtInput.getText().equals("")) {
          playerName=txtInput.getText();
        }
    }
    public String getPlayerName(){
        return playerName;
    }
    public boolean validInput(){
         if (playerName==null)
             return false;
         else {
             if (playerName.length()>10)
                 return false;
             for (int i=0;i<playerName.length();i++){
                 char curr=playerName.charAt(i);
                 if ((curr<'a'||curr>'z')&&(curr<'A'||curr>'Z')&&(curr<'1'||curr>'9'))
                     return false;
             }
         }
       return true;
    }
}
