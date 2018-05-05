package com.simplyapp.plate.calculator.testing

import org.apache.commons.lang3.RandomStringUtils
import java.util.Random

class DataGenerator {
    private val random: Random

    init {
        this.random = Random()
    }

    fun setSeed(seed: Long) {
        random.setSeed(seed)
    }

    fun nextEmail(): String {
        return RandomStringUtils.random(nextInt(3, 12), 32, 127, true, true) + "@" + RandomStringUtils.random(nextInt(3, 12), 32, 127, true, true) + nextDomainExtension()
    }

    fun nextDomainExtension(): String {
        val ext: String
        when (nextInt(3)) {
            0 -> ext = RandomStringUtils.random(2, 32, 127, true, false) + "." + RandomStringUtils.random(2, 32, 127, true, false)
            1 -> ext = RandomStringUtils.random(3, 32, 127, true, false)
            else -> ext = RandomStringUtils.random(5, 32, 127, true, false)
        }
        return "." + ext
    }

    fun nextTelephone(): String {
        val builder = StringBuilder()
        builder.append(random.nextInt(10))
        builder.append(random.nextInt(10))
        builder.append(random.nextInt(10))
        builder.append(random.nextInt(10))
        builder.append(" ")
        builder.append(random.nextInt(10))
        builder.append(random.nextInt(10))
        builder.append(random.nextInt(10))
        builder.append(random.nextInt(10))
        builder.append(random.nextInt(10))
        builder.append(random.nextInt(10))
        builder.append(random.nextInt(10))
        return builder.toString()
    }

    fun nextPersonTitle(): String {
        return nextOneOf("Mr", "Mrs", "Miss", "Dr")
    }

    /**
     * Generates a random string of a specified length.
     *
     * @param length the length of the string to generate.
     * @return a randomly generated string.
     */
    fun nextString(length: Int): String {
        return RandomStringUtils.random(length, 32, 127, false, false, null, random)
    }

    /**
     * Generates a random string of a specified length.
     *
     * @param length the length of the string to generate.
     * @return a randomly generated string.
     */
    fun nextAlphaString(length: Int): String {
        return RandomStringUtils.random(length, 32, 127, true, false, null, random)
    }

    /**
     * Return a random value from a set of valid values.
     *
     * @param <T>    Type of value to return.
     * @param values Possible values.
     * @return One of `values`.
    </T> */
    @SafeVarargs
    fun <T> nextOneOf(vararg values: T): T {
        return values[nextInt(values.size)]
    }

    /**
     * Return a random value from a list of valid values.
     *
     * @param <T>    Type of value to return.
     * @param values List of values.
     * @return One of `values`.
    </T> */
    fun <T> nextOneOf(values: List<T>): T {
        return values[nextInt(values.size)]
    }

    /**
     * Gets the next random integer in the range [0.. Integer.MAX_VALUE].
     *
     * @return a random integer.
     */
    fun nextInt(): Int {
        return random.nextInt()
    }

    /**
     * Gets the next random integer in the range [0.. n-1].
     *
     * @param max the positive upper boundary for the result. Must be > 0.
     * @return an integer **less than** the upper boundary `n` and at least zero.
     */
    fun nextInt(max: Int): Int {
        return random.nextInt(max)
    }

    /**
     * Gets the next random integer in the range [m.. n-1].
     *
     * @param min the positive lower boundary for the result. Must be in the range [0.. n-1].
     * @param max the positive upper boundary for the result. Must be > 0.
     * @return an integer **less than** the upper boundary `n` and **higher or equal
     * to** the lower boundary `m`.
     */
    fun nextInt(min: Int, max: Int): Int {
        return min + random.nextInt(max - min)
    }

    /**
     * Gets the next random long >= 0.
     *
     * @return a random long.
     */
    fun nextLong(): Long {
        return Math.abs(random.nextLong())
    }

    /**
     * Gets the next random long in the range [0.. n-1].
     *
     * @param n the positive upper boundary for the result. Must be > 0.
     * @return a random long **less than** the upper boundary `n` and at least zero.
     */
    fun nextLong(n: Long): Long {
        return Math.abs(random.nextLong()) % n
    }

    /**
     * Gets the next random double in the range [0..1) i.e. can never return 1.
     *
     * @return a random double.
     */
    fun nextDouble(): Double {
        return Math.abs(random.nextDouble())
    }

    /**
     * Gets the next random float in the range [0..1) i.e. can never return 1.
     *
     * @return a random float.
     */
    fun nextFloat(): Float {
        return Math.abs(random.nextFloat())
    }

    /**
     * Gets the next random boolean.
     *
     * @return a random boolean.
     */
    fun nextBoolean(): Boolean {
        return random.nextBoolean()
    }

    /**
     * Gets the next random boolean formated as a character, i.e 'Y' or 'N'.
     *
     * @return a random character ('Y'/'N') boolean.
     */
    fun nextYNBoolean(): Char {
        return if (nextBoolean()) 'Y' else 'N'
    }

    /**
     * Gets the next set of random bytes.
     *
     * @param bytes a byte array to populate with random bytes.
     */
    fun nextBytes(bytes: ByteArray) {
        random.nextBytes(bytes)
    }
}
