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
    Role updateRole = new Role(1, "обычный пользователь"),
            beforeUpdateRole = new Role(1, "пользователь"),
            newRole = new Role(3, "модератор"),
            forDeleteRole = new Role(4, "премиум аккаунт");

    @Before
    public void init() {
        DataSource.makeTest();
    }

    public List<Role> createPositiveInput() {
        return Arrays.asList(new Role(2, "администратор"),
                new Role(1, "пользователь"));
    }

    public List<Role> createNegativeInput() {
        return List.of(new Role(2, "обычный пользователь"));
    }

    @Test
    public void roleGetAllSizePositiveTest() {
        assertEquals(createPositiveInput().size(), getAll().size());
    }

    @Test
    public void roleGetAllSizeNegativeTest() {
        assertNotEquals(createNegativeInput().size(), getAll().size());
    }

    @Test
    public void roleGetAllPositiveTest() {
        assertEquals(createPositiveInput(), getAll());
    }

    @Test
    public void roleGetAllNegativeTest() {
        assertNotEquals(createNegativeInput(), getAll());
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
        assertTrue(update(updateRole));
        assertEquals(updateRole, getEntityById(updateRole.getId()));
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
        assertTrue(create(newRole));
        newRole = getLastAdded();
        assertEquals(newRole, getEntityById(newRole.getId()));
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
            if (t.getId() > 2) return t;
        return null;
    }

    @After
    public void dataRecovery() {
        update(beforeUpdateRole);
        delete(newRole.getId());
        DataSource.close();
    }
}
