package com.example.cs193a_hw5_devansh

import java.io.Serializable

/**
 * This is a simple wrapper class around database 'node' rows.
 */
class DBNode : Serializable {
    var text: String = ""
    var type: String = ""
    var id = -1
    var nodeid = -1
    var yes = -1
    var no = -1

    val isAnswer: Boolean
        get() = type.equals("answer", ignoreCase = true)

    val isQuestion: Boolean
        get() = type.equals("question", ignoreCase = true)

    override fun toString(): String {
        return ("DBNode {id=$id nodeid=$nodeid type=\"$type\" text=\"$text\""
                + (if (isQuestion && yes >= 0 && no >= 0) ", yes=$yes, no=$no" else "")
                + "}")
    }
}


/**
 * This is a simple wrapper class around database 'graph' rows.
 */
class DBGraph : Serializable {
    var childID: Int = 0
    var graphID: Int = 0
    var parentID: Int = 0
    var type: String = ""

    val isYes: Boolean
        get() = type.equals("yes", ignoreCase = true)

    val isNo: Boolean
        get() = !isYes

    override fun toString(): String {
        return ("DBGraph {graphid=$graphID, parentid=$parentID, childid=$childID, type=\"$type\"}")
    }
}