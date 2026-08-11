import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;


public class Project{
    
    static List<List<Integer>> map;
    static List<List<Integer>> map_copy;
    static ImageIcon empty = new ImageIcon("sources/empty.jpg");
    static ImageIcon orange_p = new ImageIcon("sources/orange_player.jpg");
    static ImageIcon orange_h = new ImageIcon("sources/orange_house.jpg");
    static ImageIcon orange_s = new ImageIcon("sources/orange_slight.jpg");
    static ImageIcon melon_p = new ImageIcon("sources/melon_player.jpg");
    static ImageIcon melon_h = new ImageIcon("sources/melon_house.jpg");
    static ImageIcon melon_s = new ImageIcon("sources/melon_slight.jpg");
    static ImageIcon batman_p = new ImageIcon("sources/batman_player.jpg");
    static ImageIcon batman_h = new ImageIcon("sources/batman_house.jpg");
    static ImageIcon batman_s = new ImageIcon("sources/batman_slight.jpg");
    static ImageIcon background = new ImageIcon("sources/background.jpg");
    static String player;
    static String gun;
    static int i_limit = 200;
    static int j_limit = 200;
    static int player_icon;
    static int player_house;
    static int player_slight;
    static int enemy0_icon;
    static int enemy0_house;
    static int enemy0_slight;
    static int enemy1_icon;
    static int enemy1_house;
    static int enemy1_slight;
    static int speed;
    static int first_gun_use = 3;
    static int start_time = 0;
    static int second_time = 0;

    static Boolean isInHouse;

    public static void initialize(){

        map = create_raw_map();
        map_copy = create_raw_map();
        if(player == "Orange"){
            player_icon = 2;
            player_house = 4;
            player_slight = 3;
            enemy0_icon = 5;
            enemy0_house = 7;
            enemy0_slight = 6;
            enemy1_icon = 8;
            enemy1_house = 10;
            enemy1_slight = 9;
        }else if(player == "melon"){
            player_icon = 5;
            player_house = 7;
            player_slight = 6;
            enemy0_icon = 2;
            enemy0_house = 4;
            enemy0_slight = 3;
            enemy1_icon = 8;
            enemy1_house = 10;
            enemy1_slight = 9;
        }else if(player == "batman"){
            player_icon = 8;
            player_house = 10;
            player_slight = 9;
            enemy0_icon = 2;
            enemy0_house = 4;
            enemy0_slight = 3;
            enemy1_icon = 5;
            enemy1_house = 7;
            enemy1_slight = 6;
        }
        isInHouse = false;

        map.get(101).set(100,player_house);
        map.get(101).set(101,player_house);
        map.get(101).set(102,player_house);
        map.get(102).set(100,player_house);
        map.get(102).set(101,player_house);
        map.get(102).set(102,player_house);
        map.get(103).set(100,player_house);
        map.get(103).set(101,player_house);
        map.get(103).set(102,player_house);

        map.get(94).set(101,enemy0_icon);
        map.get(94).set(102,enemy0_slight);
        map.get(94).set(103,enemy0_slight);
        map.get(94).set(104,enemy0_slight);
        map.get(93).set(105,enemy0_house);
        map.get(94).set(105,enemy0_house);
        map.get(95).set(105,enemy0_house);
        map.get(93).set(106,enemy0_house);
        map.get(94).set(106,enemy0_house);
        map.get(95).set(106,enemy0_house);

        map.get(74).set(101,enemy1_icon);
        map.get(74).set(102,enemy1_slight);
        map.get(74).set(103,enemy1_slight);
        map.get(74).set(104,enemy1_slight);
        map.get(73).set(105,enemy1_house);
        map.get(74).set(105,enemy1_house);
        map.get(75).set(105,enemy1_house);
        map.get(73).set(106,enemy1_house);
        map.get(74).set(106,enemy1_house);
        map.get(75).set(106,enemy1_house);

    }

    public static List<List<Integer>> create_raw_map(){

        // This function returns raw 2D map filled with zeros

        List<List<Integer>> raw_map = new ArrayList<List<Integer>>();

        for(int i=0; i<i_limit; i++){

            ArrayList<Integer> row = new ArrayList<>();
            for(int j=0; j<j_limit; j++){
                row.add(0);
            }
            raw_map.add(row);
        }
        return raw_map;
    }

    public static ArrayList<ArrayList<JLabel>> create_labels(int rows, int cols) {
        ArrayList<ArrayList<JLabel>> labels = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            ArrayList<JLabel> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(new JLabel());
            }
            labels.add(row);
        }
        return labels;
    }

    public static void expand(String expandDirection){
        
        if(expandDirection == "Right"){
            for(int i=0; i<i_limit; i++){
                for(int j=0; j<10; j++){
                    map.get(i).add(0);
                }
            }
            j_limit += 10;

        }else{
            for(int i=0; i<10; i++){
                ArrayList<Integer> new_rows = new ArrayList<>();
                for(int j=0; j<j_limit; j++){
                    new_rows.add(0);
                }
                map.add(new_rows);
            }
            i_limit += 10;
        }
    }

    public static void print_map_graphical(List<List<Integer>> map, JFrame frame,
                                           List<ArrayList<JLabel>> labels){
        
        int rows = map.size();
        int cols = map.get(0).size();
        ImageIcon temp = null;

        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){

                if(map.get(i).get(j) == 0){       temp = empty;}
                else if(map.get(i).get(j) == 2){  temp = orange_p;}
                else if(map.get(i).get(j) == 3){  temp = orange_s;}
                else if(map.get(i).get(j) == 4){  temp = orange_h;}
                else if(map.get(i).get(j) == 5){  temp = melon_p;}
                else if(map.get(i).get(j) == 6){  temp = melon_s;}
                else if(map.get(i).get(j) == 7){  temp = melon_h;}
                else if(map.get(i).get(j) == 8){  temp = batman_p;}
                else if(map.get(i).get(j) == 9){  temp = batman_s;}
                else if(map.get(i).get(j) == 10){  temp = batman_h;}

                labels.get(i).get(j).setBounds(j * 70, i * 70, 70, 70);
                labels.get(i).get(j).setIcon(temp);
                frame.getContentPane().add(labels.get(i).get(j));
            }
        }
    }

    public static List<List<Integer>> sliceSqaure2D(int iStart, int iEnd,
                                        int jStart, int jEnd){

        // This function returns a Square slice of the given 2D array

        List<List<Integer>> slice = new ArrayList<List<Integer>>();

        for(int i=iStart; i<iEnd; i++){

            if(i == i_limit){
                expand("Right");
            }
            
            ArrayList<Integer> temp = new ArrayList<Integer>();

            for(int j=jStart; j<jEnd; j++){

                if(i == i_limit){
                expand("Down");
                }
                temp.add(map.get(i).get(j));
            }
            slice.add(temp);
            
        }
        return slice;
    }

    public static void addEnclosedHouse(){


        for(int i=1; i<199; i++){
            for(int j=1, counter=0; j<199; j++, counter=0){

                if(map.get(i-1).get(j) == player_house)
                    counter++;

                if(map.get(i).get(j-1) == player_house)
                    counter++;

                if(map.get(i+1).get(j) == player_house)
                    counter++;

                if(map.get(i).get(j+1) == player_house)
                    counter++;

                if(counter >= 3)
                    map.get(i).set(j, player_house);

            }
        }
    }

    public static void terminate_enemy(int house, int icon, int slight){          /////////////////////
 
        for(int i=0; i<200; i++){
            for(int j=0; j<200; j++){
                if(map.get(i).get(j) == house ||
                   map.get(i).get(j) == icon ||
                   map.get(i).get(j) == slight)

                map.get(i).set(j, map_copy.get(i).get(j));
            }
        }
    }

    public static void assignPlayer(){

        JFrame frame = new JFrame();
        frame.setSize(560,597);
        frame.setVisible(true);
        frame.setLayout(null);

        JButton Orange = new JButton();
        Orange.setBounds(120, 350, 70, 70);
        frame.add(Orange);
        Orange.setIcon(orange_p);

        JButton Melon = new JButton();
        Melon.setBounds(271, 350, 70, 70);
        frame.add(Melon);
        Melon.setIcon(melon_p);

        JButton Batman = new JButton();
        Batman.setBounds(422, 350, 70, 70);
        frame.add(Batman);
        Batman.setIcon(batman_p);

        JLabel text = new JLabel();
        text.setText("Select player:");
        text.setBounds(250, 250, 400, 40);
        frame.add(text);

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, 560, 597);
        frame.add(backgroundLabel);
        backgroundLabel.setIcon(background);

        while(player == null) {
            Orange.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    player = "orange";
                    frame.dispose();
                    return;
                }
            });

            Melon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    player = "melon";
                    frame.dispose();
                    return;
                }
            });

            Batman.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    player = "batman";
                    frame.dispose();
                    return;
                }
            });
        }
    }

    public static void assignSpeed(){

        JFrame frame = new JFrame();
        frame.setSize(560,597);
        frame.setVisible(true);
        frame.setLayout(null);

        JButton slow = new JButton();
        slow.setBounds(100, 350, 100, 70);
        frame.add(slow);
        slow.setText("Slow");

        JButton normal = new JButton();
        normal.setBounds(251, 350, 100, 70);
        frame.add(normal);
        normal.setText("Normal");

        JButton fast = new JButton();
        fast.setBounds(402, 350, 100, 70);
        frame.add(fast);
        fast.setText("Fast");

        JLabel text = new JLabel();
        text.setText("Select speed:");
        text.setBounds(250, 250, 400, 40);
        frame.add(text);

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, 560, 597);
        frame.add(backgroundLabel);
        backgroundLabel.setIcon(background);

        while(speed == 0) {
            slow.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    speed = 1500;
                    frame.dispose();
                    return;
                }
            });

            normal.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    speed = 1100;
                    frame.dispose();
                    return;
                }
            });

            fast.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    speed = 600;
                    frame.dispose();
                    return;
                }
            });
        }
    }

    public static void addHouse(int row, int col) {


        for(int i=row-1; i<=row+1; i++){
            for(int j=col-1; j<=col+1; j++){
                if(map.get(i).get(j) == player_slight){
                    map.get(i).set(j, player_house);
                    addHouse(i, j);
                }
            }
        }
        return;


    }

    public static void first_gun(int row, int col){

        for(int i=row-1; i< row+2; i++){
            for(int j=col-1; j< col+2; j++){

                if(map.get(i).get(j) == enemy0_slight ||
                   map.get(i).get(j) == enemy0_house ||
                   map.get(i).get(j) == enemy0_icon){
                    terminate_enemy(enemy0_house, enemy0_icon, enemy0_slight);
                }
                else if(map.get(i).get(j) == enemy1_slight ||
                        map.get(i).get(j) == enemy1_house ||
                        map.get(i).get(j) == enemy1_icon){
                    terminate_enemy(enemy1_house, enemy1_icon, enemy1_slight);
                }
                map.get(i).set(j, player_house);
            }
        }
    }

    public static void second_gun(int row, int col, String direction){

        switch(direction){
            case "Up":{
                for(int i=row; i>0; i--){
                    if(map.get(i).get(col) == enemy1_icon){
                        terminate_enemy(enemy1_house, enemy1_icon, enemy1_slight);
                    }else if(map.get(i).get(col) == enemy0_icon){
                        terminate_enemy(enemy0_house, enemy0_icon, enemy0_slight);
                    }
                }
            }
            case "Down":{
                for(int i=row; i<200; i++){
                    if(map.get(i).get(col) == enemy1_icon){
                        terminate_enemy(enemy1_house, enemy1_icon, enemy1_slight);
                    }else if(map.get(i).get(col) == enemy0_icon){
                        terminate_enemy(enemy0_house, enemy0_icon, enemy0_slight);
                    }
                }
            }
            case "Right":{
                for(int j=col; j<200; j++){
                    if(map.get(row).get(j) == enemy1_icon){
                        terminate_enemy(enemy1_house, enemy1_icon, enemy1_slight);
                    }else if(map.get(row).get(j) == enemy0_icon){
                        terminate_enemy(enemy0_house, enemy0_icon, enemy0_slight);
                    }
                }
            }
            case "Left":{
                for(int j=col; j>0; j--){
                    if(map.get(row).get(j) == enemy1_icon){
                        terminate_enemy(enemy1_house, enemy1_icon, enemy1_slight);
                    }else if(map.get(row).get(j) == enemy0_icon){
                        terminate_enemy(enemy0_house, enemy0_icon, enemy0_slight);
                    }
                }
            }
        }

    }

    public static KeyListener change_state(DirectionWrapper wrapper){
        return new KeyListener(){
            
               @Override
               public void keyPressed(KeyEvent e) {
                   if(e.getKeyCode() == KeyEvent.VK_UP){
                       wrapper.direction = "Up";
                   }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                       wrapper.direction = "Down";
                   }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                       wrapper.direction = "Right";
                   }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                       wrapper.direction = "Left";
                   }else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                       gun = "1";
                   }else if(e.getKeyCode() == KeyEvent.VK_SPACE){
                       gun = "2";
                   }
               }

               @Override
               public void keyTyped(KeyEvent e) {
               }

               @Override
               public void keyReleased(KeyEvent e) {
               }
            };
    }

    public static void main(String[] args){

        assignPlayer();
        assignSpeed();
        initialize();
        List<ArrayList<JLabel>> labels = create_labels(8, 9);
        DirectionWrapper wrapper = new DirectionWrapper();

        JFrame frame = new JFrame();
        frame.setSize(560,597);
        frame.setVisible(true);

        int i=100;
        int j=100;


        while(true){

            if(isInHouse){
                    map.get(i).set(j, player_house);
                    addHouse(i, j);
                    addEnclosedHouse();
            }
            else
                map.get(i).set(j, player_slight);

            if(wrapper.direction == "Up"){

                if(gun == "1" && first_gun_use > 0){
                    gun = null;
                    first_gun_use--;
                    first_gun(i-5, j);
                }

                if(gun == "2" && second_time - start_time > 3000){
                    second_time = 0;
                    second_gun(i,j, wrapper.direction);
                }

                if(map.get(i-1).get(j) == player_house){
                    isInHouse = true;
                }
                else if(map.get(i-1).get(j) == enemy0_slight){
                    terminate_enemy(enemy0_house, enemy0_icon, enemy0_slight);
                }
                else if(map.get(i-1).get(j) == enemy1_slight){
                    terminate_enemy(enemy1_house, enemy1_icon, enemy1_slight);
                }
                else if(map.get(i-1).get(j) == enemy0_icon || map.get(i-1).get(j) == enemy1_icon){
                    System.out.println("Game over!");
                    return;
                }
                else{
                    isInHouse = false;
                }

                i--;
            }
            else if(wrapper.direction == "Down"){

                if(gun == "1" && first_gun_use > 0){
                    gun = null;
                    first_gun_use--;
                    first_gun(i+5, j);
                }

                if(map.get(i+1).get(j) == player_house){
                    isInHouse = true;
                }
                else if(map.get(i+1).get(j) == enemy0_slight){
                    terminate_enemy(enemy0_house, enemy0_icon, enemy0_slight);
                }
                else if(map.get(i+1).get(j) == enemy1_slight){
                    terminate_enemy(enemy1_house, enemy1_icon, enemy1_slight);
                }
                else if(map.get(i+1).get(j) == enemy0_icon || map.get(i-1).get(j) == enemy1_icon){
                    System.out.println("Game over!");
                    return;
                }
                else{
                    isInHouse = false;
                }

                i++;
            }
            else if(wrapper.direction == "Right"){

                if(gun == "1" && first_gun_use > 0){
                    gun = null;
                    first_gun_use--;
                    first_gun(i, j+5);
                }

                if(map.get(i).get(j+1) == player_house){
                    isInHouse = true;
                }
                else if(map.get(i).get(j+1) == enemy0_slight){
                    terminate_enemy(enemy0_house, enemy0_icon, enemy0_slight);
                }
                else if(map.get(i).get(j+1) == enemy1_slight){
                    terminate_enemy(enemy1_house, enemy1_icon, enemy1_slight);
                }
                else if(map.get(i).get(j+1) == enemy0_icon || map.get(i-1).get(j) == enemy1_icon){
                    System.out.println("Game over!");
                    return;
                }
                else{
                    isInHouse = false;
                }
                
                j++;
            }
            else{

                if(gun == "1" && first_gun_use > 0){
                    gun = null;
                    first_gun_use--;
                    first_gun(i, j-5);
                }

                if(map.get(i).get(j-1) == player_house){
                    isInHouse = true;
                }
                else if(map.get(i).get(j-1) == enemy0_slight){
                    terminate_enemy(enemy0_house, enemy0_icon, enemy0_slight);
                }
                else if(map.get(i).get(j-1) == enemy1_slight){
                    terminate_enemy(enemy1_house, enemy1_icon, enemy1_slight);
                }
                else if(map.get(i).get(j-1) == enemy0_icon || map.get(i-1).get(j) == enemy1_icon){
                    System.out.println("Game over!");
                    return;
                }
                else{
                    isInHouse = false;
                }

                j--;
            }

            map.get(i).set(j, player_icon);


            List<List<Integer>> slice = sliceSqaure2D(i-3, i+5, j-4, j+5);

            print_map_graphical(slice, frame, labels);

            frame.addKeyListener(change_state(wrapper));

            try {
                Thread.sleep(speed);
                second_time += speed;
            } catch (InterruptedException e) {}
        }
    }
    
}

class DirectionWrapper {
    public String direction = "Up";
}

// Approach:
// 1 - create a map in CLI              ✓
// 2 - make it graphical and slicing    ✓
// 3 - create time stamp                ✓
// 4 - updating graphic with time stamp ✓
// 5 - agent creating                   ✓
// 6 - game logic                       ✓
