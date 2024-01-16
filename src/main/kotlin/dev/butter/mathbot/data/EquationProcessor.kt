package dev.butter.mathbot.data

import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

object EquationProcessor {
    val equations: MutableList<String> = mutableListOf()

    fun evaluate() {
        val total = equations
            .asSequence()
            .map { it.replace("x", "*", true) }
            .map(::ExpressionBuilder)
            .map(ExpressionBuilder::build)
            .map(Expression::evaluate)
            .map(Double::toInt)
            .sum()
        val selection = StringSelection(total.toString())
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard

        clipboard.setContents(selection, selection)

        println("answer: $total")
        equations.clear()
    }
}