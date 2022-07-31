package com.jcomm.designpatterns.composite;

public class TestCompositeDesignPattern {

    public static void main(String[] args) {
       MenuComponent pancakeHouseMenu = new Menu("PANCAKE HOUSE MENU", "Breakfast");
       MenuComponent dinerMenu = new Menu("DINER MENU", "Lunch");
       MenuComponent cafeMenu = new Menu("CAFE MENU", "Dinner");
       MenuComponent dessertMenu = new Menu("DESSERT Menu", "Dessert of course!");

       MenuComponent allMenus = new Menu("ALL MENUS", "ALL menus combined");
       pancakeHouseMenu.add(new MenuItem("K&B's Pancake Breakfast",
               "Pancakes with scrambled eggs, and toast",
               true,
               2.99));

        pancakeHouseMenu.add(new MenuItem("Regular Pancake Breakfast",
                "Pancakes with fried eggs, sausage",
                true,
                3.49));
        pancakeHouseMenu.add(new MenuItem("Waffles",
                "Waffles, with your choice of blueberries or strawberries",
                true,
                3.59));
       allMenus.add(pancakeHouseMenu);
       allMenus.add(dinerMenu);


       cafeMenu.add(new MenuItem("BLT",
               "Bacon with lettuce & tomato on whole wheat", false, 2.99));
       cafeMenu.add(new MenuItem("Soup of the day",
                "Soup of the day, with a side of potato salad", false, 3.29));
        cafeMenu.add(new MenuItem("Hotdog",
                "A hot dog, with saurkraut, relish, onions, topped with cheese", false, 3.05));
       allMenus.add(cafeMenu);

       dinerMenu.add(new MenuItem("Pasta", "Spaghetti with Marinara Sauce, and a slice of sourdough bread",
               true,
               3.89));
       dinerMenu.add(new MenuItem("BLT",
               "Bacon with lettuce & tomato on whole wheat", false, 2.99));
       allMenus.add(dessertMenu);


        dessertMenu.add(new MenuItem("Apple Pie", "Apple pie with a flakey crust, topped with vanilla icecream",
                true,
                1.59));
        Waitress waitress = new Waitress(allMenus);

        waitress.getVegetarain();
    }
}
