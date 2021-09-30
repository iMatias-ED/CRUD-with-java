package clase_13;

import java.lang.reflect.Array;

class TicTacToeLogic{
    private String player = "X";
    Array winnerMovements[];

    public void changePlayer(){
        if (player.equals("X")){player="O";}
        else{player="X";}
    }
    public String getPlayer(){
        return player;
    }

    public Boolean winnerControl(String[] movements){
        String a1 = movements[0];
        String a2 = movements[1];
        String a3 = movements[2];
        String b1 = movements[3];
        String b2 = movements[4];
        String b3 = movements[5];
        String c1 = movements[6];
        String c2 = movements[7];
        String c3 = movements[8];
  
// Horizontal movements
        if (a1.equals(player) && a2.equals(player) && a3.equals(player)){
            System.out.println("Gano:" + player);
            return true;
        }
        if (b1.equals(player) && b2.equals(player) && b3.equals(player)){
            System.out.println("Gano:" + player);
            return true;
        }
        if (c1.equals(player) && c2.equals(player) && c3.equals(player)){
            System.out.println("Gano:" + player);
            return true;
        }

//vertical movements
        if (a1.equals(player) && b1.equals(player) && c1.equals(player)){
            System.out.println("Gano:" + player);
            return true;
        }
        if (a2.equals(player) && b2.equals(player) && c2.equals(player)){
            System.out.println("Gano:" + player);
            return true;
        }
        if (a3.equals(player) && b3.equals(player) && c3.equals(player)){
            System.out.println("Gano:" + player);
            return true;
        }
//diagonal movements
        if (a1.equals(player) && b2.equals(player) && c3.equals(player)){
            System.out.println("Gano:" + player);
            return true;
        }
        if (a3.equals(player) && b2.equals(player) && c1.equals(player)){
            System.out.println("Gano:" + player);
            return true;
        }
        return false;
    }



}
