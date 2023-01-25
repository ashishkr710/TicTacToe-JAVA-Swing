package MyGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class MyGame extends JFrame implements ActionListener {
    JLabel heading,clockLabel;
    Font font=new Font("",Font.BOLD,40);
    JPanel mainPanel;
    JButton [] buttons =new  JButton[9];

    int[] gameChances ={2,2,2,2,2,2,2,2,2};
    int activePlayer=0;

    int[][] wps ={
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6}
    };

    int winner=2;

    boolean gameOver = false;

    public MyGame(){
        System.out.println("Constractor");
        setTitle("TIc Tac Toe");
        setSize(850,850);
        ImageIcon icon= new ImageIcon("src/Img/1073293.png");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(icon.getImage());
        GUI();
        setVisible(true);
    }

    private void GUI(){
        this.getContentPane().setBackground(Color.CYAN);
        this.setLayout(new BorderLayout());
        heading=new JLabel("Tic Tak Toe");
        heading.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.WHITE);
        this.add(heading,BorderLayout.NORTH);
//      Clock
        clockLabel = new JLabel("clock");
        clockLabel.setFont(font);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clockLabel.setForeground(Color.WHITE);
        this.add(clockLabel,BorderLayout.SOUTH);

        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    while (true){
                        String datetime = new Date().toLocaleString();
                        clockLabel.setText(datetime);
                        Thread.sleep(1000);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        t.start();
//        panel
        mainPanel=new JPanel();
        mainPanel.setLayout(new GridLayout(3,3));
        for(int i=1; i<=9; i++){
            JButton btn=new JButton();
            btn.setBackground(Color.CYAN);
            btn.setFont(font);
            mainPanel.add(btn);
            buttons[i - 1] = btn;
            btn.addActionListener(this);
            btn.setName(String.valueOf(i-1));
        }
        this.add(mainPanel,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton currentButton =(JButton)e.getSource();

       String nameStr = currentButton.getName();

       int name = Integer.parseInt(nameStr.trim());

       if(gameOver){
           JOptionPane.showMessageDialog(this,"Game Already Over..");
           return;
       }

       if (gameChances[name]==2){
           if (activePlayer==1){
               currentButton.setIcon(new ImageIcon("src/img/o.png"));

               gameChances[name] = activePlayer;
               activePlayer=0;
           }else {
               currentButton.setIcon(new ImageIcon("src/img/x.png"));
                gameChances[name] = activePlayer;
               activePlayer=1;
           }
            for(int []temp:wps){
                if ((gameChances[temp[0]]==gameChances[temp[1]]) && (gameChances[temp[1]]==gameChances[temp[2]]) && gameChances[temp[2]]!=2)
                {
                    System.out.println("wps");
                    winner=gameChances[temp[0]];
                    gameOver=true;
                    JOptionPane.showMessageDialog(null,"Player" + winner + " has won the game..");
                   int i = JOptionPane.showConfirmDialog(this,"DO YOU WANT TO PLAY MORE ?");
                   if (i==0){
                       this.setVisible(false);
                       new MyGame();
                   }else if (i==1){
                       System.exit(1234);
                   }else {
                       System.out.println(" ");
                   }
                    break;
                }
            }
//            Draw Logic
            int c = 0;

            for (int x:gameChances)
            {
                if (x==2)
                {
                    c++;
                    break;
                }
            }
            if (c==0&&gameOver==false){
                JOptionPane.showMessageDialog(null,"Its Draw..");

                int i = JOptionPane.showConfirmDialog(this,"Play more..");
                if (i==0)
                {
                    this.setVisible(false);
                    new MyGame();
                }
                else if(i==1)
                {
                    System.exit(1234);
                }
                else
                {
                    System.out.println("else");
                }
                gameOver=true;
            };
       }
       else
       {
           JOptionPane.showMessageDialog(this,"position Already occupied ");
           System.out.println("Exist");
       }
    }
}
