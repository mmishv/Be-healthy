package service;

import by.fpmibsu.be_healthy.entity.Role;
import by.fpmibsu.be_healthy.postgres.DataSource;
import by.fpmibsu.be_healthy.service.RoleService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class RoleServiceTest extends RoleService {

    @Before
    public void init() {
        DataSource.makeTest();
    }

    public List<Role> getExpectedGetAll() {
        return Arrays.asList(
                new Role(2, "администратор"),
                new Role(1, "пользователь"));
    }

    public List<Role> getSomeEntities() {
        return List.of(new Role(2, "обычный пользователь"));
    }

    @Test
    public void roleGetAllSizePositiveTest() {
        assertEquals(getExpectedGetAll().size(), getAll().size());
    }

    @Test
    public void roleGetAllSizeNegativeTest() {
        assertNotEquals(getSomeEntities().size(), getAll().size());
    }

    @Test
    public void roleGetAllPositiveTest() {
        assertEquals(getExpectedGetAll(), getAll());
    }

    @Test
    public void roleGetAllNegativeTest() {
        assertNotEquals(getSomeEntities(), getAll());
    }

    @Test
    public void roleGetEntityByIdPositiveTest() {
        assertEquals(new Role(1, "пользователь"), getEntityById(1));
    }

    @Test
    public void roleGetEntityByNonExistentIdPositiveTest() {
        assertNull(getEntityById(10));
    }

    @Test
    public void roleUpdatePositiveTest() {
        Role updateRole = new Role(1, "обычный пользователь"),
                beforeUpdateRole = new Role(1, "пользователь");
        assertTrue(update(updateRole));
        assertEquals(updateRole, getEntityById(updateRole.getId()));
        update(beforeUpdateRole);
    }

    @Test
    public void roleUpdateNonExistentNegativeTest() {
        assertFalse(update(new Role(100, "гость")));
    }

    @Test
    public void roleUpdateNullNegativeTest() {
        assertFalse(update(null));
    }

    @Test
    public void roleCreatePositiveTest() {
        Role newRole = new Role(3, "модератор");
        assertTrue(create(newRole));
        newRole = getLastAdded();
        assertEquals(newRole, getEntityById(newRole.getId()));
        delete(newRole.getId());
    }

    @Test
    public void roleCreateCloneNegativeTest() {
        assertFalse("Названия ролей должны быть уникальными", create(new Role(5, "администратор")));
    }

    @Test
    public void roleCreateNullNegativeTest() {
        assertFalse(create(null));
    }

    @Test
    public void roleCreateNullNameNegativeTest() {
        assertFalse("Названия ролей не могут быть null", create(new Role()));
    }

    @Test
    public void roleDeletePositiveTest() {
        Role forDeleteRole = new Role(4, "премиум аккаунт");
        create(forDeleteRole);
        forDeleteRole = getLastAdded();
        assertTrue(delete(forDeleteRole.getId()));
        assertNull(getEntityById(forDeleteRole.getId()));
    }

    @Test
    public void roleDeleteNonExistentNegativeTest() {
        assertFalse(delete(100));
    }

    @Test
    public void roleGetRoleByUserIdPositiveTest() {
        assertEquals(new Role(2, "администратор"), getRoleByUserId(1));
    }

    private Role getLastAdded() {
        for (var t : getAll())
            if (t.getId() > getExpectedGetAll().size()) return t;
        return null;
    }

    @After
    public void tearDown() {
        DataSource.close();
    }
}
