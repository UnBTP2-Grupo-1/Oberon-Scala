package br.unb.cic.oberon.parser

import java.nio.file.{Files, Paths}

import org.scalatest.funsuite.AnyFunSuite
import br.unb.cic.oberon.ast._

class ParserTestSuite extends AnyFunSuite {

  test("Testing the oberon simple01 code") {
    val path = Paths.get(getClass.getClassLoader.getResource("simple/simple01.oberon").getFile)

    assert(path != null)

    val content = String.join("\n", Files.readAllLines(path))
    val module = ScalaParser.parse(content)

    assert(module.name == "SimpleModule")
    assert(module.constants.size == 1)
    assert(module.constants.head == Constant(Variable("x"), IntValue(5)))
  }


  test("Testing the oberon simple02 code. This module has one constants and two variables") {
    val path = Paths.get(getClass.getClassLoader.getResource("simple/simple02.oberon").getFile)

    assert(path != null)

    val content = String.join("\n", Files.readAllLines(path))
    val module = ScalaParser.parse(content)

    assert(module.name == "SimpleModule")
    assert(module.constants.size == 1)
    assert(module.constants.head == Constant(Variable("x"), IntValue(5)))
    assert(module.variables.size == 2)
    assert(module.variables.head == VariableDeclaration("abc", IntegerType))
    assert(module.variables(1) == VariableDeclaration("def", BooleanType))
  }

  test("Testing the oberon simple03 code. This module has three constants and two variables") {
    val path = Paths.get(getClass.getClassLoader.getResource("simple/simple03.oberon").getFile)

    assert(path != null)

    val content = String.join("\n", Files.readAllLines(path))
    val module = ScalaParser.parse(content)

    assert(module.name == "SimpleModule")
    assert(module.constants.size == 3)
    assert(module.constants.head == Constant(Variable("x"), IntValue(5)))
    assert(module.constants(1) == Constant(Variable("y"), IntValue(10)))
    assert(module.constants(2) == Constant(Variable("z"), BoolValue(true)))

    assert(module.variables.size == 2)
    assert(module.variables.head == VariableDeclaration("abc", IntegerType))
    assert(module.variables(1) == VariableDeclaration("def", BooleanType))
  }

  test("Testing the oberon simple04 code. This module has three constants, a sum, and two variables") {
    val path = Paths.get(getClass.getClassLoader.getResource("simple/simple04.oberon").getFile)

    assert(path != null)

    val content = String.join("\n", Files.readAllLines(path))
    val module = ScalaParser.parse(content)

    assert(module.name == "SimpleModule")
    assert(module.constants.size == 3)
    assert(module.constants.head == Constant(Variable("x"), IntValue(5)))
    assert(module.constants(1) == Constant(Variable("y"), IntValue(10)))
    assert(module.constants(2) == Constant(Variable("z"), AddExpression(IntValue(5), IntValue(10))))


    assert(module.variables.size == 2)
    assert(module.variables.head == VariableDeclaration("abc", IntegerType))
    assert(module.variables(1) == VariableDeclaration("def", BooleanType))
  }

  test("Testing the oberon simple05 code. This module has one constant, a multiplication, and two variables") {
    val path = Paths.get(getClass.getClassLoader.getResource("simple/simple05.oberon").getFile)

    assert(path != null)

    val content = String.join("\n", Files.readAllLines(path))
    val module = ScalaParser.parse(content)

    assert(module.name == "SimpleModule")
    assert(module.constants.size == 1)
    assert(module.constants.head == Constant(Variable("z"), MultExpression(IntValue(5), IntValue(10))))


    assert(module.variables.size == 2)
    assert(module.variables.head == VariableDeclaration("abc", IntegerType))
    assert(module.variables(1) == VariableDeclaration("def", BooleanType))
  }


  test("Testing the oberon simple06 code. This module has one constants, complex expression, and two variables") {
    val path = Paths.get(getClass.getClassLoader.getResource("simple/simple06.oberon").getFile)

    assert(path != null)

    val content = String.join("\n", Files.readAllLines(path))
    val module = ScalaParser.parse(content)

    assert(module.name == "SimpleModule")
    assert(module.constants.size == 1)
    assert(module.constants.head == Constant(Variable("z"), AddExpression(IntValue(5), MultExpression(IntValue(10), IntValue(3)))))


    assert(module.variables.size == 2)
    assert(module.variables.head == VariableDeclaration("abc", IntegerType))
    assert(module.variables(1) == VariableDeclaration("def", BooleanType))
  }

  test("Testing the oberon simple07 code. This module has two constants, a complex expression, and two variables") {
    val path = Paths.get(getClass.getClassLoader.getResource("simple/simple07.oberon").getFile)

    assert(path != null)

    val content = String.join("\n", Files.readAllLines(path))
    val module = ScalaParser.parse(content)

    assert(module.name == "SimpleModule")
    assert(module.constants.size == 2)
    assert(module.constants.head == Constant(Variable("x"), AddExpression(IntValue(5), MultExpression(IntValue(10), IntValue(3)))))
      assert(module.constants(1) == Constant(Variable("y"),
        AddExpression(IntValue(5),
         DivExpression(
           MultExpression(IntValue(10), IntValue(3)),
           IntValue(5)))))

    assert(module.variables.size == 2)
    assert(module.variables.head == VariableDeclaration("abc", IntegerType))
    assert(module.variables(1) == VariableDeclaration("def", BooleanType))
  }

  test("Testing the oberon simple08 code. This module has three constants, a boolean expresson, and two variables") {
    val path = Paths.get(getClass.getClassLoader.getResource("simple/simple08.oberon").getFile)

    assert(path != null)

    val content = String.join("\n", Files.readAllLines(path))
    val module = ScalaParser.parse(content)

    assert(module.name == "SimpleModule")
    assert(module.constants.size == 3)
    assert(module.constants.head == Constant(Variable("x"), BoolValue(false)))
    assert(module.constants(1) == Constant(Variable("y"), BoolValue(true)))
    assert(module.constants(2) == Constant(Variable("z"), AndExpression(BoolValue(true), BoolValue(false))))
  }

  ignore("Testing the oberon simple09 code. This module has one constant and an expression involving both 'and' and 'or'") {
    val path = Paths.get(getClass.getClassLoader.getResource("simple/simple09.oberon").getFile)

    assert(path != null)

    val content = String.join("\n", Files.readAllLines(path))
    val module = ScalaParser.parse(content)

    assert(module.name == "SimpleModule")
    assert(module.constants.size == 1)
    assert(module.constants.head == Constant(Variable("x"), OrExpression(AndExpression(BoolValue(true), BoolValue(false)), BoolValue(false))))
  }

  test("Testing the oberon stmt01 code. This module has a block of three statements") {
    val path = Paths.get(getClass.getClassLoader.getResource("stmts/stmt01.oberon").getFile)

    assert(path != null)

    val content = String.join("\n", Files.readAllLines(path))
    val module = ScalaParser.parse(content)

    assert(module.name == "SimpleModule")

    assert(module.stmt.isDefined)

    // assert that the main block contains a sequence of statements
    module.stmt.get match {
      case SequenceStmt(stmts) => assert(stmts.length == 3)
      case _ => fail("we are expecting three stmts in the main block")
    }

    // now we can assume that the main block contains a sequence of stmts
    val sequence = module.stmt.get.asInstanceOf[SequenceStmt]
    val stmts = sequence.stmts

    assert(stmts.head == ReadStmt("x"))
    assert(stmts(1) == ReadStmt("y"))
    assert(stmts(2) == WriteStmt(AddExpression(VarExpression("x"), VarExpression("y"))))
  }

  test("Testing the oberon stmt02 code. This module has a block of four statements") {
    val path = Paths.get(getClass.getClassLoader.getResource("stmts/stmt02.oberon").getFile)

    assert(path != null)

    val content = String.join("\n", Files.readAllLines(path))
    val module = ScalaParser.parse(content)

    assert(module.name == "SimpleModule")

    assert(module.stmt.isDefined)

    // assert that the main block contains a sequence of statements
    module.stmt.get match {
      case SequenceStmt(stmts) => assert(stmts.length == 4)
      case _ => fail("we are expecting three stmts in the main block")
    }

    // now we can assume that the main block contains a sequence of stmts
    val sequence = module.stmt.get.asInstanceOf[SequenceStmt]
    val stmts = sequence.stmts

    assert(stmts.head == ReadStmt("x"))
    assert(stmts(1) == ReadStmt("y"))
    assert(stmts(2) == AssignmentStmt("z", AddExpression(VarExpression("x"), VarExpression("y"))))
    assert(stmts(3) == WriteStmt(VarExpression("z")))
  }

  test("Testing the oberon stmt03 code. This module has IF-THEN statement") {
    val path = Paths.get(getClass.getClassLoader.getResource("stmts/stmt03.oberon").getFile)

    assert(path != null)

    val content = String.join("\n", Files.readAllLines(path))
    val module = ScalaParser.parse(content)

    assert(module.name == "SimpleModule")

    assert(module.stmt.isDefined)

    // assert that the main block contains a sequence of statements
    module.stmt.get match {
      case SequenceStmt(stmts) => assert(stmts.length == 4)
      case _ => fail("we are expecting three stmts in the main block")
    }

    // now we can assume that the main block contains a sequence of stmts
    val sequence = module.stmt.get.asInstanceOf[SequenceStmt]
    val stmts = sequence.stmts

    assert(stmts.head == ReadStmt("x"))
    assert(stmts(1) == ReadStmt("max"))

    // the third stmt must be an IfElseStmt
    stmts(2) match {
      case IfElseStmt(cond, s1, s2) =>
        assert(cond == GTExpression(VarExpression("x"),VarExpression("max")))
        assert(s1 == AssignmentStmt("max",VarExpression("x")))
        assert(s2.isEmpty) // the else stmt is None
      case _ => fail("expecting an if-then stmt")
    }

    assert(stmts(3) == WriteStmt(VarExpression("max")))
  }

  test("Testing the oberon stmt04 code. This module has a While statement") {
    val path = Paths.get(getClass.getClassLoader.getResource("stmts/stmt04.oberon").getFile)

    assert(path != null)

    val content = String.join("\n", Files.readAllLines(path))
    val module = ScalaParser.parse(content)

    assert(module.name == "SimpleModule")

    assert(module.stmt.isDefined)

    // assert that the main block contains a sequence of statements
    module.stmt.get match {
      case SequenceStmt(stmts) => assert(stmts.length == 4)
      case _ => fail("we are expecting three stmts in the main block")
    }

    // now we can assume that the main block contains a sequence of stmts
    val sequence = module.stmt.get.asInstanceOf[SequenceStmt]
    val stmts = sequence.stmts

    assert(stmts.head == ReadStmt("x"))
    assert(stmts(1) == ReadStmt("y"))

    // the third stmt must be an WhileStmt
    stmts(2) match {
      case WhileStmt(cond, stmt) =>
        assert(cond == LTExpression(VarExpression("x"),VarExpression("y")))
        assert(stmt == AssignmentStmt("x", MultExpression(VarExpression("x"), VarExpression("x"))))
      case _ => fail("expecting an if-then stmt")
    }
    assert(stmts(3) == WriteStmt(VarExpression("x")))
  }
}
