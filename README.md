public class LessonSixthTasks {

    public static void main(String[] args) {
    Dog a = new Dog("Барсик");
    a.run(50);
    a.swim(100);

    Cat b = new Cat("Тимофей");
    b.run(50);
    b.swim(50);

    Animal c = new Animal("Лошадь");
    c.run(500);

    System.out.println();
    System.out.println("Всего животных создано: " + Animal.getCount()); // Если учитывать собак и кошек
    System.out.println("Всего собак создано: " + Dog.dogCount);
    System.out.println("Всего котов создано: " + Cat.catCount);
    }

    public static class Animal{
        private String name;
        private static int count;
        public Animal(String name){
            this.name = name;
            count++;
        }
        public Animal(){
            count++;
        }
        public static int getCount() {
            return count;
        }
        public String getName(){
            return name;
        }
        public void run(int distance){
            getName();
            System.out.println("Животное " + name + " пробежало " + distance + " метров");
        }
        public void swim(int distance){
            getName();
            System.out.println("Животное " + name + " проплыло " + distance + " метров");
        }

    }

    public static class Dog extends Animal{
        private int maxRunDistance = 500;
        private int maxSwimDistance = 10;
        private static int dogCount =0;
        public Dog(String name){
            super(name);
            dogCount++;
        }
        @Override
        public void run(int distance){
            if (distance > maxRunDistance) {System.out.println("Собака " + super.getName() + " не может столько пробежать( " +
                    "Собака " + super.getName() + "пробежала " + maxRunDistance + " метров");}
            else {System.out.println("Собака " + super.getName() + " пробежала " + distance + " метров");}
        }

        @Override
        public void swim(int distance){
            if (distance > maxSwimDistance) {System.out.println("Собака " + super.getName() + " не может столько проплыть( " +
                    "Собака " + super.getName() + " проплыла " + maxSwimDistance + " метров");}
            else {System.out.println("Собака " + super.getName() + " проплыла " + distance + " метров");}
        }
    }

    public static class Cat extends Animal{
        private static int catCount =0;
        private int maxRunDistance = 200;
        public Cat(String name){
            super(name);
            catCount++;
        }
        @Override
        public void run(int distance){
            if (distance > maxRunDistance) {System.out.println("Кот " + super.getName() + " не может столько пробежать( " +
                    "Кот " + super.getName() + "пробежал " + maxRunDistance + " метров");}
            else {System.out.println("Кот " + super.getName() + " пробежал " + distance + " метров");}
        }

        @Override
        public void swim(int distance){
            System.out.println("Кот " + super.getName() + " ещё не научился плавать");
        }
    }
}

