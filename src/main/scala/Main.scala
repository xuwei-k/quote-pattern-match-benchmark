package example

import scala.quoted._
import scala.tasty.inspector._
import org.openjdk.jmh.annotations.Benchmark

object Main {
  val compilerJarPath: String =
    dotty.tools.dotc.Main.getClass.getProtectionDomain.getCodeSource.getLocation.toURI.toURL.getFile
}

trait IsOptionGet {
  def unapply(using q: Quotes)(tree: q.reflect.Tree): Boolean
}

object IsOptionGet {
  val NoTypeVariable: IsOptionGet = new IsOptionGet {
    def unapply(using q: Quotes)(tree: q.reflect.Tree): Boolean = {
      tree.isExpr && (
        tree.asExpr match {
          case '{ ($x: Option[?]).get } =>
            true
          case _ =>
            false
        }
      )
    }
  }

  val HasTypeVariable: IsOptionGet = new IsOptionGet {
    def unapply(using q: Quotes)(tree: q.reflect.Tree): Boolean = {
      tree.isExpr && (
        tree.asExpr match {
          case '{ ($x: Option[t]).get } =>
            true
          case _ =>
            false
        }
      )
    }
  }

  val LowLevelReflection: IsOptionGet = new IsOptionGet {
    def unapply(using q: Quotes)(tree: q.reflect.Tree): Boolean = {
      import q.reflect.*

      tree match {
        case Select(obj, "get") if obj.tpe <:< TypeRepr.of[Option[?]] =>
          true
        case _ =>
          false
      }
    }
  }

}

class Main {
  inline def expected: Int = 125

  def test(extractor: IsOptionGet): Int = {
    var count: Int = 0
    val inspector: Inspector = new Inspector {
      override def inspect(using Quotes)(tastys: List[Tasty[quotes.type]]): Unit = {
        import quotes.reflect.*
        val traverser = new quotes.reflect.TreeTraverser {
          override def traverseTree(tree: Tree)(owner: Symbol): Unit = {
            tree match {
              case extractor() =>
                count += 1
              case _ =>
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
  def hasTypeVariable(): Int = test(IsOptionGet.HasTypeVariable)

  @Benchmark
  def noTypeVariable(): Int = test(IsOptionGet.NoTypeVariable)

  @Benchmark
  def lowLevelReflection(): Int = test(IsOptionGet.LowLevelReflection)

}
