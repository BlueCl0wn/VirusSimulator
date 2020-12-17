import java.util.Arrays;

public class Virus {
    /**
    // Total amount of people as whole number.
    private int amountHab;

    // Amount of healthy people as whole number.
    private int healthyHab;

    // Amount of sick people as whole number.
    private int sickHab;

    // Amount of People that died from disease as whole number.
    private int deadHab;

    // Amount of people with immunity as whole number.
    private int immuneHab;
     */

    // Chance to die from disease as percentage.
    private final double mortality;

    // Chance per contact to get the disease as percentage.
    private final double chance;

    // Number of contacts a person has each day.
    private final double contacts;

    // Number of days the simulation should run.
    private final int days;

    // 2D-Array for saving data. x is day. y is data for that day.
    int[][] Habitants;

    public Virus() {

        this.chance = 0.5;
        this.contacts = 6;
        this.mortality = 0.016;
        this.days = 100;

        this.Habitants = new int[100][5];

        Habitants[0][0] = 10000; // Total amount
        Habitants[0][1] = 10000; // healthy people
        Habitants[0][2] = 0; // sick people
        Habitants[0][3] = 0; // dead people
        Habitants[0][4] = 0; // immune people

        Habitants[1][0] = 10000;
        Habitants[1][1] = 10000;
        Habitants[1][2] = 1;
        Habitants[1][3] = 0;
        Habitants[1][4] = 0;
    }


    public Virus(int amount, int contacts, double chance, int days, double mortality) {
        this.days = 50;

        /**
        this.amountHab = amountHab;
        this.healthyHab = amountHab;
        this.sickHab = 1;
        this.deadHab = 0;
        this.immuneHab = 0;
         */

        this.chance = chance;
        this.contacts = contacts;
        this.mortality = mortality;

        this.Habitants = new int[days][5];

        Habitants[0][0] = amount;
        Habitants[0][1] = amount;
        Habitants[0][2] = 0;
        Habitants[0][3] = 0;
        Habitants[0][4] = 0;

        Habitants[1][0] = amount;
        Habitants[1][1] = amount;
        Habitants[1][2] = 1;
        Habitants[1][3] = 0;
        Habitants[1][4] = 0;
    }

    public static void main(String[] args) {
        Virus Simulation = new Virus();

        Simulation.calculate();
        Simulation.print();

        System.out.println("hello world");
    }

    //hi

    // Calculates the amount of new sick people for a day.
    public int newInfested(int i){
        System.out.println(Habitants[i-1][2] * this.contacts * this.chance * (double)(Habitants[i-1][1]/Habitants[i-1][0]));
        return (int)(Habitants[i-1][2] * this.contacts * this.chance * (Habitants[i-1][1] / Habitants[i-1][0]));
    }

    // Saves and calculates data for all 'days'.
    public void calculate(){
        for(int i = 3; i < days; i++){
            Habitants[i][0] = Habitants[i-1][0]; // New amount of total people (does not change).
            Habitants[i][2] = Habitants[i-1][2] + newInfested(i); // New amount of sick people.
            Habitants[i][1] = Habitants[i-1][1] - newInfested(i); // New amount of healthy people.
            Habitants[i][3] = (int)(Habitants[i-2][2] * mortality); // New amount of dead people.

            Habitants[i][2] -= newInfested(i-2);
            Habitants[i][4] = Habitants[i-1][4] + Habitants[i-2][2]; // New amount of immune people.
        }
    }

    public void print(){
        for(int[] day: Habitants){
            System.out.println(Arrays.toString(day));
        }
    }
}
