package com.github.parksungmin.jooqs

import org.jooq.DSLContext
import org.jooq.Select
import org.jooq.impl.DSL

object Jooqs {
    @JvmStatic
    fun exists(ctx: DSLContext, query: Select<*>): Boolean {
        val field = DSL.field(DSL.exists(query))
        return ctx.select(field).fetchOne(field)!!
    }
}