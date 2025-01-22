package example

import scala.quoted._
import scala.tasty.inspector._
import org.openjdk.jmh.annotations.Benchmark

object Main {
  val compilerJarPath: String =
    dotty.tools.dotc.Main.getClass.getProtectionDomain.getCodeSource.getLocation.toURI.toURL.getFile
}

class Main {
  inline def expected: Int = 125

  @Benchmark
  def lowLevelReflection(): Int = {
    var count: Int = 0
    val inspector: Inspector = new Inspector {
      override def inspect(using Quotes)(tastys: List[Tasty[quotes.type]]): Unit = {
        import quotes.reflect.*
        val traverser = new quotes.reflect.TreeTraverser {
          override def traverseTree(tree: Tree)(owner: Symbol): Unit = {
            if (tree.isExpr) {
              tree match {
                case Select(obj, "get") if obj.tpe <:< TypeRepr.of[Option[?]] =>
                  count += 1
                case _ =>
                  super.traverseTree(tree)(owner)
              }
            } else {
              super.traverseTree(tree)(owner)
            }
          }
        }
        tastys.foreach { t =>
          traverser.traverseTree(t.ast)(Symbol.spliceOwner)
        }
      }
    }
    TastyInspector.inspectTastyFilesInJar(Main.compilerJarPath)(inspector)
    assert(count == expected)
    count
  }

  @Benchmark
  def quoteMatch(): Int = {
    var count: Int = 0
    val inspector: Inspector = new Inspector {
      override def inspect(using Quotes)(tastys: List[Tasty[quotes.type]]): Unit = {
        import quotes.reflect.*
        val traverser = new quotes.reflect.TreeTraverser {
          override def traverseTree(tree: Tree)(owner: Symbol): Unit = {
            if (tree.isExpr) {
              tree.asExpr match {
                case '{ ($x: Option[t]).get } =>
                  count += 1
                case _ =>
                  super.traverseTree(tree)(owner)
              }
            } else {
              super.traverseTree(tree)(owner)
            }
          }
        }
        tastys.foreach { t =>
          traverser.traverseTree(t.ast)(Symbol.spliceOwner)
        }
      }
    }
    TastyInspector.inspectTastyFilesInJar(Main.compilerJarPath)(inspector)
    assert(count == expected)
    count
  }

}
