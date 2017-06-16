package test;

import com.github.parksungmin.jooqs.Jooqs;
import org.junit.Assert;
import org.junit.Test;

import static com.github.parksungmin.jooqs.database.Tables.USER;
import static test.TestKt.db;

public class JooqsTest {
    @Test
    public void testExists() {
        final Boolean exists = db(dslContext -> Jooqs.exists(dslContext, dslContext.selectFrom(USER)));
        Assert.assertEquals(true, exists);

        final Boolean notExists = db(dslContext -> Jooqs.exists(dslContext, dslContext.selectFrom(USER).where(USER.ID.lt(0))));
        Assert.assertEquals(false, notExists);
    }
}
