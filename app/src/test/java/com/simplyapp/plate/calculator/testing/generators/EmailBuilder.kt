package com.simplyapp.plate.calculator.testing.generators

import com.simplyapp.plate.calculator.testing.DataGenerator
import com.simplyapp.plate.calculator.testing.FixtureBuilder

class EmailBuilder(dataGenerator: DataGenerator) : FixtureBuilder<String>(dataGenerator) {

    internal var output: String? = null

    fun reset(): EmailBuilder {
        output = null
        return this
    }

    override fun build(): String {
        if (output == null) {
            output = dataGenerator.nextEmail()
        }
        return output!!
    }

    fun setEmail(email: String): EmailBuilder {
        this.output = email
        return this
    }

}
