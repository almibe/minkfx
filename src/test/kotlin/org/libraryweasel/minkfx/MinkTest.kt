package org.libraryweasel.minkfx

import javafx.scene.Node
import javafx.scene.layout.GridPane
import javafx.scene.text.Text
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import org.junit.Test as test

class MinkTest {
    test fun emptyTest() {
        val node: Node = mink {}
        assertNotNull(node)
    }

    test fun addSingleTextNodeTest() {
        val hello = Text("Hello minkfx")
        val node = mink { +hello }

        val children = node.getChildren()
        assertEquals(1, children.size())
        assertEquals(hello, children[0])
    }

    test fun addTwoTextNodeTest() {
        val hello = Text("Hello")
        val minkfx = Text("minkfx")
        val node = mink { +hello +minkfx }

        val children = node.getChildren()
        assertEquals(2, children.size())
        assertEquals(hello, children[0])
        assertEquals(minkfx, children[1])
    }

    test fun singleSpanTest() {
        val hello = Text("Hello")
        val node = mink { +Span(hello, 3) }

        assertEquals(3, GridPane.getColumnSpan(hello))
        assertEquals(1, node.getChildren().size())
    }

    test fun parametersToCoordinatesTest() {
        val result = parametersToCoordinates(3,4,5,7)

        assertEquals(35, result.size())
        assert(result.containsAll(listOf(Coordinate(3,4))));
    }

    test fun positionSpanTest() {
        val hello = Text("Hello")
        val world = Text("World")
        val minkfx = Text("from minkfx")

        val node = mink { +hello +Span(world, 3) +minkfx }

        assertEquals(3, node.getChildren().size())
        assertEquals(3, GridPane.getColumnSpan(world))
        assertEquals(0, GridPane.getColumnIndex(hello))
        assertEquals(1, GridPane.getColumnIndex(world))
        assertEquals(4, GridPane.getColumnIndex(minkfx))
    }

    test fun breakTest() {
        val hello = Text("Hello")
        val world = Text("World")
        val minkfx = Text("from minkfx")

        val node = mink {
            +hello +Break()
            +Span(world, 3) +minkfx
        }

        assertEquals(3, node.getChildren().size())

        assertEquals(0, GridPane.getColumnIndex(hello))
        assertEquals(0, GridPane.getRowIndex(hello))

        assertEquals(0, GridPane.getColumnIndex(world))
        assertEquals(1, GridPane.getRowIndex(world))

        assertEquals(3, GridPane.getColumnIndex(minkfx))
        assertEquals(1, GridPane.getRowIndex(minkfx))
    }

    test fun blankTest() {
        val hello = Text("Hello")
        val world = Text("World")
        val minkfx = Text("from minkfx")
        val ex = Text("!!!")

        val node = mink {
            +Blank(2,2) +hello +Break()
            +Span(world, 3) +Blank() +minkfx +Break()
            +ex
        }

        assertEquals(4, node.getChildren().size())

        assertEquals(2, GridPane.getColumnIndex(hello))
        assertEquals(0, GridPane.getRowIndex(hello))

        assertEquals(2, GridPane.getColumnIndex(world))
        assertEquals(1, GridPane.getRowIndex(world))

        assertEquals(5, GridPane.getColumnIndex(minkfx))
        assertEquals(1, GridPane.getRowIndex(minkfx))

        assertEquals(0, GridPane.getColumnIndex(ex))
        assertEquals(2, GridPane.getRowIndex(ex))
    }
//
//    test fun spanRowTest() {
//
//    }
//
//    test fun spanColumnTest() {
//
//    }
}
