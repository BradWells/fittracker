package globalindustryinc.fittrackr;

public class FoodItem {

    String name;
    int calories;
    double fat;
    int carbs;

    public FoodItem(String name, int calories, double fat, int carbs){
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
    }

    public FoodItem() {

    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int newCalories) {
        calories = newCalories;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double newFat) {
        fat = newFat;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int newCarbs) {
        carbs = newCarbs;
    }

}
