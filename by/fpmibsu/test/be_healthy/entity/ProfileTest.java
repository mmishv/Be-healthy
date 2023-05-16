package entity;

import by.fpmibsu.be_healthy.entity.Profile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProfileTest {
    Profile femaleProfile, maleProfile;
    @Before
    public void init() {
        femaleProfile = new Profile();
        femaleProfile.setWeight(80);
        femaleProfile.setHeight(180);
        femaleProfile.setAge(30);
        femaleProfile.setActivity(1.55);
        femaleProfile.setGoal(1);
        femaleProfile.setSex("женский");
        maleProfile = new Profile();
        maleProfile.setWeight(80);
        maleProfile.setHeight(180);
        maleProfile.setAge(30);
        maleProfile.setActivity(1.55);
        maleProfile.setGoal(1);
        maleProfile.setSex("мужской");
    }

    @Test
    public void femaleProfileCalorieRecommendationTest(){
        assertEquals("Female calorie intake is calculated according to the formula", 2499,  femaleProfile.getCalorieRec());
    }
    @Test
    public void femaleProfileProteinsRecommendationTest(){
        assertEquals("Female protein intake is calculated according to the formula", 187,  femaleProfile.getProteinRec());
    }
    @Test
    public void femaleProfileFatsRecommendationTest(){
        assertEquals("Female fats intake is calculated according to the formula", 83,  femaleProfile.getFatRec());
    }
    @Test
    public void femaleProfileCarbsRecommendationTest(){
        assertEquals("Female carbohydrates intake is calculated according to the formula", 249,  femaleProfile.getCarbRec());
    }
    @Test
    public void maleProfileCalorieRecommendationTest(){
        assertEquals("Male calorie intake is calculated according to the formula", 2872,  maleProfile.getCalorieRec());
    }
    @Test
    public void maleProfileProteinsRecommendationTest(){
        assertEquals("Male proteins intake is calculated according to the formula", 215,  maleProfile.getProteinRec());
    }
    @Test
    public void maleProfileFatsRecommendationTest(){
        assertEquals("Male fats intake is calculated according to the formula", 95,  maleProfile.getFatRec());
    }
    @Test
    public void maleProfileCarbsRecommendationTest(){
        assertEquals("Male carbohydrates intake is calculated according to the formula", 287,  maleProfile.getCarbRec());
    }
    @Test
    public void notEnoughDataRecommendationTest(){
        Profile profile =  new Profile();
        femaleProfile.setWeight(80);
        femaleProfile.setActivity(1.55);
        femaleProfile.setGoal(1);
        femaleProfile.setSex("женский");
        assertEquals("Not enough data, should return 0", 0, profile.getCalorieRec());
        assertEquals("Not enough data, should return 0", 0, profile.getProteinRec());
        assertEquals("Not enough data, should return 0", 0, profile.getFatRec());
        assertEquals("Not enough data, should return 0", 0, profile.getCarbRec());
    }
}
