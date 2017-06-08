package test

import org.h2.Driver
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import java.io.File

fun <T> db(run: (DSLContext) -> T): T {
    return Driver().connect("jdbc:h2:mem:test-jooq-tools", null).use {
        val create = DSL.using(it, SQLDialect.H2)
        create.execute(File("src/test/resources/db.sql").readText())
        run(create)
    }
}

