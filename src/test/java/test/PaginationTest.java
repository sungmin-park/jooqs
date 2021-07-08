package test;

import com.github.parksungmin.jooqs.Pagination;
import com.github.parksungmin.jooqs.database.tables.pojos.User;
import com.github.parksungmin.jooqs.database.tables.records.UserRecord;
import kotlin.jvm.functions.Function1;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

import static com.github.parksungmin.jooqs.database.Tables.USER;
import static test.TestKt.db;

public class PaginationTest {
    private final Function1<UserRecord, User> mapper = (r) -> r.into(User.class);

    @Test
    public void testNormalizePage() {
        final Function<Integer, Pagination<User>> getUsers =
                (page) -> db(ctx -> Pagination.of(ctx, ctx.selectFrom(USER).where(USER.ID.lt(0)), page, mapper));

        Assert.assertEquals(1, getUsers.apply(1).getPage());

        // overflow 방지
        Assert.assertEquals(1, getUsers.apply(10).getPage());

        // underflow 방지
        Assert.assertEquals(1, getUsers.apply(-1).getPage());
    }

    @Test
    public void test() {
        final Pagination<User> pagination = db(ctx -> Pagination.of(ctx, ctx.selectFrom(USER), 5, 3, 7, mapper));
        System.out.println("pagination = " + pagination);
    }

    @Test
    public void testMapperDefault() {
        final Pagination<UserRecord> pagination = db(ctx -> Pagination.of(ctx, ctx.selectFrom(USER), 5, 3, 7));
        System.out.println("pagination = " + pagination);
    }
}
