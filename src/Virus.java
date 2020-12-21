import java.util.Arrays;

public class Virus {
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

    // Amount of people in community.
    int startAmount;

    // Amount of people bringing the disaese into the community.
    int startSick;

    public Virus() {

        this.chance = 0.4;
        this.contacts = 6.0;
        this.mortality = 0.016;
        this.days = 100;

        this.startAmount = 1000000;
        this.startSick = 1;

        this.Habitants = new int[100][5];

        Habitants[0][0] = startAmount; // Total amount
        Habitants[0][1] = startAmount; // healthy people
        Habitants[0][2] = 0; // sick people
        Habitants[0][3] = 0; // dead people
        Habitants[0][4] = 0; // immune people

        Habitants[1][0] = startAmount;
        Habitants[1][1] = startAmount;
        Habitants[1][2] = 0;
        Habitants[1][3] = 0;
        Habitants[1][4] = 0;

        Habitants[2][0] = startAmount;
        Habitants[2][1] = startAmount - startSick;
        Habitants[2][2] = startSick;
        Habitants[2][3] = 0;
        Habitants[2][4] = 0;
    }


    public Virus(int amount, int contacts, double chance, int days, double mortality) {
        this.days = 50;

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
    }

    //hi

    // Calculates the amount of new sick people for a day.
    public int newInfested(int i){
        System.out.print(i + "= \t" + Habitants[i - 1][2] + " * " + this.contacts + " * " + this.chance + " * " + (Habitants[i - 1][1] / (double)Habitants[i-1][0]) + " = \t");
        System.out.println((int)(Habitants[i - 1][2] * this.contacts * this.chance * (Habitants[i - 1][1]/(double)Habitants[i - 1][0]) + 0.5));
        return (int)((Habitants[i - 1][2] * this.contacts * this.chance * (Habitants[i - 1][1] / (double)Habitants[i - 1][0])) + 0.5);
    }

    // Saves and calculates data for all 'days'.
    public void calculate(){
        for(int i = 3; i < days; i++){
            Habitants[i][0] = Habitants[i - 1][0]; // New amount of total people (does not change).

            Habitants[i][2] = newSick(i); // New amount of sick people.

            Habitants[i][1] = newHealthy(i); // New amount of healthy people.

            Habitants[i][3] = newDead(i); // New amount of dead people.

            Habitants[i][4] = newImmune(i); // New amount of immune people.
        }
    }


    // New Amount of healthy people on day 'i'.
    public int newHealthy(int i){
        System.out.println("komishce leute: " + (Habitants[i - 1][2] - newInfested(i)));
        return Math.max(Habitants[i - 1][1] - newInfested(i), 0);
    }

    // New amount of sick people on day 'i'.
    public int newSick(int i){
        return Habitants[i - 1][2] + newInfested(i) - (Habitants[i - 2][2] - Habitants[i - 3][2]);
    }

    public int newDead(int i){
        return 0;
        //(int)((Habitants[i - 2][2] - Habitants[i - 3][2]) * this.mortality);
    }

    // New amount of immune people on day 'i'.
    public int newImmune(int i){
        return Habitants[i - 1][4] + Habitants[i - 2][2] - Habitants[i - 3][2];
    }


    public void print(){
        for(int[] day: Habitants){
            System.out.println(Arrays.toString(day));
        }
    }
}
