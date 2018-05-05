package com.simplyapp.plate.calculator.testing

abstract class FixtureBuilder<T>(protected var dataGenerator: DataGenerator) {

    /**
     * @return a built instance of type T.
     */
    abstract fun build(): T

    fun setSeed(seed: Long) {
        dataGenerator.setSeed(seed)
    }

}
