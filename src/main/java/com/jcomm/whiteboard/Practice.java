package com.jcomm.whiteboard;

import java.util.Stack;

/**
 * Created by jova on 4/21/15.
 */
public class Practice {





    public static void isNested(String arg[]){

        char [] p = { '(','(','(', ')', '(', ')', ')', '(', ')'};
        Stack<Character> openP = new Stack<>();
        boolean malformed;

        int count = 0;

        for(char c : p){

            if(c=='('){
                count++;
                openP.push(c);
            }else if(c==')'){
                count--;
                if(openP.isEmpty()) {
                    System.out.println("the " + count + " char is malformed");
                    malformed = true;
                }

                openP.pop();
            }
        }

        if(!openP.isEmpty()){
            System.out.println("the " + count + " char is malformed");
            malformed = true;
        }

        malformed = false;



        System.out.println("Is malformed "+malformed);
    }



    public static void main(String arg[]){



    }


    public void reverseLinkedList(){

        PracticeNode pn1 = new PracticeNode();

        PracticeNode pn2 = new PracticeNode();

        PracticeNode pn3 = new PracticeNode();

        PracticeNode pn4  = new PracticeNode();


        pn1.setX(4);


        pn2.setX(8);

        pn3.setX(1);

        pn4.setX(898);



    }

    private class PracticeNode{



        private Integer x = new Integer(0);
        private PracticeNode next = null;


        public Integer getX() {
            return x;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        



    }
}
