import java.lang.Math;

public class Person {
    // The time a person has been sick for.
    private int time;

    // How long a person will be sick for.
    private int length;

    // Is a person sick?
    private boolean sick;

    //is a person immune?
    private boolean immune;

    // coming soon --- Risk of dying from disease.

    public Person(int length){
        this.time = 0;
        this.sick = false;
        this.immune = false;
        this.length = length;
    }

    public void addDay(){
        time += 1;

        if(time >= length){
            sick = false;
            immune = true;
        }
    }
}
