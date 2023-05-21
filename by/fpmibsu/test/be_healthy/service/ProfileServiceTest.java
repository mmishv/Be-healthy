package service;


import by.fpmibsu.be_healthy.entity.Profile;
import by.fpmibsu.be_healthy.entity.Role;
import by.fpmibsu.be_healthy.postgres.DataSource;
import by.fpmibsu.be_healthy.service.ProfileService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class ProfileServiceTest extends ProfileService {

    @Before
    public void init() {
        DataSource.makeTest();
    }

    public List<Profile> getExpectedGetAll() {
        var roles = getAllRoles();
        return Arrays.asList (
                new Profile(2, "Adam", "adamsmith@gmail.com", "adamsmith", "1111",
                        80, 180, 30, 1.1, "мужской", 1, roles.get(0), 2000, 90, 60, 250),
                new Profile(1, "Мария", "pinina7773@gmail.com", "maria", "1111",
                        113, 170, 25, 1.2, "женский", 0.8, roles.get(1), 1800, 90, 60, 250),
                new Profile(3, "Полина", null, "polina", "1111",
                        70, 170, 29, 1.725, "женский", 0.8, roles.get(1), 2000, 90, 60, 250)
        );
    }
    public List<Role> getAllRoles(){
        return Arrays.asList(
                new Role(1, "пользователь"),
                new Role(2, "администратор"));
    }

    public List<Profile> getSomeEntities() {
        var roles = getAllRoles();
        return  Arrays.asList (
                new Profile(1, "Мария", "pinina7773@gmail.com", "maria", "1111",
                        113, 170, 25, 1.2, "женский", 0.8, roles.get(1), 1800, 90, 60, 250),
                new Profile(3, "Полина", null, "polina", "1111",
                        70, 170, 29, 1.725, "женский", 0.8, roles.get(1), 2000, 90, 60, 250));
    }

    @Test
    public void profileGetAllSizePositiveTest() {
        assertEquals(getExpectedGetAll().size(), getAll().size());
    }

    @Test
    public void profileGetAllSizeNegativeTest() {
        assertNotEquals(getSomeEntities().size(), getAll().size());
    }

    @Test
    public void profileGetAllPositiveTest() {
       assertEquals(getExpectedGetAll(), getAll());
    }

    @Test
    public void profileGetAllNegativeTest() {
        assertNotEquals(getSomeEntities(), getAll());
    }

    @Test
    public void profileGetEntityByIdPositiveTest() {
        assertEquals(getExpectedGetAll().get(2), getEntityById(3));
    }

    @Test
    public void profileGetEntityByNonExistentIdPositiveTest() {
        assertNull(getEntityById(10));
    }
    @Test
    public void profileUpdatePositiveTest() {
        var profiles = getExpectedGetAll();
        Profile updateProfile = profiles.get(0),
                beforeUpdateProduct =  profiles.get(0);
        assertTrue(update(updateProfile));
        assertEquals(updateProfile, getEntityById(updateProfile.getId()));
        update(beforeUpdateProduct);
    }

    @Test
    public void profileUpdateNonExistentNegativeTest() {
        var profiles = getExpectedGetAll();
        Profile updateProfile = profiles.get(0);
        updateProfile.setId(100);
        assertFalse(update(updateProfile));
    }

    @Test
    public void profileUpdateNullNegativeTest() {
        assertFalse(update(null));
    }
    @Test
    public void profileUpdateWithNegativeCalorieNormNegativeTest() {
        Profile profile =getExpectedGetAll().get(0);
        var kbju = profile.getKBJU_norm();
        kbju.put("k", BigDecimal.valueOf(-10));
        profile.setKBJU_norm(kbju);
        assertFalse(update(profile));
    }

    @Test
    public void profileUpdateMainPositiveTest() {
        Profile profile =getExpectedGetAll().get(0);
        var oldName = profile.getName();
        profile.setName("adam");
        assertTrue(updateMainInfo(profile));
        profile.setName(oldName);
        updateMainInfo(profile);
    }
    @Test
    public void profileUpdateWithNegativeProteinNormNegativeTest() {
        Profile profile =getExpectedGetAll().get(0);
        var kbju = profile.getKBJU_norm();
        kbju.put("b", BigDecimal.valueOf(-10));
        profile.setKBJU_norm(kbju);
        assertFalse(update(profile));
    }
    @Test
    public void profileUpdateWithNegativeFatsNormNegativeTest() {
        Profile profile =getExpectedGetAll().get(0);
        var kbju = profile.getKBJU_norm();
        kbju.put("j", BigDecimal.valueOf(-10));
        profile.setKBJU_norm(kbju);
        assertFalse(update(profile));
    }
    @Test
    public void profileUpdateWithNegativeCarbNormNegativeTest() {
        Profile profile =getExpectedGetAll().get(0);
        var kbju = profile.getKBJU_norm();
        kbju.put("u", BigDecimal.valueOf(-10));
        profile.setKBJU_norm(kbju);
        assertFalse(update(profile));
    }
    @Test
    public void profileUpdateWithWrongAgeNegativeTest() {
        Profile profile =getExpectedGetAll().get(0);
        profile.setAge(0);
        assertFalse(update(profile));
    }
    @Test
    public void profileUpdateWithWrongWeightNegativeTest() {
        Profile profile =getExpectedGetAll().get(0);
        profile.setWeight(4);
        assertFalse(update(profile));
    }
    @Test
    public void profileUpdateWithWrongHeightNegativeTest() {
        Profile profile = getExpectedGetAll().get(0);
        profile.setHeight(20);
        assertFalse(update(profile));
    }

    @Test
    public void profileUpdateRolePositiveTest() {
        Profile profile = getExpectedGetAll().get(0);
        profile.setRole(getAllRoles().get(0));
        assertTrue(updateRole(profile));
    }
    @Test
    public void profileIsLoginAvailabePositiveTest() {
        assertTrue(isLoginAvailable("11111"));
    }

    @Test
    public void profileIsLoginAvailabeNegativeTest() {
        assertFalse(isLoginAvailable("maria"));
    }
    @Test
    public void profileGetIdByLoginPositiveTest() {
        var profile = getExpectedGetAll().get(0);
        assertEquals(getIdByLogin(profile.getLogin()), profile.getId());
    }

    @Test
    public void profileGetIdByLoginNegativeTest() {
        assertEquals(getIdByLogin("1111"),-1);
    }
    @Test
    public void profileGetPasswordByLoginPositiveTest() {
        var profile = getExpectedGetAll().get(0);
        assertEquals(getPasswordByLogin(profile.getLogin()), profile.getPassword());
    }

    @Test
    public void profileGetPasswordByLoginNegativeTest() {
        assertNull(getPasswordByLogin("bvcb fsdb"));
    }

    @Test
    public void profileCreatePositiveTest() {
        Profile newProfile = getExpectedGetAll().get(0);
        newProfile.setLogin("fijvkewinjv");
        newProfile.setEmail("vvffb");
        assertTrue(create(newProfile));
        newProfile = getLastAdded();
        assertEquals(newProfile, getEntityById(newProfile.getId()));
        delete(newProfile.getId());
    }

    @Test
    public void profileCreateCloneLoginNegativeTest() {
        var newProfile = getExpectedGetAll().get(0);
        newProfile.setEmail("vbvv");
        assertFalse(create(getExpectedGetAll().get(0)));
    }

    @Test
    public void profileCreateCloneEmailNegativeTest() {
        var newProfile = getExpectedGetAll().get(0);
        newProfile.setLogin("vbvv");
        assertFalse(create(getExpectedGetAll().get(0)));
    }
    @Test
    public void profileCreateNullNegativeTest() {
        assertFalse(create(null));
    }
    @Test
    public void profileRegisterNullNegativeTest() {
        assertFalse(register(null, null));
    }
    @Test
    public void profileRegisterCloneLoginNullNegativeTest() {
        assertFalse(register("maria", "1111"));
    }
    @Test
    public void profileRegisterPositiveTest() {
        assertTrue(register("bob7777", "1111"));
        delete(getLastAdded().getId());
    }
    @Test
    public void profileDeletePositiveTest() {
        Profile forDeleteProduct = getExpectedGetAll().get(0);
        forDeleteProduct.setLogin("fijvkewinjv");
        forDeleteProduct.setEmail("vvffb");;
        create(forDeleteProduct);
        forDeleteProduct = getLastAdded();
        assertTrue(delete(forDeleteProduct.getId()));
        assertNull(getEntityById(forDeleteProduct.getId()));
    }

    @Test
    public void profileDeleteNonExistentNegativeTest() {
        assertFalse(delete(100));
    }

    private Profile getLastAdded() {
        for (var t : getAll())
            if (t.getId() > getExpectedGetAll().size()) return t;
        return null;
    }

    @After
    public void tearDown() {
        DataSource.close();
    }
}